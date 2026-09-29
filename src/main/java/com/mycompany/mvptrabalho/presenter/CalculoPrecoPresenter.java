package com.mycompany.mvptrabalho.presenter;

import com.mycompany.mvptrabalho.model.Produto;
import com.mycompany.mvptrabalho.servico.CalculoPrecoServico;
import com.mycompany.mvptrabalho.servico.RegraNegocioException;
import com.mycompany.mvptrabalho.view.CalculoPrecoView;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CalculoPrecoPresenter {

    private final CalculoPrecoView view;
    private final CalculoPrecoServico calculoPrecoServico;

    public CalculoPrecoPresenter(CalculoPrecoView view, CalculoPrecoServico calculoPrecoServico) {
        this.view = view;
        this.calculoPrecoServico = calculoPrecoServico;

        view.setPresenter(this);
        view.setDataCalculo(Formatador.data(LocalDate.now()));
        atualizarInformacao();
        view.exibir();
    }

    public void aoClicarCalcular() {
        try {
            LocalDate data = Formatador.paraData(view.getDataCalculo(), "Data do cálculo");
            List<Produto> produtos = calculoPrecoServico.calcularTodos(data);

            List<String[]> linhas = new ArrayList<>();
            for (Produto produto : produtos) {
                linhas.add(new String[]{
                    produto.getNome(),
                    Formatador.moeda(produto.getPrecoCusto()),
                    produto.getCategoria().getNome(),
                    Formatador.decimal(produto.getMargemLucroAtual()),
                    Formatador.moeda(produto.getPrecoVendaAtual())
                });
            }
            view.exibirResultados(linhas);
            atualizarInformacao();
            view.exibirMensagem("Cálculo realizado com sucesso para " + produtos.size() + " produtos.");
        } catch (RegraNegocioException | IllegalArgumentException e) {
            view.exibirErro(e.getMessage());
        }
    }

    public void aoClicarFechar() {
        view.fechar();
    }

    private void atualizarInformacao() {
        String texto = "O cálculo só pode ser realizado novamente após "
                + CalculoPrecoServico.INTERVALO_MINIMO_DIAS + " dias.";
        LocalDate ultimo = calculoPrecoServico.buscarDataUltimoCalculo().orElse(null);
        if (ultimo != null) {
            texto += " Último cálculo: " + Formatador.data(ultimo) + ".";
        }
        view.setInformacao(texto);
    }
}
