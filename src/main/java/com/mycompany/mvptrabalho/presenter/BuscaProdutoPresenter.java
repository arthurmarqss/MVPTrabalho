package com.mycompany.mvptrabalho.presenter;

import com.mycompany.mvptrabalho.model.Produto;
import com.mycompany.mvptrabalho.servico.NotificadorAlteracoes;
import com.mycompany.mvptrabalho.servico.Observador;
import com.mycompany.mvptrabalho.servico.ProdutoServico;
import com.mycompany.mvptrabalho.view.BuscaProdutoView;
import java.util.ArrayList;
import java.util.List;

/**
 * Presenter da busca de produtos. Observa as alterações de dados para
 * atualizar a tabela quando um produto é salvo ou os preços são calculados.
 */
public class BuscaProdutoPresenter implements Observador {

    private static final int BUSCA_POR_CATEGORIA = 1;

    private final BuscaProdutoView view;
    private final ProdutoServico produtoServico;
    private final NotificadorAlteracoes notificador;
    private final Navegador navegador;

    // Mesma ordem das linhas da tabela: o índice da linha é o índice aqui.
    private List<Produto> produtosExibidos = new ArrayList<>();

    public BuscaProdutoPresenter(BuscaProdutoView view, ProdutoServico produtoServico,
            NotificadorAlteracoes notificador, Navegador navegador) {
        this.view = view;
        this.produtoServico = produtoServico;
        this.notificador = notificador;
        this.navegador = navegador;

        view.setPresenter(this);
        view.setOpcoesBusca(List.of("Nome do produto", "Categoria"));
        notificador.inscrever(this);
        buscar();
        view.exibir();
    }

    public void aoClicarBuscar() {
        buscar();
    }

    public void aoSelecionarLinha() {
        view.setVisualizarHabilitado(view.getLinhaSelecionada() >= 0);
    }

    public void aoClicarNovo() {
        navegador.abrirInclusaoProduto();
    }

    public void aoClicarVisualizar() {
        int linha = view.getLinhaSelecionada();
        if (linha < 0) {
            view.exibirErro("Selecione um produto na tabela.");
            return;
        }
        navegador.abrirVisualizacaoProduto(produtosExibidos.get(linha));
    }

    public void aoClicarFechar() {
        view.fechar();
    }

    /** Chamado pela View quando a janela é fechada (botão ou "X"). */
    public void aoFecharTela() {
        notificador.remover(this);
    }

    @Override
    public void dadosAlterados() {
        buscar();
    }

    private void buscar() {
        String texto = view.getTextoBusca();
        if (view.getOpcaoBusca() == BUSCA_POR_CATEGORIA) {
            produtosExibidos = produtoServico.buscarPorCategoria(texto);
        } else {
            produtosExibidos = produtoServico.buscarPorNome(texto);
        }

        List<String[]> linhas = new ArrayList<>();
        for (Produto produto : produtosExibidos) {
            linhas.add(new String[]{
                produto.getNome(),
                Formatador.moeda(produto.getPrecoCusto()),
                produto.getCategoria().getNome(),
                Formatador.decimal(produto.getMargemLucroAtual()),
                Formatador.moeda(produto.getPrecoVendaAtual())
            });
        }
        view.exibirProdutos(linhas);
        view.setVisualizarHabilitado(false);
    }
}
