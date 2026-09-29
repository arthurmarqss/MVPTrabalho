package com.mycompany.mvptrabalho.view;

public interface FabricaTelas {

    BuscaProdutoView criarBuscaProduto();

    ProdutoFormView criarProdutoForm();

    ProdutoVisualizacaoView criarProdutoVisualizacao();

    HistoricoPrecoView criarHistoricoPreco();

    CategoriaView criarCategoria();

    CalculoPrecoView criarCalculoPreco();
}
