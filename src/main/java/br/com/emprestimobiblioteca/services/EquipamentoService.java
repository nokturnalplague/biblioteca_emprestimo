package br.com.emprestimobiblioteca.services;

import br.com.emprestimobiblioteca.daos.EquipamentoDAO;
import br.com.emprestimobiblioteca.entities.Equipamento;
import java.sql.SQLException;
import java.util.List;

public class EquipamentoService {
    private final EquipamentoDAO dao = new EquipamentoDAO();

    public void cadastrarEquipamento(Equipamento equipamento) throws SQLException {
        dao.inserir(equipamento);
    }

    public List<Equipamento> listarEquipamentos() throws SQLException {
        return dao.listarTodos();
    }

    public Equipamento buscarPorId(long id) throws SQLException {
        return dao.buscarPorId(id);
    }

    public void atualizar(Equipamento equipamento) throws SQLException {
        dao.atualizar(equipamento);
    }

    public void deletar(long id) throws SQLException {
        dao.deletar(id);
    }
}
