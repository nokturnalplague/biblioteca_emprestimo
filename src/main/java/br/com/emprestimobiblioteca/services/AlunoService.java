package br.com.emprestimobiblioteca.services;

import br.com.emprestimobiblioteca.daos.AlunoDAO;
import br.com.emprestimobiblioteca.entities.Aluno;

import java.sql.SQLException;
import java.util.List;

public class AlunoService {
    private final AlunoDAO dao = new AlunoDAO();

    public void cadastrarAluno(Aluno aluno) throws SQLException {
        dao.inserir(aluno);
    }

    public List<Aluno> listarAlunos() throws SQLException {
        return dao.listarTodos();
    }

    public Aluno buscarPorId(long id) throws SQLException {
        return dao.buscarPorId(id);
    }

    public void atualizar(Aluno aluno) throws SQLException {
        dao.atualizar(aluno);
    }

    public void deletar(long id) throws SQLException {
        dao.deletar(id);
    }
}
