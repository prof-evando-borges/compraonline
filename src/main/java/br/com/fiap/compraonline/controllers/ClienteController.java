package br.com.fiap.compraonline.controllers;

import br.com.fiap.compraonline.entities.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {

    @GetMapping
    public List<String> listarCliente() {

        return List.of("Evando", "Teste");
    }


}
