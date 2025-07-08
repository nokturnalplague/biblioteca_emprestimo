package br.com.emprestimobiblioteca.entities;

import java.time.LocalDateTime;

public class Aluno {
    private long codigo;
    private String nome;
    private long matricula;
    private String curso;
    private String email;
    private String telefone;
    private LocalDateTime dataCadastro;
    private boolean ativo;

    public Aluno() {}

    public long getCodigo() { return codigo; }
    public void setCodigo(long codigo) { this.codigo = codigo; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public long getMatricula() { return matricula; }
    public void setMatricula(long matricula) { this.matricula = matricula; }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
