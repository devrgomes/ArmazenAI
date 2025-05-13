package dao;

import model.Produto;
import model.Categoria;
import model.Fornecedor;
import util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void inserir(Produto produto, int categoriaId, int fornecedorId) {
        String sql = "INSERT INTO produto (nome_produto, categoria_produto, quantidade_disponivel, preco_unitario, categoria_id, fornecedor_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNomeProduto());
            stmt.setString(2, produto.getCategoriaProduto());
            stmt.setString(3, produto.getQuantidadeDisponivel());
            stmt.setString(4, produto.getPrecoUnitario());
            stmt.setInt(5, categoriaId);
            stmt.setInt(6, fornecedorId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();

        String sql = "SELECT p.*, c.nome_categoria, c.descricao_categoria, " +
                "f.razao_social, f.cnpj " +
                "FROM produto p " +
                "JOIN categoria c ON p.categoria_id = c.id " +
                "JOIN fornecedor f ON p.fornecedor_id = f.id";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Categoria categoria = new Categoria(
                        rs.getString("nome_categoria"),
                        rs.getString("descricao_categoria")
                );

                Fornecedor fornecedor = new Fornecedor(
                        rs.getString("razao_social"),
                        rs.getString("cnpj"),
                        null // Ignorando contato neste contexto
                );

                Produto produto = new Produto(
                        rs.getString("nome_produto"),
                        rs.getString("categoria_produto"),
                        rs.getString("quantidade_disponivel"),
                        rs.getString("preco_unitario"),
                        categoria,
                        fornecedor
                );

                produtos.add(produto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }

    public Produto buscarPorId(int id) {
        Produto produto = null;

        String sql = "SELECT p.*, c.nome_categoria, c.descricao_categoria, " +
                "f.razao_social, f.cnpj " +
                "FROM produto p " +
                "JOIN categoria c ON p.categoria_id = c.id " +
                "JOIN fornecedor f ON p.fornecedor_id = f.id " +
                "WHERE p.id = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Categoria categoria = new Categoria(
                        rs.getString("nome_categoria"),
                        rs.getString("descricao_categoria")
                );

                Fornecedor fornecedor = new Fornecedor(
                        rs.getString("razao_social"),
                        rs.getString("cnpj"),
                        null
                );

                produto = new Produto(
                        rs.getString("nome_produto"),
                        rs.getString("categoria_produto"),
                        rs.getString("quantidade_disponivel"),
                        rs.getString("preco_unitario"),
                        categoria,
                        fornecedor
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produto;
    }

    public void atualizar(Produto produto, int id, int categoriaId, int fornecedorId) {
        String sql = "UPDATE produto SET nome_produto=?, categoria_produto=?, quantidade_disponivel=?, preco_unitario=?, categoria_id=?, fornecedor_id=? WHERE id=?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNomeProduto());
            stmt.setString(2, produto.getCategoriaProduto());
            stmt.setString(3, produto.getQuantidadeDisponivel());
            stmt.setString(4, produto.getPrecoUnitario());
            stmt.setInt(5, categoriaId);
            stmt.setInt(6, fornecedorId);
            stmt.setInt(7, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM produto WHERE id=?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
