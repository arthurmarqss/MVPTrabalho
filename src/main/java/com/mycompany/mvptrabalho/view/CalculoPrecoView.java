package com.mycompany.mvptrabalho.view;

import com.mycompany.mvptrabalho.presenter.CalculoPrecoPresenter;
import java.util.List;

public interface CalculoPrecoView {

    void setPresenter(CalculoPrecoPresenter presenter);

    void exibir();

    void fechar();

    String getDataCalculo();

    void setDataCalculo(String data);

    void setInformacao(String informacao);

    void exibirResultados(List<String[]> linhas);

    void exibirMensagem(String mensagem);

    void exibirErro(String mensagem);
}
