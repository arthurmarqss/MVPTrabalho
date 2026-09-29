package com.mycompany.mvptrabalho.model;

import java.time.LocalDate;

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
