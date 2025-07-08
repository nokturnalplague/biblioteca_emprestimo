package br.com.emprestimobiblioteca.daos;

import br.com.emprestimobiblioteca.entities.Aluno;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    public void inserir(Aluno aluno) throws SQLException {
        String sql = "INSERT INTO aluno (nome, matricula, curso, email, telefone, data_cadastro, ativo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, aluno.getNome());
            stmt.setLong(2, aluno.getMatricula());
            stmt.setString(3, aluno.getCurso());
            stmt.setString(4, aluno.getEmail());
            stmt.setString(5, aluno.getTelefone());
            stmt.setTimestamp(6, Timestamp.valueOf(aluno.getDataCadastro()));
            stmt.setBoolean(7, aluno.isAtivo());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    aluno.setCodigo(rs.getLong(1));
                }
            }
        }
    }

    public Aluno buscarPorId(long codigo) throws SQLException {
        String sql = "SELECT * FROM aluno WHERE codigo = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, codigo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Aluno aluno = new Aluno();
                    aluno.setCodigo(rs.getLong("codigo"));
                    aluno.setNome(rs.getString("nome"));
                    aluno.setMatricula(rs.getLong("matricula"));
                    aluno.setCurso(rs.getString("curso"));
                    aluno.setEmail(rs.getString("email"));
                    aluno.setTelefone(rs.getString("telefone"));
                    aluno.setDataCadastro(rs.getTimestamp("data_cadastro").toLocalDateTime());
                    aluno.setAtivo(rs.getBoolean("ativo"));
                    return aluno;
                }
            }
        }
        return null;
    }

    public List<Aluno> listarTodos() throws SQLException {
        List<Aluno> lista = new ArrayList<>();
        String sql = "SELECT * FROM aluno";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setCodigo(rs.getLong("codigo"));
                aluno.setNome(rs.getString("nome"));
                aluno.setMatricula(rs.getLong("matricula"));
                aluno.setCurso(rs.getString("curso"));
                aluno.setEmail(rs.getString("email"));
                aluno.setTelefone(rs.getString("telefone"));
                aluno.setDataCadastro(rs.getTimestamp("data_cadastro").toLocalDateTime());
                aluno.setAtivo(rs.getBoolean("ativo"));
                lista.add(aluno);
            }
        }
        return lista;
    }

    public void atualizar(Aluno aluno) throws SQLException {
        String sql = "UPDATE aluno SET nome = ?, matricula = ?, curso = ?, email = ?, telefone = ?, data_cadastro = ?, ativo = ? WHERE codigo = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setLong(2, aluno.getMatricula());
            stmt.setString(3, aluno.getCurso());
            stmt.setString(4, aluno.getEmail());
            stmt.setString(5, aluno.getTelefone());
            stmt.setTimestamp(6, Timestamp.valueOf(aluno.getDataCadastro()));
            stmt.setBoolean(7, aluno.isAtivo());
            stmt.setLong(8, aluno.getCodigo());
            stmt.executeUpdate();
        }
    }

    public void deletar(long codigo) throws SQLException {
        String sql = "DELETE FROM aluno WHERE codigo = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, codigo);
            stmt.executeUpdate();
        }
    }
}
