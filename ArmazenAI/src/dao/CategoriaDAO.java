    package dao;

    import model.Categoria;
    import util.Conexao;

    import java.sql.*;
    import java.util.ArrayList;
    import java.util.List;

    public class CategoriaDAO {

        public void inserir(Categoria categoria) {
            String sql = "INSERT INTO categoria (nome_categoria, descricao_categoria) VALUES (?, ?)";

            try (Connection conn = Conexao.getConexao();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, categoria.getNomeCategoria());
                stmt.setString(2, categoria.getDescricaoCategoria());
                stmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public List<Categoria> listarTodos() {
            List<Categoria> categorias = new ArrayList<>();
            String sql = "SELECT * FROM categoria";

            try (Connection conn = Conexao.getConexao();
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Categoria categoria = new Categoria(
                            rs.getString("nome_categoria"),
                            rs.getString("descricao_categoria")
                    );
                    categorias.add(categoria);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return categorias;
        }

        public Categoria buscarPorId(int id) {
            Categoria categoria = null;
            String sql = "SELECT * FROM categoria WHERE id = ?";

            try (Connection conn = Conexao.getConexao();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    categoria = new Categoria(
                            rs.getString("nome_categoria"),
                            rs.getString("descricao_categoria")
                    );
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return categoria;
        }

        public void atualizar(Categoria categoria, int id) {
            String sql = "UPDATE categoria SET nome_categoria = ?, descricao_categoria = ? WHERE id = ?";

            try (Connection conn = Conexao.getConexao();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, categoria.getNomeCategoria());
                stmt.setString(2, categoria.getDescricaoCategoria());
                stmt.setInt(3, id);
                stmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void remover(int id) {
            String sql = "DELETE FROM categoria WHERE id = ?";

            try (Connection conn = Conexao.getConexao();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id);
                stmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }