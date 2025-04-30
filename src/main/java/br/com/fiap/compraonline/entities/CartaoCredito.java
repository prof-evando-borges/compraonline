package br.com.fiap.compraonline.entities;

import br.com.fiap.compraonline.exceptions.PagamentoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class CartaoCredito extends Pagamento {

    private int idCartaoCredito;
    private String nomeImpresso;
    private String numero;
    private String validade;
    private int cvv;
    private static List<CartaoCredito> cartoesSalvos = new ArrayList<>();

    public CartaoCredito(double valor, String nomeImpresso, String numero, String validade, int cvv, int idCliente, int tipoPagamento) {
        super(valor, idCliente, tipoPagamento);
        this.idCartaoCredito = gerarIdUnico();
        this.nomeImpresso = nomeImpresso;
        this.numero = numero;
        this.validade = validade;
        this.cvv = cvv;
        this.idCliente = idCliente;
    }

    public CartaoCredito(double valor, int idCliente, int tipoPagamento) {
        super(valor, idCliente, tipoPagamento);
    }


    // SALVAR CARTÃO

    public static void adicionarCartao(CartaoCredito cartao) {
        cartoesSalvos.add(cartao);
        System.out.println("\n✅ Cartão salvo com sucesso!\n");
    }

    public static List<CartaoCredito> listarCartoesDoCliente(int idCliente) {
        List<CartaoCredito> cartoesDoCliente = new ArrayList<>();
        for (CartaoCredito cartao : cartoesSalvos) {
            if (cartao.idCliente == idCliente) {
                cartoesDoCliente.add(cartao);
            }
        }
        return cartoesDoCliente;
    }

    // MASCARA CARTÃO
    public String getNumeroMascarado() {
        if (numero.length() < 4) {
            return "****";
        }
        return "**** **** **** " + numero.substring(Math.max(0, numero.length() - 4));
    }

    // MÉTODO HERDADO

  //  @Override
    public boolean processarPagamento() throws PagamentoException {
        System.out.println("\n🔹 Processando pagamento via Cartão de Crédito...");
        if (numero.length() == 16 && cvv >= 100 && cvv <= 999) {
            status = "Aprovado";
            System.out.println("✅ Pagamento aprovado!\n");
            return true;
        }
        status = "Recusado";
        throw new PagamentoException("❌ Pagamento recusado! Verifique os dados do cartão.");
    }

    private int gerarIdUnico() {
        Random random = new Random();
        return 10000000 + random.nextInt(90000000);
    }

    public int getIdCartaoCredito() {
        return idCartaoCredito;
    }
    public void setIdCartaoCredito(int idCartaoCredito) {
        this.idCartaoCredito = idCartaoCredito;
    }
    public String getNomeImpresso() {
        return nomeImpresso;
    }
    public void setNomeImpresso(String nomeImpresso) {
        this.nomeImpresso = nomeImpresso;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getValidade() {
        return validade;
    }
    public void setValidade(String validade) {
        this.validade = validade;
    }
    public int getCvv() {
        return cvv;
    }
    public void setCvv(int cvv) {
        this.cvv = cvv;
    }




}

