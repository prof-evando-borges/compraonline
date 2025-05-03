package br.com.fiap.compraonline.controllers;

import br.com.fiap.compraonline.entities.Pedido;
import br.com.fiap.compraonline.entities.StatusPedido;
import br.com.fiap.compraonline.exceptions.PedidoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/pedidos")
public class PedidoController {

    private List<Pedido> pedidosMock = new ArrayList<>();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido criarPedido(@RequestBody Pedido pedido) {
        System.out.println("[LOG] Pedido recebido: " + pedido);
        pedidosMock.add(pedido);
        return pedido;
    }

    @GetMapping
    public List<Pedido> listarTodos() {
        return pedidosMock;
    }

    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable Long id) {
        return pedidosMock.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new PedidoNaoEncontradoException(id));
    }

    @PutMapping("/{id}")
    public Pedido atualizarPedido(@PathVariable Long id, @RequestBody Pedido pedidoAtualizado) {
        System.out.println("[LOG] Atualizando pedido ID: " + id);
        return pedidoAtualizado;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirPedido(@PathVariable Long id) {
        System.out.println("[LOG] Excluindo pedido ID: " + id);
    }

    @GetMapping("/clientes/{clienteId}")
    public List<Pedido> listarPorCliente(@PathVariable String clienteId) {
        return pedidosMock.stream()
                .filter(p -> p.getClienteId().equals(clienteId))
                .toList();
    }

    @PatchMapping("/{id}/status")
    public Pedido atualizarStatus(@PathVariable Long id, @RequestBody StatusPedido novoStatus) {
        Pedido pedido = buscarPorId(id); 
        pedido.setStatus(novoStatus);
        return pedido;
    }
}