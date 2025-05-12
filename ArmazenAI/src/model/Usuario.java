package model;

import java.time.LocalDate;

public abstract class Usuario {

    // Atributos
    protected String nomeCompleto;
    protected LocalDate dataNascimento;
    protected String cpf;
    protected String matricula;
    protected String senha;
    protected LocalDate dataCadastro;
    protected boolean status;

    protected Contato contato;

    public Usuario(String nomeCompleto, LocalDate dataNascimento, String cpf, String matricula, String senha, LocalDate dataCadastro, boolean status, Contato contato) {
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.matricula = matricula;
        this.senha = senha;
        this.dataCadastro = LocalDate.now();
        this.status = status;
        this.contato = contato;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    @Override
    public String toString() {
        return "Usuario{"
                + "Nome Completo=" + nomeCompleto
                + ", Data de Nascimento=" + dataNascimento
                + ", CPF=" + cpf
                + ", Matricula=" + matricula
                + ", Senha=" + senha
                + ", Data de Cadastro=" + dataCadastro
                + ", Status=" + status
                + ", Contato=" + contato.toString()
                + '}';
    }
}