package br.com.fiap.compraonline.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {
    private Long id;
    private String clienteId;
    private final LocalDateTime data;
    private StatusPedido status;
    private final List<ItemPedido> itens = new ArrayList<>();

    // Construtor com inicialização da data
    public Pedido() {
        this.data = LocalDateTime.now(); // Data gerada automaticamente
    }

    // Método de domínio para adicionar item com validação
    public void adicionarItem(ItemPedido item) {
        if (item == null) {
            throw new IllegalArgumentException("Item não pode ser nulo!");
        }
        if (item.getPrecoUnitario().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço unitário inválido!");
        }
        this.itens.add(item);
    }

    // Método para substituir todos os itens (com validação)
    public void setItens(List<ItemPedido> novosItens) {
        this.itens.clear();
        if (novosItens == null || novosItens.isEmpty()) {
            throw new IllegalArgumentException("Lista de itens não pode ser vazia!");
        }
        novosItens.forEach(this::adicionarItem);
    }

    // Getters e Setters melhorados
    public Long getId() { return id; }
    
    public void setId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido!");
        }
        this.id = id;
    }

    public String getClienteId() { return clienteId; }
    
    public void setClienteId(String clienteId) {
        if (clienteId == null || clienteId.isBlank()) {
            throw new IllegalArgumentException("ID do cliente é obrigatório!");
        }
        this.clienteId = clienteId;
    }

    // Data não tem setter (imutável)
    public LocalDateTime getData() { return data; }

    public StatusPedido getStatus() { return status; }
    
    public void setStatus(StatusPedido status) {
        if (status == null) {
            throw new IllegalArgumentException("Status é obrigatório!");
        }
        this.status = status;
    }

    // Lista imutável para proteger a coleção
    public List<ItemPedido> getItens() {
        return Collections.unmodifiableList(itens);
    }
}