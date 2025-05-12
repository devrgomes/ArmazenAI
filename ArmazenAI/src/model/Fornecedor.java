package model;

public class Fornecedor {

    private String razaoSocial;
    private String cnpj;
    private Contato contato;

    public Fornecedor(String razaoSocial, String cnpj, Contato contato) {
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.contato = contato;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    @Override
    public String toString() {
        return "Fornecedor{"
                + "Razao Social=" + razaoSocial
                + ", CNPJ=" + cnpj
                + ", Contato=" + contato.toString()
                + '}';
    }
}