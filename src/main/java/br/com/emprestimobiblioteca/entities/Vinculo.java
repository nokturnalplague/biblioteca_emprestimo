package br.com.emprestimobiblioteca.entities;

import java.time.LocalDateTime;

public class Vinculo {
    private long codigo;
    private long codAluno;
    private long codEquipamento;
    private int qtdEmprestimo;
    private LocalDateTime dataInicio;
    private boolean ativo;
    private LocalDateTime dataVinculo;


    public Vinculo() {}

    public Vinculo(long codigo, long codAluno, long codEquipamento, LocalDateTime dataVinculo, boolean ativo) {
        this.codigo = codigo;
        this.codAluno = codAluno;
        this.codEquipamento = codEquipamento;
        this.dataVinculo = dataVinculo;
        this.ativo = ativo;
    }

    public long getCodigo() { return codigo; }
    public void setCodigo(long codigo) { this.codigo = codigo; }

    public long getCodAluno() { return codAluno; }
    public void setCodAluno(long codAluno) { this.codAluno = codAluno; }

    public long getCodEquipamento() { return codEquipamento; }
    public void setCodEquipamento(long codEquipamento) { this.codEquipamento = codEquipamento; }

    public int getQtdEmprestimo() { return qtdEmprestimo; }
    public void setQtdEmprestimo(int qtdEmprestimo) { this.qtdEmprestimo = qtdEmprestimo; }

    public LocalDateTime getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDateTime dataInicio) { this.dataInicio = dataInicio; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    @Override
    public String toString() {
        return "ID: " + codigo +
                ", Aluno ID: " + codAluno +
                ", Equipamento ID: " + codEquipamento +
                (dataVinculo != null ? ", Data do Vínculo: " + dataVinculo : "") +
                ", Ativo: " + (ativo ? "Sim" : "Não");
    }

}
