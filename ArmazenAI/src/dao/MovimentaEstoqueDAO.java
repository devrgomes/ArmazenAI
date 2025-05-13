package dao;

import model.MovimentaEstoque;
import model.MovimentaEstoque.TipoMovimentacao;
import model.Operador;
import util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovimentaEstoqueDAO {

    public void inserir(MovimentaEstoque movimentacao, int operadorId) {
        String sql = "INSERT INTO MovimentaEstoque (tipo_movimentacao, quantidade_movimentada, data_movimentacao, operador_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, movimentacao.getTipoMovimentacao().name());
            stmt.setInt(2, movimentacao.getQuantidadeMovimentada());
            stmt.setDate(3, Date.valueOf(movimentacao.getDataMovimentacao()));
            stmt.setInt(4, operadorId);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<MovimentaEstoque> listarTodos() {
        List<MovimentaEstoque> movimentacoes = new ArrayList<>();

        String sql = "SELECT m.*, u.nome_completo, u.matricula FROM MovimentaEstoque m " +
                "JOIN usuario u ON m.operador_id = u.id";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Operador operador = new Operador(
                        rs.getString("nome_completo"),
                        null, // dataNascimento
                        null, // cpf
                        rs.getString("matricula"),
                        null, // senha
                        null, // dataCadastro
                        rs.getBoolean("status"),
                        null  // contato
                );

                MovimentaEstoque mov = new MovimentaEstoque(
                        TipoMovimentacao.valueOf(rs.getString("tipo_movimentacao")),
                        rs.getInt("quantidade_movimentada"),
                        rs.getDate("data_movimentacao").toLocalDate(),
                        operador
                );

                movimentacoes.add(mov);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movimentacoes;
    }

    public MovimentaEstoque buscarPorId(int id) {
        MovimentaEstoque movimentacao = null;

        String sql = "SELECT m.*, u.nome_completo, u.matricula FROM MovimentaEstoque m " +
                "JOIN usuario u ON m.operador_id = u.id WHERE m.id = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Operador operador = new Operador(
                        rs.getString("nome_completo"),
                        null, null,
                        rs.getString("matricula"),
                        null, null,
                        rs.getBoolean("status"),
                        null
                );

                movimentacao = new MovimentaEstoque(
                        TipoMovimentacao.valueOf(rs.getString("tipo_movimentacao")),
                        rs.getInt("quantidade_movimentada"),
                        rs.getDate("data_movimentacao").toLocalDate(),
                        operador
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movimentacao;
    }
}