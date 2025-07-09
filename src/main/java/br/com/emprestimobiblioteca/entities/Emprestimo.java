package br.com.emprestimobiblioteca.entities;

import java.time.LocalDateTime;

public class Emprestimo {
    private long codigo;
    private long codAluno;
    private long codEquipamento;
    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataDevPrevista;
    private LocalDateTime dataDevReal; // pode ser null
    private boolean atrasado;
    private boolean ativo;
    private LocalDateTime dataDevolucao;


    public Emprestimo() {}

    public Emprestimo(long codigo, long codAluno, long codEquipamento, LocalDateTime dataEmprestimo, LocalDateTime dataDevolucao, boolean ativo) {
        this.codigo = codigo;
        this.codAluno = codAluno;
        this.codEquipamento = codEquipamento;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.ativo = ativo;
    }

    public long getCodigo() { return codigo; }
    public void setCodigo(long codigo) { this.codigo = codigo; }

    public long getCodAluno() { return codAluno; }
    public void setCodAluno(long codAluno) { this.codAluno = codAluno; }

    public long getCodEquipamento() { return codEquipamento; }
    public void setCodEquipamento(long codEquipamento) { this.codEquipamento = codEquipamento; }

    public LocalDateTime getDataEmprestimo() { return dataEmprestimo; }
    public void setDataEmprestimo(LocalDateTime dataEmprestimo) { this.dataEmprestimo = dataEmprestimo; }

    public LocalDateTime getDataDevPrevista() { return dataDevPrevista; }
    public void setDataDevPrevista(LocalDateTime dataDevPrevista) { this.dataDevPrevista = dataDevPrevista; }

    public LocalDateTime getDataDevReal() { return dataDevReal; }
    public void setDataDevReal(LocalDateTime dataDevReal) { this.dataDevReal = dataDevReal; }

    public boolean isAtrasado() { return atrasado; }
    public void setAtrasado(boolean atrasado) { this.atrasado = atrasado; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public LocalDateTime getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDateTime dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public String toString() {
        return "ID: " + codigo +
                ", Aluno ID: " + codAluno +
                ", Equipamento ID: " + codEquipamento +
                ", Data Empréstimo: " + dataEmprestimo +
                ", Data Devolução: " + (dataDevolucao != null ? dataDevolucao : "Ainda não devolvido") +
                ", Ativo: " + (ativo ? "Sim" : "Não");
    }


}
