package br.com.fiap.compraonline.entities;

import br.com.fiap.compraonline.exceptions.PagamentoException;
import java.util.Random;

public class Boleto extends Pagamento {

    private String codigoBarras;

    public Boleto(double valor, int idCliente) throws PagamentoException {
        super(valor, idCliente, 3);
    }

    @Override
    public boolean processarPagamento() throws PagamentoException {

        if (valor <= 0) {
            throw new PagamentoException("❌ O valor do pagamento está inválido!");
        } else {

            System.out.println("🔹 Gerando boleto...");

            codigoBarras = gerarCodigoBoleto();

            System.out.println("\n✅ Boleto gerado com sucesso! Código: " + codigoBarras);
            System.out.println("⏳ Aguardando pagamento...\n");

            status = "Aguardando Pagamento";
            return true;
        }

    }

    // MÉTODO QUE GERA O CÓDIGO DE BARRAS COM 44 DÍGITOS ALEATÓRIOS
    private String gerarCodigoBoleto() {
        Random random = new Random();
        StringBuilder codigo = new StringBuilder();
        for (int i = 0; i < 44; i++) {
            codigo.append(random.nextInt(10));
        }
        return codigo.toString();
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }
    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }
}

