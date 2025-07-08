package br.com.emprestimobiblioteca.daos;

import br.com.emprestimobiblioteca.entities.Vinculo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VinculoDAO {
    public void inserir(Vinculo vinculo) throws SQLException {
        String sql = "INSERT INTO vinculo (cod_aluno, cod_equipamento, qtd_emprestimo, data_inicio, ativo) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, vinculo.getCodAluno());
            stmt.setLong(2, vinculo.getCodEquipamento());
            stmt.setInt(3, vinculo.getQtdEmprestimo());
            stmt.setTimestamp(4, Timestamp.valueOf(vinculo.getDataInicio()));
            stmt.setBoolean(5, vinculo.isAtivo());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    vinculo.setCodigo(rs.getLong(1));
                }
            }
        }
    }

    public List<Vinculo> listarTodos() throws SQLException {
        List<Vinculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM vinculo";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Vinculo v = new Vinculo();
                v.setCodigo(rs.getLong("codigo"));
                v.setCodAluno(rs.getLong("cod_aluno"));
                v.setCodEquipamento(rs.getLong("cod_equipamento"));
                v.setQtdEmprestimo(rs.getInt("qtd_emprestimo"));
                v.setDataInicio(rs.getTimestamp("data_inicio").toLocalDateTime());
                v.setAtivo(rs.getBoolean("ativo"));
                lista.add(v);
            }
        }
        return lista;
    }

    public void atualizar(Vinculo vinculo) throws SQLException {
        String sql = "UPDATE vinculo SET cod_aluno = ?, cod_equipamento = ?, qtd_emprestimo = ?, data_inicio = ?, ativo = ? WHERE codigo = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, vinculo.getCodAluno());
            stmt.setLong(2, vinculo.getCodEquipamento());
            stmt.setInt(3, vinculo.getQtdEmprestimo());
            stmt.setTimestamp(4, Timestamp.valueOf(vinculo.getDataInicio()));
            stmt.setBoolean(5, vinculo.isAtivo());
            stmt.setLong(6, vinculo.getCodigo());
            stmt.executeUpdate();
        }
    }

    public void deletar(long codigo) throws SQLException {
        String sql = "DELETE FROM vinculo WHERE codigo = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, codigo);
            stmt.executeUpdate();
        }
    }

    public Vinculo buscarPorId(long codigo) throws SQLException {
        String sql = "SELECT * FROM vinculo WHERE codigo = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, codigo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Vinculo v = new Vinculo();
                    v.setCodigo(rs.getLong("codigo"));
                    v.setCodAluno(rs.getLong("cod_aluno"));
                    v.setCodEquipamento(rs.getLong("cod_equipamento"));
                    v.setQtdEmprestimo(rs.getInt("qtd_emprestimo"));
                    v.setDataInicio(rs.getTimestamp("data_inicio").toLocalDateTime());
                    v.setAtivo(rs.getBoolean("ativo"));
                    return v;
                }
            }
        }
        return null;
    }
}
