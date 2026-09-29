package com.mycompany.mvptrabalho.servico;

/**
 * Padrão Observer: quem implementa esta interface é avisado sempre que os
 * dados do sistema forem alterados (inclusão, edição, exclusão ou cálculo).
 */
public interface Observador {

    void dadosAlterados();
}
