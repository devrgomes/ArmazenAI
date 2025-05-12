package model;

public class Contato {

    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;

    private String telefone;
    private String email;

    public Contato(String rua, int numero, String bairro, String cidade, String estado, String telefone, String email) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.email = email;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contato{"
                + "Rua=" + rua
                + ", Numero=" + numero
                + ", Bairro=" + bairro
                + ", Cidade=" + cidade
                + ", Estado=" + estado
                + ", Telefone=" + telefone
                + ", Email=" + email
                + '}';
    }
}