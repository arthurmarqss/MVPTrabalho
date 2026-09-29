package com.mycompany.mvptrabalho.presenter;

import com.mycompany.mvptrabalho.model.HistoricoPreco;
import com.mycompany.mvptrabalho.model.Produto;
import com.mycompany.mvptrabalho.servico.NotificadorAlteracoes;
import com.mycompany.mvptrabalho.servico.Observador;
import com.mycompany.mvptrabalho.servico.ProdutoServico;
import com.mycompany.mvptrabalho.view.HistoricoPrecoView;
import java.util.ArrayList;
import java.util.List;

public class HistoricoPrecoPresenter implements Observador {

    private final HistoricoPrecoView view;
    private final ProdutoServico produtoServico;
    private final NotificadorAlteracoes notificador;
    private final Produto produto;

    public HistoricoPrecoPresenter(HistoricoPrecoView view, ProdutoServico produtoServico,
            NotificadorAlteracoes notificador, Produto produto) {
        this.view = view;
        this.produtoServico = produtoServico;
        this.notificador = notificador;
        this.produto = produto;

        view.setPresenter(this);
        notificador.inscrever(this);
        exibirDados();
        view.exibir();
    }

    public void aoClicarFechar() {
        view.fechar();
    }

    public void aoFecharTela() {
        notificador.remover(this);
    }

    @Override
    public void dadosAlterados() {
        exibirDados();
    }

    private void exibirDados() {
        view.setProduto(produto.getNome());
        view.setCategoria(produto.getCategoria().getNome());

        List<String[]> linhas = new ArrayList<>();
        for (HistoricoPreco historico : produtoServico.listarHistorico(produto)) {
            linhas.add(new String[]{
                Formatador.data(historico.getDataCalculo()),
                Formatador.decimal(historico.getPercentualLucro()),
                Formatador.moeda(historico.getPrecoVenda())
            });
        }
        view.exibirHistorico(linhas);
    }
}
