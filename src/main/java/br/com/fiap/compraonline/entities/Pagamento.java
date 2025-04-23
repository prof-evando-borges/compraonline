package br.com.fiap.compraonline.entities;

import br.com.fiap.compraonline.exceptions.PagamentoException;

import java.time.LocalDate;
import java.util.Random;



public abstract class Pagamento {

    protected int id;
    protected int idCliente;
    protected double valor;
    protected int tipoPagamento;
    protected LocalDate data;
    protected String status;

    public Pagamento() {}

    public abstract boolean processarPagamento() throws PagamentoException;

    public Pagamento(double valor, int idCliente, int tipoPagamento) {
        this.id = gerarIdUnico();
        this.idCliente = idCliente;
        this.tipoPagamento = tipoPagamento;
        this.data = LocalDate.now();
        this.valor = valor;
        this.status = "Pendente";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(int tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private int gerarIdUnico() {
        Random random = new Random();
        return 10000000 + random.nextInt(90000000);
    }


}

