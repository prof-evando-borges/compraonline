package br.com.fiap.compraonline.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static String user = "rm552345";
    private static String password = "150104";

    public static Connection conectar() throws SQLException {
        try {
            if (url == null || user == null || password == null) {
                throw new SQLException("Credenciais de conexão não configuradas corretamente.");
            }

            return DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {

            System.err.println("Erro ao tentar conectar com o banco de dados: " + e.getMessage());
            throw e;
        }
    }
}
