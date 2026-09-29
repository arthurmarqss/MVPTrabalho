package com.mycompany.mvptrabalho.view;

import com.mycompany.mvptrabalho.presenter.CategoriaPresenter;
import java.util.List;

/**
 * Contrato da tela mestre-detalhe de categorias.
 */
public interface CategoriaView {

    void setPresenter(CategoriaPresenter presenter);

    void exibir();

    void fechar();

    String getNome();

    void setNome(String nome);

    String getPercentual();

    void setPercentual(String percentual);

    void setCamposEditaveis(boolean editaveis);

    /** Texto exibido no canto do formulário, ex.: "Modo: Visualização". */
    void setModo(String modo);

    void setBotoesHabilitados(boolean novo, boolean editar, boolean excluir,
            boolean salvar, boolean cancelar, boolean fechar);

    /** Cada linha: nome, percentual de lucro. */
    void exibirCategorias(List<String[]> linhas);

    void setTabelaHabilitada(boolean habilitada);

    /** Índice da linha selecionada, ou -1 se nenhuma. */
    int getLinhaSelecionada();

    void selecionarLinha(int indice);

    boolean confirmar(String mensagem);

    void exibirMensagem(String mensagem);

    void exibirErro(String mensagem);
}
