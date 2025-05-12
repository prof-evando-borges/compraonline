package br.com.fiap.compraonline.controllers;

import br.com.fiap.compraonline.entities.Pagamento;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/pagamentos")

=======
import br.com.fiap.compraonline.exceptions.PagamentoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/pagamentos")

public class PagamentoController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public void pagar(@RequestBody Pagamento pagamento) { }

    @GetMapping("/{idCliente}")
    public Pagamento buscarPagamento(@PathVariable int idCliente) { return null; }

    @PutMapping("/{idPagamento}")
    public Pagamento atualizarPagamento(@PathVariable int idPagamento, @RequestBody Pagamento pagamento) { return null; }


    public void pagar(@RequestBody Pagamento pagamento) {
        System.out.println("Pagamento: " + pagamento);
    }

    @GetMapping
    public List<Pagamento> listarCliente() {

        Pagamento pagamento = new Pagamento(100d, 100, 100);
        List<Pagamento> pagamentos = new ArrayList<>();
        pagamentos.add(pagamento);


        return pagamentos;
    }

    @GetMapping("/{id}")
    public Pagamento buscarPorId(@PathVariable Long id) {
        return null;
    }

    @PutMapping("/{id}")
    public Pagamento atualizar(@PathVariable Long id, @RequestBody Pagamento produto) {
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {

    }

}
