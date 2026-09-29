package com.mycompany.mvptrabalho.view;

import com.mycompany.mvptrabalho.presenter.ProdutoFormPresenter;
import java.util.List;

/**
 * Contrato da tela de produto nos modos de inclusão e edição.
 */
public interface ProdutoFormView {

    void setPresenter(ProdutoFormPresenter presenter);

    void exibir();

    void fechar();

    String getNome();

    void setNome(String nome);

    String getPrecoCusto();

    void setPrecoCusto(String precoCusto);

    void setCategorias(List<String> nomes);

    /** Índice da categoria selecionada, ou -1 se nenhuma. */
    int getIndiceCategoria();

    void setIndiceCategoria(int indice);

    void setMargemLucro(String margem);

    void setPrecoVenda(String precoVenda);

    void exibirMensagem(String mensagem);

    void exibirErro(String mensagem);
}
