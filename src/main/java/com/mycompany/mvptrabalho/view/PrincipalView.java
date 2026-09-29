package com.mycompany.mvptrabalho.view;

import com.mycompany.mvptrabalho.presenter.PrincipalPresenter;

public interface PrincipalView {

    void setPresenter(PrincipalPresenter presenter);

    void exibir();
}
