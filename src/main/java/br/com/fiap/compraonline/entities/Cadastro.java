package br.com.fiap.compraonline.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cadastro {
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static final String USER = "rm553117";
    private static final String PASSWORD = "160102";

    private static final String INSERT = "INSERT INTO USUARIO(id_usuario, nome_usuario, email_usuario, senha_usuario, telefone_usuario, documento_usuario) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM USUARIO WHERE email_usuario = ?";

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

    public void inserirCliente(Cliente cliente) {
        Connection conexao = conectar();
        if (conexao == null) {
            System.out.println("Erro na conexão. Cadastro abortado.");
            return;
        }

        try (PreparedStatement stmt = conexao.prepareStatement(INSERT)) {
            stmt.setString(1, cliente.getId());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getSenha());
            stmt.setLong(5, Long.parseLong(cliente.getTelefone().replaceAll("\\D", "")));
            stmt.setLong(6, Long.parseLong(cliente.getDocumento().replaceAll("\\D", "")));

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Cliente cadastrado com sucesso!");
            } else {
                System.out.println("Erro ao cadastrar cliente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao);
        }
    }

    public boolean deletarCliente(String email) {
        Connection conexao = conectar();
        if (conexao == null) {
            System.out.println("Erro na conexão. Exclusão abortada.");
            return false;
        }

        try (PreparedStatement stmt = conexao.prepareStatement(DELETE)) {
            stmt.setString(1, email);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Cliente deletado com sucesso!");
                return true;
            } else {
                System.out.println("Cliente não encontrado.");
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