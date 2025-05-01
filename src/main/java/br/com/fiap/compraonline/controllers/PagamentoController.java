package br.com.fiap.compraonline.controllers;

import br.com.fiap.compraonline.entities.Pagamento;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


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

}
