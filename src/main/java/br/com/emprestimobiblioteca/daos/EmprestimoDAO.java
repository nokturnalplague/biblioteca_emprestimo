package br.com.emprestimobiblioteca.daos;

import br.com.emprestimobiblioteca.entities.Emprestimo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmprestimoDAO {
    public void inserir(Emprestimo emprestimo) throws SQLException {
        String sql = "INSERT INTO emprestimo (cod_aluno, cod_equipamento, data_emprestimo, data_dev_prevista, data_dev_real, atrasado, ativo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, emprestimo.getCodAluno());
            stmt.setLong(2, emprestimo.getCodEquipamento());
            stmt.setTimestamp(3, Timestamp.valueOf(emprestimo.getDataEmprestimo()));
            stmt.setTimestamp(4, Timestamp.valueOf(emprestimo.getDataDevPrevista()));

            if (emprestimo.getDataDevReal() != null) {
                stmt.setTimestamp(5, Timestamp.valueOf(emprestimo.getDataDevReal()));
            } else {
                stmt.setNull(5, Types.TIMESTAMP);
            }

            stmt.setBoolean(6, emprestimo.isAtrasado());
            stmt.setBoolean(7, emprestimo.isAtivo());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    emprestimo.setCodigo(rs.getLong(1));
                }
            }
        }
    }

    public List<Emprestimo> listarTodos() throws SQLException {
        List<Emprestimo> lista = new ArrayList<>();
        String sql = "SELECT * FROM emprestimo";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Emprestimo e = new Emprestimo();
                e.setCodigo(rs.getLong("codigo"));
                e.setCodAluno(rs.getLong("cod_aluno"));
                e.setCodEquipamento(rs.getLong("cod_equipamento"));
                e.setDataEmprestimo(rs.getTimestamp("data_emprestimo").toLocalDateTime());
                e.setDataDevPrevista(rs.getTimestamp("data_dev_prevista").toLocalDateTime());
                Timestamp dataDevReal = rs.getTimestamp("data_dev_real");
                e.setDataDevReal(dataDevReal != null ? dataDevReal.toLocalDateTime() : null);
                e.setAtrasado(rs.getBoolean("atrasado"));
                e.setAtivo(rs.getBoolean("ativo"));
                lista.add(e);
            }
        }
        return lista;
    }

    public void atualizar(Emprestimo emprestimo) throws SQLException {
        String sql = "UPDATE emprestimo SET cod_aluno = ?, cod_equipamento = ?, data_emprestimo = ?, data_dev_prevista = ?, data_dev_real = ?, atrasado = ?, ativo = ? WHERE codigo = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, emprestimo.getCodAluno());
            stmt.setLong(2, emprestimo.getCodEquipamento());
            stmt.setTimestamp(3, Timestamp.valueOf(emprestimo.getDataEmprestimo()));
            stmt.setTimestamp(4, Timestamp.valueOf(emprestimo.getDataDevPrevista()));
            if (emprestimo.getDataDevReal() != null) {
                stmt.setTimestamp(5, Timestamp.valueOf(emprestimo.getDataDevReal()));
            } else {
                stmt.setNull(5, Types.TIMESTAMP);
            }
            stmt.setBoolean(6, emprestimo.isAtrasado());
            stmt.setBoolean(7, emprestimo.isAtivo());
            stmt.setLong(8, emprestimo.getCodigo());
            stmt.executeUpdate();
        }
    }

    public void deletar(long codigo) throws SQLException {
        String sql = "DELETE FROM emprestimo WHERE codigo = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, codigo);
            stmt.executeUpdate();
        }
    }
}
