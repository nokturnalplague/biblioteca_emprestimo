package br.com.emprestimobiblioteca.views;

import br.com.emprestimobiblioteca.controllers.VinculoController;
import br.com.emprestimobiblioteca.entities.Vinculo;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class VinculoView {

    private static final VinculoController controller = new VinculoController();

    public static void menu() {
        String[] opcoes = {
                "Criar Vínculo",
                "Listar Vínculos",
                "Buscar por ID",
                "Atualizar Vínculo",
                "Deletar Vínculo",
                "Voltar"
        };

        while (true) {
            int escolha = JOptionPane.showOptionDialog(
                    null,
                    "Selecione uma opção:",
                    "Menu Vínculo",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            switch (escolha) {
                case 0 -> criar();
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

    private static void criar() {
        try {
            long codAluno = Long.parseLong(JOptionPane.showInputDialog("Código do Aluno:"));
            long codEquipamento = Long.parseLong(JOptionPane.showInputDialog("Código do Equipamento:"));
            boolean ativo = JOptionPane.showConfirmDialog(null, "Vínculo ativo?") == JOptionPane.YES_OPTION;

            Vinculo vinculo = new Vinculo(0, codAluno, codEquipamento, LocalDateTime.now(), ativo);
            controller.adicionarVinculo(vinculo);
            JOptionPane.showMessageDialog(null, "Vínculo criado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar vínculo: " + e.getMessage());
        }
    }

    private static void listar() {
        try {
            List<Vinculo> vinculos = controller.listarVinculos();
            StringBuilder sb = new StringBuilder("Vínculos:\n");
            for (Vinculo v : vinculos) {
                sb.append(v).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.length() > 0 ? sb.toString() : "Nenhum vínculo encontrado.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar vínculos: " + e.getMessage());
        }
    }

    private static void buscarPorId() {
        try {
            long id = Long.parseLong(JOptionPane.showInputDialog("ID do vínculo:"));
            Vinculo v = controller.buscarPorId(id);
            JOptionPane.showMessageDialog(null, v != null ? v.toString() : "Vínculo não encontrado.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar vínculo: " + e.getMessage());
        }
    }

    private static void atualizar() {
        try {
            long id = Long.parseLong(JOptionPane.showInputDialog("ID do vínculo a atualizar:"));
            Vinculo v = controller.buscarPorId(id);
            if (v == null) {
                JOptionPane.showMessageDialog(null, "Vínculo não encontrado.");
                return;
            }

            long codAluno = Long.parseLong(JOptionPane.showInputDialog("Código do Aluno:", v.getCodAluno()));
            long codEquipamento = Long.parseLong(JOptionPane.showInputDialog("Código do Equipamento:", v.getCodEquipamento()));
            boolean ativo = JOptionPane.showConfirmDialog(null, "Vínculo ativo?") == JOptionPane.YES_OPTION;

            v.setCodAluno(codAluno);
            v.setCodEquipamento(codEquipamento);
            v.setAtivo(ativo);

            controller.atualizarVinculo(v);
            JOptionPane.showMessageDialog(null, "Vínculo atualizado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar vínculo: " + e.getMessage());
        }
    }

    private static void deletar() {
        try {
            long id = Long.parseLong(JOptionPane.showInputDialog("ID do vínculo a deletar:"));
            controller.deletarVinculo(id);
            JOptionPane.showMessageDialog(null, "Vínculo deletado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar vínculo: " + e.getMessage());
        }
    }
}
