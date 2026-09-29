package com.mycompany.mvptrabalho.view;

import com.mycompany.mvptrabalho.presenter.ProdutoVisualizacaoPresenter;

/**
 * Contrato da tela de visualização de produto (somente leitura).
 */
public interface ProdutoVisualizacaoView {

    void setPresenter(ProdutoVisualizacaoPresenter presenter);

    void exibir();

    void fechar();

    void setNome(String nome);

    void setPrecoCusto(String precoCusto);

    void setCategoria(String categoria);

    void setMargemLucro(String margem);

    void setPrecoVenda(String precoVenda);
}
