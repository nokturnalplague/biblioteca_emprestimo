package br.com.emprestimobiblioteca.entities;

import java.time.LocalDateTime;

public class Equipamento {
    private long codigo;
    private String tipo;
    private String marca;
    private String modelo;
    private String numDeSerie;
    private LocalDateTime dataCadastro;
    private boolean ativo;

    public Equipamento() {}

    public Equipamento(long codigo, String tipo, String marca, String modelo, String numDeSerie, LocalDateTime dataCadastro, boolean ativo) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.numDeSerie = numDeSerie;
        this.dataCadastro = dataCadastro;
        this.ativo = ativo;
    }

    public long getCodigo() { return codigo; }
    public void setCodigo(long codigo) { this.codigo = codigo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getNumDeSerie() { return numDeSerie; }
    public void setNumDeSerie(String numDeSerie) { this.numDeSerie = numDeSerie; }

    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    @Override
    public String toString() {
        return "ID: " + codigo +
                ", Tipo: " + tipo +
                ", Marca: " + marca +
                ", Modelo: " + modelo +
                ", Nº de Série: " + numDeSerie +
                ", Data de Cadastro: " + dataCadastro +
                ", Ativo: " + (ativo ? "Sim" : "Não");
    }
}
