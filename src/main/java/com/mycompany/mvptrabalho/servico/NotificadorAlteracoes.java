package com.mycompany.mvptrabalho.servico;

import java.util.ArrayList;
import java.util.List;

/**
 * Mantém a lista de observadores e os avisa quando os dados mudam. É assim
 * que, por exemplo, uma categoria nova aparece na hora na tela de produto
 * que já estava aberta.
 */
public class NotificadorAlteracoes {

    private final List<Observador> observadores = new ArrayList<>();

    public void inscrever(Observador observador) {
        observadores.add(observador);
    }

    public void remover(Observador observador) {
        observadores.remove(observador);
    }

    public void notificar() {
        // Percorre uma cópia: um observador pode se remover durante o aviso.
        for (Observador observador : new ArrayList<>(observadores)) {
            observador.dadosAlterados();
        }
    }
}
