package br.com.fiap.compraonline.controllers;

import br.com.fiap.compraonline.entities.Produto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/produtos")
public class ProdutoController {


    // -> GET-> http://localhost:8080/v1/produtos
    // -> GET -> http://localhost:8080/v1/produtos/{id}

    @GetMapping("/{id}")
    public Produto obterPorId(@PathVariable int id) {
        return new Produto("Teste", "Teste", "Teste", 123, 1234, 456);
    }
}
