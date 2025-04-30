package br.com.fiap.compraonline.controllers;

import br.com.fiap.compraonline.entities.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/v1/Estoque")
public class ProdutoController {

    private final Map<Integer, Produto> repositorio = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto salvarProduto(@RequestBody Produto produto){
        int id = idGenerator.getAndIncrement();
        produto.setId(id);
        repositorio.put(id, produto);
        return produto;

    }

    @GetMapping("/{id}")
    public Produto buscarProdutoPorId(@PathVariable int id){
        Produto produto = repositorio.get(id);
        if (produto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
        return produto;
    }

    @PutMapping("/{id}")
    public Produto atualizarProduto(@PathVariable int id, @RequestBody Produto produto){
        if (!repositorio.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
        produto.setId(id);
        repositorio.put(id, produto);
        return produto;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProdutoPorNumeroSerial(@PathVariable int id){
        if (!repositorio.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
        repositorio.remove(id);
    }

    @GetMapping
    public List<Produto> listaEstoque() {
        return new ArrayList<>(repositorio.values());
    }
}
