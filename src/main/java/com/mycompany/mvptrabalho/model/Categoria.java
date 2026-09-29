package com.mycompany.mvptrabalho.model;

/**
 * Categoria de produtos. O percentual de lucro é a margem usada no cálculo
 * do preço de venda dos produtos associados a ela.
 */
public class Categoria {

    private Long id;
    private String nome;
    private Double percentualLucro;

    public Categoria(String nome, Double percentualLucro) {
        this.nome = nome;
        this.percentualLucro = percentualLucro;
    }

    public Long getId() {
        return id;
    }

    /**
     * Chamado apenas pelo repositório, no momento da inclusão, para atribuir
     * o identificador interno.
     */
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPercentualLucro() {
        return percentualLucro;
    }

    public void setPercentualLucro(Double percentualLucro) {
        this.percentualLucro = percentualLucro;
    }

    @Override
    public String toString() {
        return nome;
    }
}
