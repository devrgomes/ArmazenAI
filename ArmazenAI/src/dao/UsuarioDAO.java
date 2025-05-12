package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;
import model.Contato;
import model.Administrador;
import model.Operador;
import util.Conexao;

public class UsuarioDAO {

    public void inserir(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmtContato = null;
        PreparedStatement stmtUsuario = null;
        PreparedStatement stmtTipo = null;

        try {
            conn = Conexao.getConexao();
            conn.setAutoCommit(false);

            // 1. Inserir contato
            String sqlContato = "INSERT INTO contato (rua, numero, bairro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmtContato = conn.prepareStatement(sqlContato, Statement.RETURN_GENERATED_KEYS);
            Contato c = usuario.getContato();
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

            // 2. Inserir usuário
            String sqlUsuario = "INSERT INTO usuario (nome_completo, data_nascimento, cpf, matricula, senha, data_cadastro, status, contato_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            stmtUsuario = conn.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);
            stmtUsuario.setString(1, usuario.getNomeCompleto());
            stmtUsuario.setDate(2, Date.valueOf(usuario.getDataNascimento()));
            stmtUsuario.setString(3, usuario.getCpf());
            stmtUsuario.setString(4, usuario.getMatricula());
            stmtUsuario.setString(5, usuario.getSenha());
            stmtUsuario.setDate(6, Date.valueOf(usuario.getDataCadastro()));
            stmtUsuario.setBoolean(7, usuario.isStatus());
            stmtUsuario.setInt(8, contatoId);
            stmtUsuario.executeUpdate();

            ResultSet rsUsuario = stmtUsuario.getGeneratedKeys();
            int usuarioId = 0;
            if (rsUsuario.next()) {
                usuarioId = rsUsuario.getInt(1);
            }

            // 3. Inserir tipo de usuario (administrador ou operador)
            if (usuario instanceof Administrador) {
                stmtTipo = conn.prepareStatement("INSERT INTO administrador (usuario_id) VALUES (?)");
            } else if (usuario instanceof Operador) {
                stmtTipo = conn.prepareStatement("INSERT INTO operador (usuario_id) VALUES (?)");
            }

            if (stmtTipo != null) {
                stmtTipo.setInt(1, usuarioId);
                stmtTipo.executeUpdate();
            }

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
                if (stmtUsuario != null) stmtUsuario.close();
                if (stmtTipo != null) stmtTipo.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();

        String sql = "SELECT u.*, c.*, "
                + "CASE "
                + "WHEN a.usuario_id IS NOT NULL THEN 'Administrador' "
                + "WHEN o.usuario_id IS NOT NULL THEN 'Operador' "
                + "END AS tipo "
                + "FROM usuario u "
                + "JOIN contato c ON u.contato_id = c.id "
                + "LEFT JOIN administrador a ON u.id = a.usuario_id "
                + "LEFT JOIN operador o ON u.id = o.usuario_id";

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

                Usuario usuario;
                String tipo = rs.getString("tipo");

                if ("Administrador".equals(tipo)) {
                    usuario = new Administrador(
                            rs.getString("nome_completo"),
                            rs.getDate("data_nascimento").toLocalDate(),
                            rs.getString("cpf"),
                            rs.getString("matricula"),
                            rs.getString("senha"),
                            rs.getDate("data_cadastro").toLocalDate(),
                            rs.getBoolean("status"),
                            contato
                    );
                } else {
                    usuario = new Operador(
                            rs.getString("nome_completo"),
                            rs.getDate("data_nascimento").toLocalDate(),
                            rs.getString("cpf"),
                            rs.getString("matricula"),
                            rs.getString("senha"),
                            rs.getDate("data_cadastro").toLocalDate(),
                            rs.getBoolean("status"),
                            contato
                    );
                }

                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public Usuario buscarPorId(int id) {
        Usuario usuario = null;

        String sql = "SELECT u.*, c.*, "
                + "CASE "
                + "WHEN a.usuario_id IS NOT NULL THEN 'Administrador' "
                + "WHEN o.usuario_id IS NOT NULL THEN 'Operador' "
                + "END AS tipo "
                + "FROM usuario u "
                + "JOIN contato c ON u.contato_id = c.id "
                + "LEFT JOIN administrador a ON u.id = a.usuario_id "
                + "LEFT JOIN operador o ON u.id = o.usuario_id "
                + "WHERE u.id = ?";

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

                String tipo = rs.getString("tipo");

                if ("Administrador".equals(tipo)) {
                    usuario = new Administrador(
                            rs.getString("nome_completo"),
                            rs.getDate("data_nascimento").toLocalDate(),
                            rs.getString("cpf"),
                            rs.getString("matricula"),
                            rs.getString("senha"),
                            rs.getDate("data_cadastro").toLocalDate(),
                            rs.getBoolean("status"),
                            contato
                    );
                } else if ("Operador".equals(tipo)) {
                    usuario = new Operador(
                            rs.getString("nome_completo"),
                            rs.getDate("data_nascimento").toLocalDate(),
                            rs.getString("cpf"),
                            rs.getString("matricula"),
                            rs.getString("senha"),
                            rs.getDate("data_cadastro").toLocalDate(),
                            rs.getBoolean("status"),
                            contato
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    public void atualizar(Usuario usuario, int idUsuario, int idContato) {
        String sqlUsuario = "UPDATE usuario SET nome_completo=?, data_nascimento=?, cpf=?, matricula=?, senha=?, status=? WHERE id=?";
        String sqlContato = "UPDATE contato SET rua=?, numero=?, bairro=?, cidade=?, estado=?, telefone=?, email=? WHERE id=?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmtUser = conn.prepareStatement(sqlUsuario);
             PreparedStatement stmtContato = conn.prepareStatement(sqlContato)) {

            stmtUser.setString(1, usuario.getNomeCompleto());
            stmtUser.setDate(2, Date.valueOf(usuario.getDataNascimento()));
            stmtUser.setString(3, usuario.getCpf());
            stmtUser.setString(4, usuario.getMatricula());
            stmtUser.setString(5, usuario.getSenha());
            stmtUser.setBoolean(6, usuario.isStatus());
            stmtUser.setInt(7, idUsuario);
            stmtUser.executeUpdate();

            Contato c = usuario.getContato();
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

    public void remover(int idUsuario) {
        Connection conn = null;

        try {
            conn = Conexao.getConexao();
            conn.setAutoCommit(false);

            // Deleta da tabela filho primeiro
            PreparedStatement stmtAdm = conn.prepareStatement("DELETE FROM administrador WHERE usuario_id=?");
            stmtAdm.setInt(1, idUsuario);
            stmtAdm.executeUpdate();

            PreparedStatement stmtOp = conn.prepareStatement("DELETE FROM operador WHERE usuario_id=?");
            stmtOp.setInt(1, idUsuario);
            stmtOp.executeUpdate();

            // Recupera o ID do contato antes de apagar o usuário
            PreparedStatement stmtGetContato = conn.prepareStatement("SELECT contato_id FROM usuario WHERE id=?");
            stmtGetContato.setInt(1, idUsuario);
            ResultSet rs = stmtGetContato.executeQuery();
            int contatoId = 0;
            if (rs.next()) {
                contatoId = rs.getInt("contato_id");
            }

            // Apaga usuário
            PreparedStatement stmtUsuario = conn.prepareStatement("DELETE FROM usuario WHERE id=?");
            stmtUsuario.setInt(1, idUsuario);
            stmtUsuario.executeUpdate();

            // Apaga contato
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