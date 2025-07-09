package br.com.emprestimobiblioteca.views;

import br.com.emprestimobiblioteca.controllers.EquipamentoController;
import br.com.emprestimobiblioteca.entities.Equipamento;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class EquipamentoView {

    private static final EquipamentoController controller = new EquipamentoController();

    public static void menu() {
        String[] opcoes = {
                "Cadastrar Equipamento",
                "Listar Equipamentos",
                "Buscar por ID",
                "Atualizar Equipamento",
                "Deletar Equipamento",
                "Voltar"
        };

        while (true) {
            int escolha = JOptionPane.showOptionDialog(
                    null,
                    "Selecione uma opção:",
                    "Menu Equipamento",
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
            String tipo = JOptionPane.showInputDialog("Tipo:");
            String marca = JOptionPane.showInputDialog("Marca:");
            String modelo = JOptionPane.showInputDialog("Modelo:");
            String numSerie = JOptionPane.showInputDialog("Número de Série:");
            boolean ativo = JOptionPane.showConfirmDialog(null, "Equipamento ativo?") == JOptionPane.YES_OPTION;

            Equipamento equipamento = new Equipamento(0, tipo, marca, modelo, numSerie, LocalDateTime.now(), ativo);
            controller.adicionarEquipamento(equipamento);
            JOptionPane.showMessageDialog(null, "Equipamento cadastrado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar equipamento: " + e.getMessage());
        }
    }

    private static void listar() {
        try {
            List<Equipamento> equipamentos = controller.listarEquipamentos();
            StringBuilder sb = new StringBuilder("Equipamentos:\n");
            for (Equipamento eq : equipamentos) {
                sb.append(eq).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.length() > 0 ? sb.toString() : "Nenhum equipamento encontrado.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar equipamentos: " + e.getMessage());
        }
    }

    private static void buscarPorId() {
        try {
            long id = Long.parseLong(JOptionPane.showInputDialog("ID do equipamento:"));
            Equipamento equipamento = controller.buscarPorId(id);
            JOptionPane.showMessageDialog(null, equipamento != null ? equipamento.toString() : "Equipamento não encontrado.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar equipamento: " + e.getMessage());
        }
    }

    private static void atualizar() {
        try {
            long id = Long.parseLong(JOptionPane.showInputDialog("ID do equipamento a atualizar:"));
            Equipamento equipamento = controller.buscarPorId(id);
            if (equipamento == null) {
                JOptionPane.showMessageDialog(null, "Equipamento não encontrado.");
                return;
            }

            String tipo = JOptionPane.showInputDialog("Tipo:", equipamento.getTipo());
            String marca = JOptionPane.showInputDialog("Marca:", equipamento.getMarca());
            String modelo = JOptionPane.showInputDialog("Modelo:", equipamento.getModelo());
            String numSerie = JOptionPane.showInputDialog("Número de Série:", equipamento.getNumDeSerie());
            boolean ativo = JOptionPane.showConfirmDialog(null, "Ativo?") == JOptionPane.YES_OPTION;

            equipamento.setTipo(tipo);
            equipamento.setMarca(marca);
            equipamento.setModelo(modelo);
            equipamento.setNumDeSerie(numSerie);
            equipamento.setAtivo(ativo);

            controller.atualizar(equipamento);
            JOptionPane.showMessageDialog(null, "Equipamento atualizado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar equipamento: " + e.getMessage());
        }
    }

    private static void deletar() {
        try {
            long id = Long.parseLong(JOptionPane.showInputDialog("ID do equipamento a deletar:"));
            controller.deletar(id);
            JOptionPane.showMessageDialog(null, "Equipamento deletado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar equipamento: " + e.getMessage());
        }
    }
}
