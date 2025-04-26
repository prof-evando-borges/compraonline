package br.com.fiap.compraonline.entities;

import java.math.BigDecimal;

public class ItemPedido {
    private String produtoId;
    private int quantidade;
    private BigDecimal precoUnitario;

    // Getters e Setters
    public String getProdutoId() { return produtoId; }
    public void setProdutoId(String produtoId) { this.produtoId = produtoId; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { 
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero!");
        }
        this.quantidade = quantidade; 
    }

    public BigDecimal getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(BigDecimal precoUnitario) { 
        if (precoUnitario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço unitário inválido!");
        }
        this.precoUnitario = precoUnitario; 
    }
}