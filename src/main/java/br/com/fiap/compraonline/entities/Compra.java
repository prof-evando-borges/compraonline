package br.com.fiap.compraonline.entities;


import br.com.fiap.compraonline.exceptions.CompraException;

public class Compra {
    private int id;
    private double valor;
    private Cliente usuario;
    private CartaoCredito cartao;
    // private CompraDAO compraDAO = new CompraDAO();

    public boolean confirmarCompra(int cvv) {
        return this.cartao.getCvv() == cvv;
    }

    public void realizarCompra(Cliente usuario, CartaoCredito cartao, int cvv, double valor) throws CompraException {
        if (usuario == null) {
            throw new CompraException("Usuário não está logado.");
        }
        if (cartao == null) {
            throw new CompraException("Não há cartão salvo.");
        }
        if (cartao.getCvv() != cvv) {
            throw new CompraException("CVV inválido.");
        }

        Compra compra = new Compra(id, valor, usuario, cartao);
        // compraDAO.salvarCompraDAO(compra);
    }

    public Compra(int id, double valor, Cliente usuario, CartaoCredito cartao) {
        super();
        this.id = id;
        this.valor = valor;
        this.usuario = usuario;
        this.cartao = cartao;
    }


    public Compra() {
        super();
    }

    public int getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Cliente getUsuario() {
        return usuario;
    }

    public void setUsuario(Cliente usuario) {
        this.usuario = usuario;
    }

    public CartaoCredito getCartao() {
        return cartao;
    }

    public void setCartao(CartaoCredito cartao) {
        this.cartao = cartao;
    }
}
