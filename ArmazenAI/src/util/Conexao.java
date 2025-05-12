package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    // Dados do banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/ArmazenAI"; // URL do banco
    private static final String USER = "root"; // Usuário do banco
    private static final String PASSWORD = "root"; // Senha do banco

    // Metodo para estabelecer a conexão
    public static Connection getConexao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Estabelece a conexão
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao carregar o driver do MySQL.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erro ao estabelecer conexão com o banco de dados.");
            e.printStackTrace();
        }
        return null;
    }
}