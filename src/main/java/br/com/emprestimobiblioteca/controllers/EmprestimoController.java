package br.com.emprestimobiblioteca.controllers;

import br.com.emprestimobiblioteca.entities.Emprestimo;
import br.com.emprestimobiblioteca.services.EmprestimoService;
import java.sql.SQLException;
import java.util.List;

public class EmprestimoController {
    private final EmprestimoService service = new EmprestimoService();

    public void adicionarEmprestimo(Emprestimo emprestimo) throws SQLException {
        service.cadastrarEmprestimo(emprestimo);
    }

    public List<Emprestimo> listarEmprestimos() throws SQLException {
        return service.listarEmprestimos();
    }

    public Emprestimo buscarPorId(long id) throws SQLException {
        return service.buscarPorId(id);
    }

    public void atualizarEmprestimo(Emprestimo emprestimo) throws SQLException {
        service.atualizar(emprestimo);
    }

    public void deletarEmprestimo(long id) throws SQLException {
        service.deletar(id);
    }
}
