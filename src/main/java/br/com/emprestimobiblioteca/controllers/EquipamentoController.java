package br.com.emprestimobiblioteca.controllers;

import br.com.emprestimobiblioteca.entities.Equipamento;
import br.com.emprestimobiblioteca.services.EquipamentoService;
import java.sql.SQLException;
import java.util.List;

public class EquipamentoController {
    private final EquipamentoService service = new EquipamentoService();

    public void adicionarEquipamento(Equipamento equipamento) throws SQLException {
        service.cadastrarEquipamento(equipamento);
    }

    public List<Equipamento> listarEquipamentos() throws SQLException {
        return service.listarEquipamentos();
    }

    public Equipamento buscarPorId(long id) throws SQLException {
        return service.buscarPorId(id);
    }

    public void atualizarEquipamento(Equipamento equipamento) throws SQLException {
        service.atualizar(equipamento);
    }

    public void deletarEquipamento(long id) throws SQLException {
        service.deletar(id);
    }
}
