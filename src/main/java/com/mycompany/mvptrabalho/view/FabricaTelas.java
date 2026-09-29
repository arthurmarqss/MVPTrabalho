package com.mycompany.mvptrabalho.view;

/**
 * Cria as telas. Assim o Presenter pode abrir uma nova tela sem conhecer
 * nenhuma classe Swing: ele recebe apenas a interface da View.
 */
public interface FabricaTelas {

    BuscaProdutoView criarBuscaProduto();

    ProdutoFormView criarProdutoForm();

    ProdutoVisualizacaoView criarProdutoVisualizacao();

    HistoricoPrecoView criarHistoricoPreco();

    CategoriaView criarCategoria();

    CalculoPrecoView criarCalculoPreco();
}
