package br.com.fiap.compraonline.entities;

import java.util.Random;

public class Produto {
    private int id;
    private String nome;
    private String marca;
    private String desricao;
    private int numeroSerial;
    private double preco;
    private int quant;

    public Produto(String nome, String marca, String descricao, int numeroSerial, double  preco, int quant) {
        super();
        this.id = gerarIdUnico();
        this.nome = nome;
        this.marca = marca;
        this.desricao = descricao;
        this.numeroSerial = numeroSerial;
        this.preco = preco;
        this.quant = quant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id= id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDesricao() { return desricao; }

    public void setDesricao(String desricao) {
        this.desricao = desricao;
    }

    public int getNumeroSerial() {

        return numeroSerial;
    }

    public void setNumeroSerial(int numeroSerial) {

        this.numeroSerial = numeroSerial;
    }

    public double  getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    private int gerarIdUnico() {
        Random random = new Random();
        return 10000000 + random.nextInt(90000000);
    }
}
