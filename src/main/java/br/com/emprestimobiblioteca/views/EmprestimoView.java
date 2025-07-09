package br.com.emprestimobiblioteca.views;

import br.com.emprestimobiblioteca.controllers.EmprestimoController;
import br.com.emprestimobiblioteca.entities.Emprestimo;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class EmprestimoView {

    private static final EmprestimoController controller = new EmprestimoController();

    public static void menu() {
        String[] opcoes = {
                "Registrar Empréstimo",
                "Listar Empréstimos",
                "Buscar por ID",
                "Atualizar Empréstimo",
                "Deletar Empréstimo",
                "Voltar"
        };

        while (true) {
            int escolha = JOptionPane.showOptionDialog(
                    null,
                    "Selecione uma opção:",
                    "Menu Empréstimos",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            switch (escolha) {
                case 0 -> registrar();
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

    private static void registrar() {
        try {
            long codAluno = Long.parseLong(JOptionPane.showInputDialog("Código do Aluno:"));
            long codEquipamento = Long.parseLong(JOptionPane.showInputDialog("Código do Equipamento:"));
            boolean ativo = JOptionPane.showConfirmDialog(null, "Empréstimo ativo?") == JOptionPane.YES_OPTION;

            Emprestimo emprestimo = new Emprestimo(0, codAluno, codEquipamento, LocalDateTime.now(), null, ativo);
            controller.adicionarEmprestimo(emprestimo);
            JOptionPane.showMessageDialog(null, "Empréstimo registrado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar empréstimo: " + e.getMessage());
        }
    }

    private static void listar() {
        try {
            List<Emprestimo> emprestimos = controller.listarEmprestimos();
            StringBuilder sb = new StringBuilder("Empréstimos:\n");
            for (Emprestimo emp : emprestimos) {
                sb.append(emp).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.length() > 0 ? sb.toString() : "Nenhum empréstimo encontrado.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar empréstimos: " + e.getMessage());
        }
    }

    private static void buscarPorId() {
        try {
            long id = Long.parseLong(JOptionPane.showInputDialog("ID do empréstimo:"));
            Emprestimo emp = controller.buscarPorId(id);
            JOptionPane.showMessageDialog(null, emp != null ? emp.toString() : "Empréstimo não encontrado.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar empréstimo: " + e.getMessage());
        }
    }

    private static void atualizar() {
        try {
            long id = Long.parseLong(JOptionPane.showInputDialog("ID do empréstimo a atualizar:"));
            Emprestimo emp = controller.buscarPorId(id);
            if (emp == null) {
                JOptionPane.showMessageDialog(null, "Empréstimo não encontrado.");
                return;
            }

            long codAluno = Long.parseLong(JOptionPane.showInputDialog("Código do Aluno:", emp.getCodAluno()));
            long codEquipamento = Long.parseLong(JOptionPane.showInputDialog("Código do Equipamento:", emp.getCodEquipamento()));
            boolean ativo = JOptionPane.showConfirmDialog(null, "Empréstimo ativo?") == JOptionPane.YES_OPTION;

            emp.setCodAluno(codAluno);
            emp.setCodEquipamento(codEquipamento);
            emp.setAtivo(ativo);

            controller.atualizarEmprestimo(emp);
            JOptionPane.showMessageDialog(null, "Empréstimo atualizado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar empréstimo: " + e.getMessage());
        }
    }

    private static void deletar() {
        try {
            long id = Long.parseLong(JOptionPane.showInputDialog("ID do empréstimo a deletar:"));
            controller.deletarEmprestimo(id);
            JOptionPane.showMessageDialog(null, "Empréstimo deletado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar empréstimo: " + e.getMessage());
        }
    }
}
