package br.com.emprestimobiblioteca.views;

import javax.swing.*;

public class MenuView {

    public static void exibirMenu() {
        String[] opcoes = {
                "Gerenciar Alunos",
                "Gerenciar Equipamentos",
                "Gerenciar Empréstimos",
                "Gerenciar Vínculos",
                "Sair"
        };

        while (true) {
            int escolha = JOptionPane.showOptionDialog(
                    null,
                    "Selecione uma opção:",
                    "Sistema de Empréstimos",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            switch (escolha) {
                case 0 -> AlunoView.menu();
                case 1 -> EquipamentoView.menu();
                case 2 -> EmprestimoView.menu();
                case 3 -> VinculoView.menu();
                case 4, JOptionPane.CLOSED_OPTION -> {
                    JOptionPane.showMessageDialog(null, "Encerrando o sistema.");
                    return;
                }
            }
        }
    }
}
