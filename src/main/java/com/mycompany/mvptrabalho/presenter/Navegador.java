package com.mycompany.mvptrabalho.presenter;

import com.mycompany.mvptrabalho.model.Produto;
import com.mycompany.mvptrabalho.servico.CalculoPrecoServico;
import com.mycompany.mvptrabalho.servico.CategoriaServico;
import com.mycompany.mvptrabalho.servico.NotificadorAlteracoes;
import com.mycompany.mvptrabalho.servico.ProdutoServico;
import com.mycompany.mvptrabalho.view.FabricaTelas;

public class Navegador {

    private final FabricaTelas fabrica;
    private final CategoriaServico categoriaServico;
    private final ProdutoServico produtoServico;
    private final CalculoPrecoServico calculoPrecoServico;
    private final NotificadorAlteracoes notificador;

    public Navegador(FabricaTelas fabrica, CategoriaServico categoriaServico, ProdutoServico produtoServico,
            CalculoPrecoServico calculoPrecoServico, NotificadorAlteracoes notificador) {
        this.fabrica = fabrica;
        this.categoriaServico = categoriaServico;
        this.produtoServico = produtoServico;
        this.calculoPrecoServico = calculoPrecoServico;
        this.notificador = notificador;
    }

    public void abrirBuscaProdutos() {
        new BuscaProdutoPresenter(fabrica.criarBuscaProduto(), produtoServico, notificador, this);
    }

    public void abrirInclusaoProduto() {
        new ProdutoFormPresenter(fabrica.criarProdutoForm(), produtoServico, categoriaServico, notificador, null);
    }

    public void abrirEdicaoProduto(Produto produto) {
        new ProdutoFormPresenter(fabrica.criarProdutoForm(), produtoServico, categoriaServico, notificador, produto);
    }

    public void abrirVisualizacaoProduto(Produto produto) {
        new ProdutoVisualizacaoPresenter(fabrica.criarProdutoVisualizacao(), produtoServico, notificador, this, produto);
    }

    public void abrirHistoricoPrecos(Produto produto) {
        new HistoricoPrecoPresenter(fabrica.criarHistoricoPreco(), produtoServico, notificador, produto);
    }

    public void abrirCategorias() {
        new CategoriaPresenter(fabrica.criarCategoria(), categoriaServico);
    }

    public void abrirCalculoPrecos() {
        new CalculoPrecoPresenter(fabrica.criarCalculoPreco(), calculoPrecoServico);
    }
}
