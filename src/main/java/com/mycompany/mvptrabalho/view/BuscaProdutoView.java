package com.mycompany.mvptrabalho.view;

import com.mycompany.mvptrabalho.presenter.BuscaProdutoPresenter;
import java.util.List;

/**
 * Contrato da tela de busca de produtos.
 */
public interface BuscaProdutoView {

    void setPresenter(BuscaProdutoPresenter presenter);

    void exibir();

    void fechar();

    void setOpcoesBusca(List<String> opcoes);

    /** Índice da opção escolhida em "Busca por". */
    int getOpcaoBusca();

    String getTextoBusca();

    /** Cada linha: nome, preço de custo, categoria, margem, preço de venda. */
    void exibirProdutos(List<String[]> linhas);

    /** Índice da linha selecionada, ou -1 se nenhuma. */
    int getLinhaSelecionada();

    void setVisualizarHabilitado(boolean habilitado);

    void exibirErro(String mensagem);
}
