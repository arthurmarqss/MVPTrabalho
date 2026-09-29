package com.mycompany.mvptrabalho.view;

import com.mycompany.mvptrabalho.presenter.HistoricoPrecoPresenter;
import java.util.List;

public interface HistoricoPrecoView {

    void setPresenter(HistoricoPrecoPresenter presenter);

    void exibir();

    void fechar();

    void setProduto(String produto);

    void setCategoria(String categoria);

    void exibirHistorico(List<String[]> linhas);
}
