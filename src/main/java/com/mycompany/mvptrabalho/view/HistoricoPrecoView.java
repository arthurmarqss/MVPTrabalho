package com.mycompany.mvptrabalho.view;

import com.mycompany.mvptrabalho.presenter.HistoricoPrecoPresenter;
import java.util.List;

/**
 * Contrato da tela de histórico de preços de um produto.
 */
public interface HistoricoPrecoView {

    void setPresenter(HistoricoPrecoPresenter presenter);

    void exibir();

    void fechar();

    void setProduto(String produto);

    void setCategoria(String categoria);

    /** Cada linha: data, percentual de lucro, preço de venda. */
    void exibirHistorico(List<String[]> linhas);
}
