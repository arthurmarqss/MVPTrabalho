package com.mycompany.mvptrabalho.model;

/**
 * Produto cadastrado. A margem de lucro atual e o preço de venda atual são
 * dados calculados: só mudam pelo processo de cálculo de preços, por isso
 * não possuem setters livres.
 */
public class Produto {

    private Long id;
    private String nome;
    private Double precoCusto;
    private Categoria categoria;

    // Resultado do último cálculo. Ficam null enquanto não houver cálculo.
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

    /**
     * Registra o resultado de um cálculo de preços. É a única forma de
     * alterar a margem e o preço de venda atuais.
     */
    public void registrarPrecoCalculado(Double margemLucro, Double precoVenda) {
        this.margemLucroAtual = margemLucro;
        this.precoVendaAtual = precoVenda;
    }

    @Override
    public String toString() {
        return nome;
    }
}
