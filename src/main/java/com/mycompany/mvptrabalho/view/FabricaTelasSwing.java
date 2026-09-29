package com.mycompany.mvptrabalho.view;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class FabricaTelasSwing implements FabricaTelas {

    private final JDesktopPane areaTrabalho;

    public FabricaTelasSwing(JDesktopPane areaTrabalho) {
        this.areaTrabalho = areaTrabalho;
    }

    @Override
    public BuscaProdutoView criarBuscaProduto() {
        return adicionar(new BuscaProdutoTela());
    }

    @Override
    public ProdutoFormView criarProdutoForm() {
        return adicionar(new ProdutoFormTela());
    }

    @Override
    public ProdutoVisualizacaoView criarProdutoVisualizacao() {
        return adicionar(new ProdutoVisualizacaoTela());
    }

    @Override
    public HistoricoPrecoView criarHistoricoPreco() {
        return adicionar(new HistoricoPrecoTela());
    }

    @Override
    public CategoriaView criarCategoria() {
        return adicionar(new CategoriaTela());
    }

    @Override
    public CalculoPrecoView criarCalculoPreco() {
        return adicionar(new CalculoPrecoTela());
    }

    private <T extends JInternalFrame> T adicionar(T tela) {
        areaTrabalho.add(tela);
        return tela;
    }
}
