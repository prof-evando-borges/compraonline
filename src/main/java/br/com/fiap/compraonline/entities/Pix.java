package br.com.fiap.compraonline.entities;

import br.com.fiap.compraonline.exceptions.PagamentoException;

import java.util.UUID;


public class Pix extends Pagamento {

    private String codigoPagamento;

    public Pix(double valor, int idCliente, int tipoPagamento) {
        super(valor, idCliente, tipoPagamento);
    }

    @Override
    public boolean processarPagamento() throws PagamentoException {

        if (valor <= 0) {
            throw new PagamentoException("❌ O valor do pagamento está inválido!");

        } else {

            System.out.println("\n🔹 Processando pagamento via Pix...");

            // GERANDO CÓDIGO DO PIX
            codigoPagamento = gerarCodigoPix();

            System.out.println("\n✅ Código Pix gerado com sucesso: " + codigoPagamento);
            System.out.println("⏳ Aguardando pagamento...");

            status = "Aguardando Pagamento\n\n";
            return true;

        }

    }

    private String gerarCodigoPix() {
        return "PIX-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public String getCodigoPagamento() {
        return codigoPagamento;
    }

    public void setCodigoPagamento(String codigoPagamento) {
        this.codigoPagamento = codigoPagamento;
    }

}

