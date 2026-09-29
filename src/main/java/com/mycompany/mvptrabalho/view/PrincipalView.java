package com.mycompany.mvptrabalho.view;

import com.mycompany.mvptrabalho.presenter.PrincipalPresenter;

/**
 * Contrato da tela principal. O Presenter só conhece esta interface, nunca
 * a classe Swing concreta.
 */
public interface PrincipalView {

    void setPresenter(PrincipalPresenter presenter);

    void exibir();
}
