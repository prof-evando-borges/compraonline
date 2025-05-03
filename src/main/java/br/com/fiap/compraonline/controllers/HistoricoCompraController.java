package br.com.fiap.compraonline.controllers;

import br.com.fiap.compraonline.entities.HistoricoCompra;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/historicocompras")
public class HistoricoCompraController {

    @GetMapping("/{idUsuario}")
    public HistoricoCompra visualizarCompras(@PathVariable int idUsuario) {

        return new HistoricoCompra(idUsuario);
    }
}
