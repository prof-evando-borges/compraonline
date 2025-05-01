package br.com.fiap.compraonline.entities;

import br.com.fiap.compraonline.exceptions.PagamentoException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public abstract class Pagamento {

    protected int id;
    protected Cliente cliente;
    protected int idCliente;
    protected double valor;
    protected int tipoPagamento;
    protected LocalDate data;
    protected String status;
    private List<Pagamento> pagamentos;

    public abstract boolean processarPagamento() throws PagamentoException;

    public Pagamento(double valor, int idCliente, int tipoPagamento) throws PagamentoException {
        this.id = gerarIdUnico();
        this.idCliente =idCliente;
        this.tipoPagamento = tipoPagamento;
        this.data = LocalDate.now();
        this.valor = valor;
        this.status = "Pendente";
    }

    private int gerarIdUnico() {
        Random random = new Random();
        return 10000000 + random.nextInt(90000000);
    }

    public void setId(int id) { this.id = id; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public void setValor(double valor) { this.valor = valor; }
    public void setTipoPagamento(int tipoPagamento) { this.tipoPagamento = tipoPagamento; }
    public void setData(LocalDate data) { this.data = data; }
    public void setStatus(String status) { this.status = status; }
    public void setPagamentos(List<Pagamento> pagamentos) { this.pagamentos = pagamentos; }

}

