package br.com.fiap.compraonline.exceptions;

public class PedidoInvalidoException extends RuntimeException {
    
    // Construtor padrão com mensagem de erro
    public PedidoInvalidoException(String mensagem) {
        super(mensagem);
    }
}