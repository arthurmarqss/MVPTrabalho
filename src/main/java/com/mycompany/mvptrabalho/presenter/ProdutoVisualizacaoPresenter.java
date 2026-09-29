package com.mycompany.mvptrabalho.presenter;

import com.mycompany.mvptrabalho.model.Produto;
import com.mycompany.mvptrabalho.servico.NotificadorAlteracoes;
import com.mycompany.mvptrabalho.servico.Observador;
import com.mycompany.mvptrabalho.servico.ProdutoServico;
import com.mycompany.mvptrabalho.view.ProdutoVisualizacaoView;

public class ProdutoVisualizacaoPresenter implements Observador {

    private final ProdutoVisualizacaoView view;
    private final ProdutoServico produtoServico;
    private final NotificadorAlteracoes notificador;
    private final Navegador navegador;
    private final Produto produto;

    public ProdutoVisualizacaoPresenter(ProdutoVisualizacaoView view, ProdutoServico produtoServico,
            NotificadorAlteracoes notificador, Navegador navegador, Produto produto) {
        this.view = view;
        this.produtoServico = produtoServico;
        this.notificador = notificador;
        this.navegador = navegador;
        this.produto = produto;

        view.setPresenter(this);
        notificador.inscrever(this);
        exibirDados();
        view.exibir();
    }

    public void aoClicarHistorico() {
        navegador.abrirHistoricoPrecos(produto);
    }

    public void aoClicarEditar() {
        view.fechar();
        navegador.abrirEdicaoProduto(produto);
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
        Produto atual = produtoServico.buscarPorId(produto.getId()).orElse(produto);
        view.setNome(atual.getNome());
        view.setPrecoCusto(Formatador.moeda(atual.getPrecoCusto()));
        view.setCategoria(atual.getCategoria().getNome());
        view.setMargemLucro(Formatador.decimal(atual.getMargemLucroAtual()));
        view.setPrecoVenda(Formatador.moeda(atual.getPrecoVendaAtual()));
    }
}
