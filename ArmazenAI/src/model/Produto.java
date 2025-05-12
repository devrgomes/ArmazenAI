package model;

public class Produto {

    private String nomeProduto;
    private String categoriaProduto;
    private String quantidadeDisponivel;
    private String precoUnitario;

    private Categoria categoria;
    private Fornecedor fornecedor;

    public Produto(String nomeProduto, String categoriaProduto, String quantidadeDisponivel, String precoUnitario, Categoria categoria, Fornecedor forncedor) {
        this.nomeProduto = nomeProduto;
        this.categoriaProduto = categoriaProduto;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.precoUnitario = precoUnitario;
        this.categoria = categoria;
        this.fornecedor = fornecedor;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getCategoriaProduto() {
        return categoriaProduto;
    }

    public void setCategoriaProduto(String categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }

    public String getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(String quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public String getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(String precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public String toString() {
        return "Produto{"
                + "Nome Produto=" + nomeProduto
                + ", Categoria Produto=" + categoriaProduto
                + ", Quantidade Disponivel=" + quantidadeDisponivel
                + ", Preco Unitario=" + precoUnitario
                + ", Categoria=" + categoria.toString()
                + ", Fornecedor=" + fornecedor.toString()
                + '}';
    }
}