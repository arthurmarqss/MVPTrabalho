package com.mycompany.mvptrabalho.view;

import com.mycompany.mvptrabalho.presenter.CalculoPrecoPresenter;
import java.util.List;

/**
 * Contrato da tela de cálculo de margem de lucro e preço de venda.
 */
public interface CalculoPrecoView {

    void setPresenter(CalculoPrecoPresenter presenter);

    void exibir();

    void fechar();

    String getDataCalculo();

    void setDataCalculo(String data);

    void setInformacao(String informacao);

    /** Cada linha: nome, preço unitário, categoria, percentual, preço calculado. */
    void exibirResultados(List<String[]> linhas);

    void exibirMensagem(String mensagem);

    void exibirErro(String mensagem);
}
