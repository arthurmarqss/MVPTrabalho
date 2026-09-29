package com.mycompany.mvptrabalho.view;

import com.mycompany.mvptrabalho.presenter.CategoriaPresenter;
import java.util.List;

public interface CategoriaView {

    void setPresenter(CategoriaPresenter presenter);

    void exibir();

    void fechar();

    String getNome();

    void setNome(String nome);

    String getPercentual();

    void setPercentual(String percentual);

    void setCamposEditaveis(boolean editaveis);

    void setModo(String modo);

    void setBotoesHabilitados(boolean novo, boolean editar, boolean excluir,
            boolean salvar, boolean cancelar, boolean fechar);

    void exibirCategorias(List<String[]> linhas);

    void setTabelaHabilitada(boolean habilitada);

    int getLinhaSelecionada();

    void selecionarLinha(int indice);

    boolean confirmar(String mensagem);

    void exibirMensagem(String mensagem);

    void exibirErro(String mensagem);
}
