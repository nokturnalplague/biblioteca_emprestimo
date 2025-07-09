package br.com.emprestimobiblioteca.views;

import br.com.emprestimobiblioteca.controllers.AlunoController;
import br.com.emprestimobiblioteca.entities.Aluno;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class AlunoView {

    private static final AlunoController controller = new AlunoController();

    public static void menu() {
        String[] opcoes = {
                "Cadastrar Aluno",
                "Listar Alunos",
                "Buscar por ID",
                "Atualizar Aluno",
                "Deletar Aluno",
                "Voltar"
        };

        while (true) {
            int escolha = JOptionPane.showOptionDialog(
                    null,
                    "Selecione uma opção:",
                    "Menu Aluno",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            switch (escolha) {
                case 0 -> cadastrar();
                case 1 -> listar();
                case 2 -> buscarPorId();
                case 3 -> atualizar();
                case 4 -> deletar();
                case 5, JOptionPane.CLOSED_OPTION -> {
                    return;
                }
            }
        }
    }

    private static void cadastrar() {
        try {
            String nome = JOptionPane.showInputDialog("Nome:");
            long matricula = Long.parseLong(JOptionPane.showInputDialog("Matrícula:"));
            String curso = JOptionPane.showInputDialog("Curso:");
            String email = JOptionPane.showInputDialog("Email:");
            String telefone = JOptionPane.showInputDialog("Telefone:");
            boolean ativo = JOptionPane.showConfirmDialog(null, "Ativo?") == JOptionPane.YES_OPTION;

            Aluno aluno = new Aluno(0, nome, matricula, curso, email, telefone, LocalDateTime.now(), ativo);
            controller.adicionarAluno(aluno);
            JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar aluno: " + e.getMessage());
        }
    }

    private static void listar() {
        try {
            List<Aluno> alunos = controller.listarAlunos();
            StringBuilder sb = new StringBuilder("Alunos:\n");
            for (Aluno aluno : alunos) {
                sb.append(aluno).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.length() > 0 ? sb.toString() : "Nenhum aluno encontrado.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar alunos: " + e.getMessage());
        }
    }

    private static void buscarPorId() {
        try {
            long id = Long.parseLong(JOptionPane.showInputDialog("ID do aluno:"));
            Aluno aluno = controller.buscarPorId(id);
            JOptionPane.showMessageDialog(null, aluno != null ? aluno.toString() : "Aluno não encontrado.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar aluno: " + e.getMessage());
        }
    }

    private static void atualizar() {
        try {
            long id = Long.parseLong(JOptionPane.showInputDialog("ID do aluno a atualizar:"));
            Aluno aluno = controller.buscarPorId(id);
            if (aluno == null) {
                JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
                return;
            }

            String nome = JOptionPane.showInputDialog("Nome:", aluno.getNome());
            long matricula = Long.parseLong(JOptionPane.showInputDialog("Matrícula:", String.valueOf(aluno.getMatricula())));
            String curso = JOptionPane.showInputDialog("Curso:", aluno.getCurso());
            String email = JOptionPane.showInputDialog("Email:", aluno.getEmail());
            String telefone = JOptionPane.showInputDialog("Telefone:", aluno.getTelefone());
            boolean ativo = JOptionPane.showConfirmDialog(null, "Ativo?") == JOptionPane.YES_OPTION;

            aluno.setNome(nome);
            aluno.setMatricula(matricula);
            aluno.setCurso(curso);
            aluno.setEmail(email);
            aluno.setTelefone(telefone);
            aluno.setAtivo(ativo);

            controller.atualizarAluno(aluno);
            JOptionPane.showMessageDialog(null, "Aluno atualizado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar aluno: " + e.getMessage());
        }
    }

    private static void deletar() {
        try {
            long id = Long.parseLong(JOptionPane.showInputDialog("ID do aluno a deletar:"));
            controller.deletarAluno(id);
            JOptionPane.showMessageDialog(null, "Aluno deletado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar aluno: " + e.getMessage());
        }
    }
}
