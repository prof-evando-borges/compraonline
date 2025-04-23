package br.com.fiap.compraonline.entities;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.restaurante.pagamento.dao.Conexao;
import br.com.restaurante.pagamento.dao.PagamentoDAOImpl;
import br.com.restaurante.pagamento.exception.PagamentoException;

public class Cliente {

    private int idCliente;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private List<Pagamento> pagamentos;

    public Cliente() {

    }

    public Cliente(String nome, String cpf, String email, String telefone) {
        this.idCliente = gerarIdUnico();
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.pagamentos = new ArrayList<>();
    }

    private int gerarIdUnico() {
        Random random = new Random();
        return 10000000 + random.nextInt(90000000);
    }


    public void realizarPagamento(Pagamento pagamento) throws PagamentoException  {

        if (pagamento.processarPagamento()) {
            pagamentos.add(pagamento);

            // CONECTANDO NO BANCO
            try (Connection cnn = Conexao.conectar()){

                PagamentoDAOImpl pagInsert = new PagamentoDAOImpl(cnn);
                pagInsert.salvar(pagamento);

                System.out.println("✅ Compra realizado com sucesso e salva no banco!\n");

            } catch (SQLException e) {

                throw new PagamentoException("❌ Erro ao salvar o pagamento no banco de dados.", e);

            }

        } else {
            System.out.println("❌ Erro ao processar a compra.");
        }

    }

    public void consultarPagamentos(int idCliente) throws PagamentoException {

        System.out.println("\n🔹 Pagamentos do Cliente " + nome + ":\n");
        // CONECTANDO NO BANCO
        try (Connection cnn = Conexao.conectar()){

            PagamentoDAOImpl pagSelectById = new PagamentoDAOImpl(cnn);

            List<Pagamento> pagamentosDoCliente = pagSelectById.buscarPorId(idCliente);

            if (pagamentosDoCliente.isEmpty()) {
                System.out.println("⚠️ Nenhum pagamento encontrado para o cliente " + idCliente);
            } else {
                for (Pagamento p : pagamentosDoCliente) {
                    String tipoPag = switch (p.tipoPagamento) {
                        case 1 -> "Cartão de Crédito";
                        case 2 -> "Pix";
                        case 3 -> "Boleto";
                        default -> "Desconhecido";
                    };

                    System.out.println("   ID: " + p.getId() + " | Tipo Pagamento: " + tipoPag + " | Data: " + p.getData() + " | Valor: R$ " + p.getValor() + " | Status: " + p.getStatus());
                }
            }

            System.out.println("\n✅ Consulta realizada com sucesso!\n");

        } catch (SQLException e) {
            throw new PagamentoException("❌ Erro ao consultar os pagamentos do cliente no banco de dados.", e);
        }
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }




}

