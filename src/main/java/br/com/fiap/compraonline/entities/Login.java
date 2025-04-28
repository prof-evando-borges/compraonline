package br.com.fiap.compraonline.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static final String USER = "rm553117";
    private static final String PASSWORD = "160102";

    private static final String LOGIN = "SELECT nome_usuario FROM USUARIO WHERE email_usuario = ? AND senha_usuario = ?";
    private static final String SELECT_SENHA = "SELECT senha_usuario FROM USUARIO WHERE email_usuario = ?";

    private Connection conectar() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void fecharConexao(Connection conexao) {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean autenticar(String email, String senha) {
        Connection conexao = conectar();
        if (conexao == null) {
            System.out.println("Erro na conexão. Login abortado.");
            return false;
        }

        try (PreparedStatement stmt = conexao.prepareStatement(LOGIN)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login bem-sucedido! Bem-vindo, " + rs.getString("nome_usuario") + "!");
                return true;
            } else {
                System.out.println("Credenciais inválidas!");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            fecharConexao(conexao);
        }
    }

    public boolean verificarSenha(String email, String senha) {
        Connection conexao = conectar();
        if (conexao == null) {
            System.out.println("Erro na conexão. Verificação abortada.");
            return false;
        }

        try (PreparedStatement stmt = conexao.prepareStatement(SELECT_SENHA)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String senhaBanco = rs.getString("senha_usuario");
                return senhaBanco.equals(senha);
            } else {
                System.out.println("Usuário não encontrado.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            fecharConexao(conexao);
        }
    }
}