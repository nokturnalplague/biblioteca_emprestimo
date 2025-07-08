package br.com.emprestimobiblioteca.services;

import br.com.emprestimobiblioteca.entities.*;
import br.com.emprestimobiblioteca.services.*;

import java.time.LocalDateTime;
import java.util.List;

public class TesteServices {
    public static void main(String[] args) {
        try {
            // ===== ALUNO =====
            AlunoService alunoService = new AlunoService();
            Aluno aluno = new Aluno();
            aluno.setNome("Teste Aluno");
            aluno.setMatricula(20250001);
            aluno.setCurso("Sistemas de Informação");
            aluno.setEmail("teste.aluno@exemplo.com");
            aluno.setTelefone("12345-6789");
            aluno.setDataCadastro(LocalDateTime.now());
            aluno.setAtivo(true);

            alunoService.cadastrarAluno(aluno);
            System.out.println("[ALUNO] Inserido com ID: " + aluno.getCodigo());

            List<Aluno> alunos = alunoService.listarAlunos();
            System.out.println("[ALUNO] Total cadastrados: " + alunos.size());

            Aluno alunoBuscado = alunoService.buscarPorId(aluno.getCodigo());
            System.out.println("[ALUNO] Buscado: " + alunoBuscado.getNome());

            // ===== EQUIPAMENTO =====
            EquipamentoService equipamentoService = new EquipamentoService();
            Equipamento equipamento = new Equipamento();
            equipamento.setTipo("Tablet");
            equipamento.setMarca("Samsung");
            equipamento.setModelo("Galaxy Tab S7");
            equipamento.setNumDeSerie("SN123456789");
            equipamento.setDataCadastro(LocalDateTime.now());
            equipamento.setAtivo(true);

            equipamentoService.cadastrarEquipamento(equipamento);
            System.out.println("[EQUIPAMENTO] Inserido com ID: " + equipamento.getCodigo());

            List<Equipamento> equipamentos = equipamentoService.listarEquipamentos();
            System.out.println("[EQUIPAMENTO] Total cadastrados: " + equipamentos.size());

            Equipamento equipamentoBuscado = equipamentoService.buscarPorId(equipamento.getCodigo());
            System.out.println("[EQUIPAMENTO] Buscado: " + equipamentoBuscado.getMarca() + " " + equipamentoBuscado.getModelo());

            // ===== VINCULO =====
            VinculoService vinculoService = new VinculoService();
            Vinculo vinculo = new Vinculo();
            vinculo.setCodAluno(aluno.getCodigo());
            vinculo.setCodEquipamento(equipamento.getCodigo());
            vinculo.setQtdEmprestimo(1);
            vinculo.setDataInicio(LocalDateTime.now());
            vinculo.setAtivo(true);

            vinculoService.cadastrarVinculo(vinculo);
            System.out.println("[VINCULO] Inserido com ID: " + vinculo.getCodigo());

            List<Vinculo> vinculos = vinculoService.listarVinculos();
            System.out.println("[VINCULO] Total cadastrados: " + vinculos.size());

            Vinculo vinculoBuscado = vinculoService.buscarPorId(vinculo.getCodigo());
            System.out.println("[VINCULO] Buscado: CodAluno=" + vinculoBuscado.getCodAluno() + ", CodEquipamento=" + vinculoBuscado.getCodEquipamento());

            // ===== EMPRESTIMO =====
            EmprestimoService emprestimoService = new EmprestimoService();
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setCodAluno(aluno.getCodigo());
            emprestimo.setCodEquipamento(equipamento.getCodigo());
            emprestimo.setDataEmprestimo(LocalDateTime.now());
            emprestimo.setDataDevPrevista(LocalDateTime.now().plusDays(7));
            emprestimo.setDataDevReal(null);
            emprestimo.setAtrasado(false);
            emprestimo.setAtivo(true);

            emprestimoService.cadastrarEmprestimo(emprestimo);
            System.out.println("[EMPRESTIMO] Inserido com ID: " + emprestimo.getCodigo());

            List<Emprestimo> emprestimos = emprestimoService.listarEmprestimos();
            System.out.println("[EMPRESTIMO] Total cadastrados: " + emprestimos.size());

            Emprestimo emprestimoBuscado = emprestimoService.buscarPorId(emprestimo.getCodigo());
            System.out.println("[EMPRESTIMO] Buscado: CodAluno=" + emprestimoBuscado.getCodAluno() + ", CodEquipamento=" + emprestimoBuscado.getCodEquipamento());

        } catch (Exception e) {
            System.err.println("Erro nos testes:");
            e.printStackTrace();
        }
    }
}
