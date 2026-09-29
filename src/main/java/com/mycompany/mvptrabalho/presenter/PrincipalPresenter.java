package com.mycompany.mvptrabalho.presenter;

import com.mycompany.mvptrabalho.view.PrincipalView;

/**
 * Presenter da tela principal: repassa as opções do menu "Dados" ao
 * Navegador.
 */
public class PrincipalPresenter {

    private final PrincipalView view;
    private final Navegador navegador;

    public PrincipalPresenter(PrincipalView view, Navegador navegador) {
        this.view = view;
        this.navegador = navegador;
        view.setPresenter(this);
        view.exibir();
    }

    public void aoClicarIncluirProdutos() {
        navegador.abrirInclusaoProduto();
    }

    public void aoClicarBuscarProdutos() {
        navegador.abrirBuscaProdutos();
    }

    public void aoClicarCategorias() {
        navegador.abrirCategorias();
    }

    public void aoClicarCalcularMargem() {
        navegador.abrirCalculoPrecos();
    }
}
