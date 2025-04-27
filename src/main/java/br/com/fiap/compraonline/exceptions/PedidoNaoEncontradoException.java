package br.com.fiap.compraonline.exceptions;

public class PedidoNaoEncontradoException extends Exception {
    
    // Construtor para receber o ID do pedido não encontrado
    public PedidoNaoEncontradoException(Long id) {
        super("Pedido com ID " + id + " não encontrado!");
    }

    // Construtor para mensagens personalizadas
    public PedidoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}