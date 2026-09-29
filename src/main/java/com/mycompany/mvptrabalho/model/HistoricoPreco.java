package com.mycompany.mvptrabalho.model;

import java.time.LocalDate;

/**
 * Registro imutável de um cálculo de preço. Guarda uma cópia do percentual
 * usado (e não a categoria), para que alterações posteriores na margem da
 * categoria não modifiquem o histórico.
 */
public class HistoricoPreco {

    private final Produto produto;
    private final LocalDate dataCalculo;
    private final Double percentualLucro;
    private final Double precoVenda;

    public HistoricoPreco(Produto produto, LocalDate dataCalculo, Double percentualLucro, Double precoVenda) {
        this.produto = produto;
        this.dataCalculo = dataCalculo;
        this.percentualLucro = percentualLucro;
        this.precoVenda = precoVenda;
    }

    public Produto getProduto() {
        return produto;
    }

    public LocalDate getDataCalculo() {
        return dataCalculo;
    }

    public Double getPercentualLucro() {
        return percentualLucro;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }
}
