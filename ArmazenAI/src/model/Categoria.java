package  model;

public class Categoria {


    private String nomeCategoria;
    private String descricaoCategoria;

    public Categoria(String nomeCategoria, String descriçãoCategoria) {
        this.nomeCategoria = nomeCategoria;
        this.descricaoCategoria = descriçãoCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getDescricaoCategoria() {
        return descricaoCategoria;
    }

    public void setDescricaoCategoria(String descricaoCategoria) {
        this.descricaoCategoria = descricaoCategoria;
    }

    @Override
    public String toString() {
        return "Categoria{"
                + "Nome Categoria=" + nomeCategoria
                + ", Descricao Categoria=" + descricaoCategoria
                + '}';
    }
}