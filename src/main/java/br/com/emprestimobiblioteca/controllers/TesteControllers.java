package br.com.emprestimobiblioteca.controllers;

import br.com.emprestimobiblioteca.entities.*;
import br.com.emprestimobiblioteca.controllers.*;
import java.time.LocalDateTime;
import java.util.List;

public class TesteControllers {
    public static void main(String[] args) {
        try {
            // === ALUNO ===
            AlunoController alunoController = new AlunoController();
            Aluno aluno = new Aluno();
            aluno.setNome("Maria Controller");
            aluno.setMatricula(123456);
            aluno.setCurso("ADS");
            aluno.setEmail("maria@exemplo.com");
            aluno.setTelefone("48999999999");
            aluno.setDataCadastro(LocalDateTime.now());
            aluno.setAtivo(true);
            alunoController.adicionarAluno(aluno);
            System.out.println("✅ Aluno inserido.");

            List<Aluno> alunos = alunoController.listarAlunos();
            System.out.println("📋 Alunos cadastrados:");
            for (Aluno a : alunos) {
                System.out.println("- " + a.getCodigo() + " | " + a.getNome());
            }

            Aluno alunoBuscado = alunoController.buscarPorId(aluno.getCodigo());
            System.out.println("🔍 Aluno buscado: " + alunoBuscado.getNome());

            alunoBuscado.setCurso("Engenharia");
            alunoController.atualizarAluno(alunoBuscado);
            System.out.println("✏️ Aluno atualizado.");

            // === EQUIPAMENTO ===
            EquipamentoController equipamentoController = new EquipamentoController();
            Equipamento equipamento = new Equipamento();
            equipamento.setTipo("Notebook");
            equipamento.setMarca("Dell");
            equipamento.setModelo("Inspiron 15");
            equipamento.setNumDeSerie("ABC123456");
            equipamento.setDataCadastro(LocalDateTime.now());
            equipamento.setAtivo(true);
            equipamentoController.adicionarEquipamento(equipamento);
            System.out.println("✅ Equipamento inserido.");

            List<Equipamento> equipamentos = equipamentoController.listarEquipamentos();
            System.out.println("📋 Equipamentos cadastrados:");
            for (Equipamento e : equipamentos) {
                System.out.println("- " + e.getCodigo() + " | " + e.getModelo());
            }

            Equipamento equipamentoBuscado = equipamentoController.buscarPorId(equipamento.getCodigo());
            System.out.println("🔍 Equipamento buscado: " + equipamentoBuscado.getModelo());

            equipamentoBuscado.setModelo("Latitude");
            equipamentoController.atualizarEquipamento(equipamentoBuscado);
            System.out.println("✏️ Equipamento atualizado.");

            // === VINCULO ===
            VinculoController vinculoController = new VinculoController();
            Vinculo vinculo = new Vinculo();
            vinculo.setCodAluno(aluno.getCodigo());
            vinculo.setCodEquipamento(equipamento.getCodigo());
            vinculo.setQtdEmprestimo(1);
            vinculo.setDataInicio(LocalDateTime.now());
            vinculo.setAtivo(true);
            vinculoController.adicionarVinculo(vinculo);
            System.out.println("✅ Vínculo inserido.");

            List<Vinculo> vinculos = vinculoController.listarVinculos();
            System.out.println("📋 Vínculos cadastrados:");
            for (Vinculo v : vinculos) {
                System.out.println("- " + v.getCodigo() + " | Aluno: " + v.getCodAluno() + ", Equip: " + v.getCodEquipamento());
            }

            Vinculo vinculoBuscado = vinculoController.buscarPorId(vinculo.getCodigo());
            System.out.println("🔍 Vínculo buscado: Aluno " + vinculoBuscado.getCodAluno());

            vinculoBuscado.setQtdEmprestimo(2);
            vinculoController.atualizarVinculo(vinculoBuscado);
            System.out.println("✏️ Vínculo atualizado.");

            // === EMPRÉSTIMO ===
            EmprestimoController emprestimoController = new EmprestimoController();
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setCodAluno(aluno.getCodigo());
            emprestimo.setCodEquipamento(equipamento.getCodigo());
            emprestimo.setDataEmprestimo(LocalDateTime.now());
            emprestimo.setDataDevPrevista(LocalDateTime.now().plusDays(7));
            emprestimo.setDataDevReal(null);
            emprestimo.setAtrasado(false);
            emprestimo.setAtivo(true);
            emprestimoController.adicionarEmprestimo(emprestimo);
            System.out.println("✅ Empréstimo inserido.");

            List<Emprestimo> emprestimos = emprestimoController.listarEmprestimos();
            System.out.println("📋 Empréstimos cadastrados:");
            for (Emprestimo e : emprestimos) {
                System.out.println("- " + e.getCodigo() + " | Aluno: " + e.getCodAluno() + ", Equip: " + e.getCodEquipamento());
            }

            Emprestimo emprestimoBuscado = emprestimoController.buscarPorId(emprestimo.getCodigo());
            System.out.println("🔍 Empréstimo buscado: " + emprestimoBuscado.getCodigo());

            emprestimoBuscado.setAtrasado(true);
            emprestimoController.atualizarEmprestimo(emprestimoBuscado);
            System.out.println("✏️ Empréstimo atualizado.");

            // === REMOÇÃO DOS DADOS (opcional) ===
            emprestimoController.deletarEmprestimo(emprestimoBuscado.getCodigo());
            vinculoController.deletarVinculo(vinculoBuscado.getCodigo());
            equipamentoController.deletarEquipamento(equipamentoBuscado.getCodigo());
            alunoController.deletarAluno(alunoBuscado.getCodigo());
            System.out.println("🗑️ Todos os dados removidos (teste limpo).");

        } catch (Exception e) {
            System.err.println("❌ Erro no teste:");
            e.printStackTrace();
        }
    }
}
