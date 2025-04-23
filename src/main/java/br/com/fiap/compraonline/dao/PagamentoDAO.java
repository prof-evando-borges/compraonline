package br.com.fiap.compraonline.dao;

import java.util.List;

import br.com.fiap.compraonline.entities.Pagamento;
import br.com.fiap.compraonline.exceptions.PagamentoException;

public interface PagamentoDAO {

    void salvar(Pagamento pagamento) throws PagamentoException ;
    List<Pagamento> buscarPorId(int idCliente) throws PagamentoException ;

}

