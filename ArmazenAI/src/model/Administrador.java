package model;

import java.time.LocalDate;

public class Administrador extends Usuario {

    public Administrador(String nomeCompleto, LocalDate dataNascimento, String cpf, String matricula, String senha, LocalDate dataCadastro, boolean status, Contato contato) {
        super(nomeCompleto, dataNascimento, cpf, matricula, senha, dataCadastro, status, contato);
    }
}