package br.com.emprestimobiblioteca.daos;

import br.com.emprestimobiblioteca.daos.*;
import br.com.emprestimobiblioteca.entities.*;

import java.time.LocalDateTime;
import java.util.List;

public class TesteDaos {
    public static void main(String[] args) {
        try {
            // TESTE ALUNO

            Aluno aluno = new Aluno();
            aluno.setNome("Maria Silva");
            aluno.setMatricula(20231234);
            aluno.setCurso("Engenharia de Software");
            aluno.setEmail("maria@exemplo.com");
            aluno.setTelefone("99999-9999");
            aluno.setDataCadastro(LocalDateTime.now());
            aluno.setAtivo(true);

            AlunoDAO alunoDAO = new AlunoDAO();
            alunoDAO.inserir(aluno);
            System.out.println("Aluno inserido com ID: " + aluno.getCodigo());

            List<Aluno> alunos = alunoDAO.listarTodos();
            System.out.println("Lista de alunos:");
            for (Aluno a : alunos) {
                System.out.println(a.getCodigo() + " - " + a.getNome());
            }

            // TESTE EQUIPAMENTO
            Equipamento eq = new Equipamento();
            eq.setTipo("Notebook");
            eq.setMarca("Dell");
            eq.setModelo("Inspiron 15");
            eq.setNumDeSerie("ABC123456");
            eq.setDataCadastro(LocalDateTime.now());
            eq.setAtivo(true);

            EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
            equipamentoDAO.inserir(eq);
            System.out.println("Equipamento inserido com ID: " + eq.getCodigo());

            List<Equipamento> equipamentos = equipamentoDAO.listarTodos();
            System.out.println("Lista de equipamentos:");
            for (Equipamento e : equipamentos) {
                System.out.println(e.getCodigo() + " - " + e.getMarca() + " " + e.getModelo());
            }

            // TESTE VINCULO
            Vinculo vinculo = new Vinculo();
            vinculo.setCodAluno(aluno.getCodigo());
            vinculo.setCodEquipamento(eq.getCodigo());
            vinculo.setQtdEmprestimo(1);
            vinculo.setDataInicio(LocalDateTime.now());
            vinculo.setAtivo(true);

            VinculoDAO vinculoDAO = new VinculoDAO();
            vinculoDAO.inserir(vinculo);
            System.out.println("Vínculo inserido com ID: " + vinculo.getCodigo());

            List<Vinculo> vinculos = vinculoDAO.listarTodos();
            System.out.println("Lista de vínculos:");
            for (Vinculo v : vinculos) {
                System.out.println(v.getCodigo() + " - Aluno: " + v.getCodAluno() + ", Equipamento: " + v.getCodEquipamento());
            }

            // TESTE EMPRESTIMO
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setCodAluno(aluno.getCodigo());
            emprestimo.setCodEquipamento(eq.getCodigo());
            emprestimo.setDataEmprestimo(LocalDateTime.now());
            emprestimo.setDataDevPrevista(LocalDateTime.now().plusDays(7));
            emprestimo.setDataDevReal(null); // ainda não devolvido
            emprestimo.setAtrasado(false);
            emprestimo.setAtivo(true);

            EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
            emprestimoDAO.inserir(emprestimo);
            System.out.println("Empréstimo inserido com ID: " + emprestimo.getCodigo());

            List<Emprestimo> emprestimos = emprestimoDAO.listarTodos();
            System.out.println("Lista de empréstimos:");
            for (Emprestimo e : emprestimos) {
                System.out.println(e.getCodigo() + " - Aluno: " + e.getCodAluno() + ", Equipamento: " + e.getCodEquipamento());
            }

        } catch (Exception e) {
            System.err.println("Erro durante os testes:");
            e.printStackTrace();
        }
    }
}
