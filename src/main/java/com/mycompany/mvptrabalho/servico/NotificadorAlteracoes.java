package com.mycompany.mvptrabalho.servico;

import java.util.ArrayList;
import java.util.List;

public class NotificadorAlteracoes {

    private final List<Observador> observadores = new ArrayList<>();

    public void inscrever(Observador observador) {
        observadores.add(observador);
    }

    public void remover(Observador observador) {
        observadores.remove(observador);
    }

    public void notificar() {
        for (Observador observador : new ArrayList<>(observadores)) {
            observador.dadosAlterados();
        }
    }
}
