package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Fornecedor;
import model.Contato;
import util.Conexao;

public class FornecedorDAO {

    public void inserir(Fornecedor fornecedor) {
        Connection conn = null;
        PreparedStatement stmtContato = null;
        PreparedStatement stmtFornecedor = null;

        try {
            conn = Conexao.getConexao();
            conn.setAutoCommit(false);

            // Inserir contato
            String sqlContato = "INSERT INTO contato (rua, numero, bairro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmtContato = conn.prepareStatement(sqlContato, Statement.RETURN_GENERATED_KEYS);
            Contato c = fornecedor.getContato();
            stmtContato.setString(1, c.getRua());
            stmtContato.setInt(2, c.getNumero());
            stmtContato.setString(3, c.getBairro());
            stmtContato.setString(4, c.getCidade());
            stmtContato.setString(5, c.getEstado());
            stmtContato.setString(6, c.getTelefone());
            stmtContato.setString(7, c.getEmail());
            stmtContato.executeUpdate();

            ResultSet rsContato = stmtContato.getGeneratedKeys();
            int contatoId = 0;
            if (rsContato.next()) {
                contatoId = rsContato.getInt(1);
            }

            // Inserir fornecedor
            String sqlFornecedor = "INSERT INTO fornecedor (razao_social, cnpj, contato_id) VALUES (?, ?, ?)";
            stmtFornecedor = conn.prepareStatement(sqlFornecedor);
            stmtFornecedor.setString(1, fornecedor.getRazaoSocial());
            stmtFornecedor.setString(2, fornecedor.getCnpj());
            stmtFornecedor.setInt(3, contatoId);
            stmtFornecedor.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (stmtContato != null) stmtContato.close();
                if (stmtFornecedor != null) stmtFornecedor.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Fornecedor> listarTodos() {
        List<Fornecedor> fornecedores = new ArrayList<>();
        String sql = "SELECT f.*, c.* FROM fornecedor f JOIN contato c ON f.contato_id = c.id";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Contato contato = new Contato(
                        rs.getString("rua"),
                        rs.getInt("numero"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email")
                );

                Fornecedor fornecedor = new Fornecedor(
                        rs.getString("razao_social"),
                        rs.getString("cnpj"),
                        contato
                );

                fornecedores.add(fornecedor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fornecedores;
    }

    public Fornecedor buscarPorId(int id) {
        Fornecedor fornecedor = null;

        String sql = "SELECT f.*, c.* FROM fornecedor f JOIN contato c ON f.contato_id = c.id WHERE f.id = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Contato contato = new Contato(
                        rs.getString("rua"),
                        rs.getInt("numero"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email")
                );

                fornecedor = new Fornecedor(
                        rs.getString("razao_social"),
                        rs.getString("cnpj"),
                        contato
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fornecedor;
    }

    public void atualizar(Fornecedor fornecedor, int idFornecedor, int idContato) {
        String sqlFornecedor = "UPDATE fornecedor SET razao_social=?, cnpj=? WHERE id=?";
        String sqlContato = "UPDATE contato SET rua=?, numero=?, bairro=?, cidade=?, estado=?, telefone=?, email=? WHERE id=?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmtFornecedor = conn.prepareStatement(sqlFornecedor);
             PreparedStatement stmtContato = conn.prepareStatement(sqlContato)) {

            stmtFornecedor.setString(1, fornecedor.getRazaoSocial());
            stmtFornecedor.setString(2, fornecedor.getCnpj());
            stmtFornecedor.setInt(3, idFornecedor);
            stmtFornecedor.executeUpdate();

            Contato c = fornecedor.getContato();
            stmtContato.setString(1, c.getRua());
            stmtContato.setInt(2, c.getNumero());
            stmtContato.setString(3, c.getBairro());
            stmtContato.setString(4, c.getCidade());
            stmtContato.setString(5, c.getEstado());
            stmtContato.setString(6, c.getTelefone());
            stmtContato.setString(7, c.getEmail());
            stmtContato.setInt(8, idContato);
            stmtContato.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(int idFornecedor) {
        Connection conn = null;

        try {
            conn = Conexao.getConexao();
            conn.setAutoCommit(false);

            // Recuperar contato_id antes de excluir
            PreparedStatement stmtGetContato = conn.prepareStatement("SELECT contato_id FROM fornecedor WHERE id=?");
            stmtGetContato.setInt(1, idFornecedor);
            ResultSet rs = stmtGetContato.executeQuery();
            int contatoId = 0;
            if (rs.next()) {
                contatoId = rs.getInt("contato_id");
            }

            // Excluir fornecedor
            PreparedStatement stmtFornecedor = conn.prepareStatement("DELETE FROM fornecedor WHERE id=?");
            stmtFornecedor.setInt(1, idFornecedor);
            stmtFornecedor.executeUpdate();

            // Excluir contato
            PreparedStatement stmtContato = conn.prepareStatement("DELETE FROM contato WHERE id=?");
            stmtContato.setInt(1, contatoId);
            stmtContato.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}