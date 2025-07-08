package br.com.emprestimobiblioteca.daos;

import br.com.emprestimobiblioteca.entities.Equipamento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoDAO {
    public void inserir(Equipamento equipamento) throws SQLException {
        String sql = "INSERT INTO equipamento (tipo, marca, modelo, num_de_serie, data_cadastro, ativo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, equipamento.getTipo());
            stmt.setString(2, equipamento.getMarca());
            stmt.setString(3, equipamento.getModelo());
            stmt.setString(4, equipamento.getNumDeSerie());
            stmt.setTimestamp(5, Timestamp.valueOf(equipamento.getDataCadastro()));
            stmt.setBoolean(6, equipamento.isAtivo());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    equipamento.setCodigo(rs.getLong(1));
                }
            }
        }
    }

    public Equipamento buscarPorId(long codigo) throws SQLException {
        String sql = "SELECT * FROM equipamento WHERE codigo = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, codigo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Equipamento eq = new Equipamento();
                    eq.setCodigo(rs.getLong("codigo"));
                    eq.setTipo(rs.getString("tipo"));
                    eq.setMarca(rs.getString("marca"));
                    eq.setModelo(rs.getString("modelo"));
                    eq.setNumDeSerie(rs.getString("num_de_serie"));
                    eq.setDataCadastro(rs.getTimestamp("data_cadastro").toLocalDateTime());
                    eq.setAtivo(rs.getBoolean("ativo"));
                    return eq;
                }
            }
        }
        return null;
    }

    public List<Equipamento> listarTodos() throws SQLException {
        List<Equipamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM equipamento";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Equipamento eq = new Equipamento();
                eq.setCodigo(rs.getLong("codigo"));
                eq.setTipo(rs.getString("tipo"));
                eq.setMarca(rs.getString("marca"));
                eq.setModelo(rs.getString("modelo"));
                eq.setNumDeSerie(rs.getString("num_de_serie"));
                eq.setDataCadastro(rs.getTimestamp("data_cadastro").toLocalDateTime());
                eq.setAtivo(rs.getBoolean("ativo"));
                lista.add(eq);
            }
        }
        return lista;
    }

    public void atualizar(Equipamento equipamento) throws SQLException {
        String sql = "UPDATE equipamento SET tipo = ?, marca = ?, modelo = ?, num_de_serie = ?, data_cadastro = ?, ativo = ? WHERE codigo = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, equipamento.getTipo());
            stmt.setString(2, equipamento.getMarca());
            stmt.setString(3, equipamento.getModelo());
            stmt.setString(4, equipamento.getNumDeSerie());
            stmt.setTimestamp(5, Timestamp.valueOf(equipamento.getDataCadastro()));
            stmt.setBoolean(6, equipamento.isAtivo());
            stmt.setLong(7, equipamento.getCodigo());
            stmt.executeUpdate();
        }
    }

    public void deletar(long codigo) throws SQLException {
        String sql = "DELETE FROM equipamento WHERE codigo = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, codigo);
            stmt.executeUpdate();
        }
    }
}
