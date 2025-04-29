package br.com.fiap.compraonline.entities;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Estoque")
public class Estoque {

    ArrayList<Produto> estoque = new ArrayList<>();

    public void cadastroProduto(int quantidadeProdutos, String nome, String marca, String descricao, int numeroSerial, double preco, int quantidade) {

        for(int i= 0; i < quantidadeProdutos; i++) {

            estoque.add(new Produto(nome, marca, descricao, numeroSerial, preco, quantidade));
        }

        if(quantidadeProdutos == 1) {
            System.out.println("Cadastro do produto realizado com sucesso");
        } else if (quantidadeProdutos >= 2) {
            System.out.println("Cadastro dos produtos realizados com sucesso");
        }

    }

    @GetMapping
    public List<Produto> listaEstoque() {
        ArrayList<Produto> estoque = new ArrayList<>();

        return List.of(estoque.toArray(new Produto[0]));
    }
}
