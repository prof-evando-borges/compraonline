package br.com.fiap.compraonline.exceptions;

public class PagamentoException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public PagamentoException(String mensagem) {
        super(mensagem);
    }

    public PagamentoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}

