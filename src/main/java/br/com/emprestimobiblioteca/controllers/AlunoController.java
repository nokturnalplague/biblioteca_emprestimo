package br.com.emprestimobiblioteca.controllers;

import br.com.emprestimobiblioteca.entities.Aluno;
import br.com.emprestimobiblioteca.services.AlunoService;
import java.sql.SQLException;
import java.util.List;

public class AlunoController {
    private final AlunoService service = new AlunoService();

    public void adicionarAluno(Aluno aluno) throws SQLException {
        service.cadastrarAluno(aluno);
    }

    public List<Aluno> listarAlunos() throws SQLException {
        return service.listarAlunos();
    }

    public Aluno buscarPorId(long id) throws SQLException {
        return service.buscarPorId(id);
    }

    public void atualizarAluno(Aluno aluno) throws SQLException {
        service.atualizar(aluno);
    }

    public void deletarAluno(long id) throws SQLException {
        service.deletar(id);
    }
}
