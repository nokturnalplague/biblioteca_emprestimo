package br.com.emprestimobiblioteca.services;

import br.com.emprestimobiblioteca.daos.EmprestimoDAO;
import br.com.emprestimobiblioteca.entities.Emprestimo;
import java.sql.SQLException;
import java.util.List;

public class EmprestimoService {
    private final EmprestimoDAO dao = new EmprestimoDAO();

    public void cadastrarEmprestimo(Emprestimo emprestimo) throws SQLException {
        dao.inserir(emprestimo);
    }

    public List<Emprestimo> listarEmprestimos() throws SQLException {
        return dao.listarTodos();
    }

    public Emprestimo buscarPorId(long id) throws SQLException {
        return dao.buscarPorId(id);
    }

    public void atualizar(Emprestimo emprestimo) throws SQLException {
        dao.atualizar(emprestimo);
    }

    public void deletar(long id) throws SQLException {
        dao.deletar(id);
    }
}
