package com.mycompany.mvptrabalho.view;

import com.mycompany.mvptrabalho.presenter.BuscaProdutoPresenter;
import java.util.List;

public interface BuscaProdutoView {

    void setPresenter(BuscaProdutoPresenter presenter);

    void exibir();

    void fechar();

    void setOpcoesBusca(List<String> opcoes);

    int getOpcaoBusca();

    String getTextoBusca();

    void exibirProdutos(List<String[]> linhas);

    int getLinhaSelecionada();

    void setVisualizarHabilitado(boolean habilitado);

    void exibirErro(String mensagem);
}
