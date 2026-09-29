package com.mycompany.mvptrabalho.presenter;

import com.mycompany.mvptrabalho.model.Categoria;
import com.mycompany.mvptrabalho.model.Produto;
import com.mycompany.mvptrabalho.servico.CategoriaServico;
import com.mycompany.mvptrabalho.servico.NotificadorAlteracoes;
import com.mycompany.mvptrabalho.servico.Observador;
import com.mycompany.mvptrabalho.servico.ProdutoServico;
import com.mycompany.mvptrabalho.view.ProdutoFormView;
import java.util.ArrayList;
import java.util.List;

public class ProdutoFormPresenter implements Observador {

    private final ProdutoFormView view;
    private final ProdutoServico produtoServico;
    private final CategoriaServico categoriaServico;
    private final NotificadorAlteracoes notificador;
    private final Produto produto;

    private List<Categoria> categorias = new ArrayList<>();

    public ProdutoFormPresenter(ProdutoFormView view, ProdutoServico produtoServico,
            CategoriaServico categoriaServico, NotificadorAlteracoes notificador, Produto produto) {
        this.view = view;
        this.produtoServico = produtoServico;
        this.categoriaServico = categoriaServico;
        this.notificador = notificador;
        this.produto = produto;

        view.setPresenter(this);
        notificador.inscrever(this);
        carregarCategorias();
        if (isEdicao()) {
            preencherComProduto();
        }
        view.exibir();
    }

    public void aoClicarSalvar() {
        try {
            String nome = view.getNome();
            Double precoCusto = Formatador.paraDouble(view.getPrecoCusto(), "Preço de custo");
            Long categoriaId = getIdCategoriaSelecionada();

            if (isEdicao()) {
                produtoServico.alterar(produto.getId(), nome, precoCusto, categoriaId);
            } else {
                produtoServico.incluir(nome, precoCusto, categoriaId);
            }

            view.exibirMensagem("Item salvo com sucesso!");
            view.fechar();
        } catch (IllegalArgumentException e) {
            view.exibirErro(e.getMessage());
        }
    }

    public void aoClicarCancelar() {
        view.fechar();
    }

    public void aoFecharTela() {
        notificador.remover(this);
    }

    @Override
    public void dadosAlterados() {
        carregarCategorias();
    }

    private boolean isEdicao() {
        return produto != null;
    }

    private void preencherComProduto() {
        view.setNome(produto.getNome());
        view.setPrecoCusto(Formatador.decimal(produto.getPrecoCusto()));
        view.setIndiceCategoria(indiceDaCategoria(produto.getCategoria().getId()));
        view.setMargemLucro(Formatador.decimal(produto.getMargemLucroAtual()));
        view.setPrecoVenda(Formatador.moeda(produto.getPrecoVendaAtual()));
    }

    private void carregarCategorias() {
        Long selecionada = getIdCategoriaSelecionada();

        categorias = categoriaServico.listarTodas();
        List<String> nomes = new ArrayList<>();
        for (Categoria categoria : categorias) {
            nomes.add(categoria.getNome());
        }
        view.setCategorias(nomes);
        view.setIndiceCategoria(indiceDaCategoria(selecionada));
    }

    private Long getIdCategoriaSelecionada() {
        int indice = view.getIndiceCategoria();
        if (indice < 0 || indice >= categorias.size()) {
            return null;
        }
        return categorias.get(indice).getId();
    }

    private int indiceDaCategoria(Long id) {
        for (int i = 0; i < categorias.size(); i++) {
            if (categorias.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}
