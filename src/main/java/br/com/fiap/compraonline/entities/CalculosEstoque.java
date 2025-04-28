package br.com.fiap.compraonline.entities;

public class CalculosEstoque {

    public double calculoPrecoTotal(int quant, double preco) {

        double calculo = quant * preco;

        return calculo;
    }
}
