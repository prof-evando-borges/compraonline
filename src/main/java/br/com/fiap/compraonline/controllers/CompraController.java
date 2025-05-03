package br.com.fiap.compraonline.controllers;

import br.com.fiap.compraonline.entities.Compra;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/compras")
public class CompraController {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void realizarCompra (@RequestBody Compra compra) {
        System.out.println("Compra realizada com sucesso");
    }

}