package com.mycompany.mvptrabalho.model;

public class Produto {

    private Long id;
    private String nome;
    private Double precoCusto;
    private Categoria categoria;

    private Double margemLucroAtual;
    private Double precoVendaAtual;

    public Produto(String nome, Double precoCusto, Categoria categoria) {
        this.nome = nome;
        this.precoCusto = precoCusto;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(Double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Double getMargemLucroAtual() {
        return margemLucroAtual;
    }

    public Double getPrecoVendaAtual() {
        return precoVendaAtual;
    }

    public void registrarPrecoCalculado(Double margemLucro, Double precoVenda) {
        this.margemLucroAtual = margemLucro;
        this.precoVendaAtual = precoVenda;
    }

    @Override
    public String toString() {
        return nome;
    }
}
