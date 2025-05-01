package br.com.fiap.compraonline.controllers;

import br.com.fiap.compraonline.entities.CartaoCredito;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/CartaoCredio")

public class CartaoCreditoController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvarCartao(@RequestBody CartaoCredito cartaoCredito) { }

    @GetMapping("/{idCliente}")
    public CartaoCredito buscarCartoes(@PathVariable int idCliente) { return null; }

    @PutMapping("/{idCartao}")
    public CartaoCredito atualizarCartao(@PathVariable int idCartao, @RequestBody CartaoCredito cartaoCredito) { return null; }

    @DeleteMapping("/{idCartao}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirCartao(@PathVariable int idCartao) { }

}
