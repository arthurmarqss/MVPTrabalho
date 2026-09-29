package com.mycompany.mvptrabalho.servico;

import com.mycompany.mvptrabalho.model.Categoria;
import com.mycompany.mvptrabalho.model.HistoricoPreco;
import com.mycompany.mvptrabalho.model.Produto;
import com.mycompany.mvptrabalho.repositorio.CategoriaRepository;
import com.mycompany.mvptrabalho.repositorio.HistoricoPrecoRepository;
import com.mycompany.mvptrabalho.repositorio.ProdutoRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Regras de negócio do cadastro e da consulta de produtos.
 */
public class ProdutoServico {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final HistoricoPrecoRepository historicoRepository;
    private final NotificadorAlteracoes notificador;

    public ProdutoServico(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository,
            HistoricoPrecoRepository historicoRepository, NotificadorAlteracoes notificador) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.historicoRepository = historicoRepository;
        this.notificador = notificador;
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.buscarPorId(id);
    }

    /** Texto vazio devolve todos os produtos. */
    public List<Produto> buscarPorNome(String texto) {
        if (texto == null || texto.isBlank()) {
            return produtoRepository.listarTodos();
        }
        return produtoRepository.buscarPorNome(texto.trim());
    }

    /** Texto vazio devolve todos os produtos. */
    public List<Produto> buscarPorCategoria(String texto) {
        if (texto == null || texto.isBlank()) {
            return produtoRepository.listarTodos();
        }
        return produtoRepository.buscarPorNomeCategoria(texto.trim());
    }

    public Produto incluir(String nome, Double precoCusto, Long categoriaId) {
        Categoria categoria = validar(nome, precoCusto, categoriaId);

        Produto produto = new Produto(nome.trim(), precoCusto, categoria);
        produtoRepository.salvar(produto);
        notificador.notificar();
        return produto;
    }

    public Produto alterar(Long id, String nome, Double precoCusto, Long categoriaId) {
        Produto produto = produtoRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));

        // Valida tudo antes de alterar qualquer atributo.
        Categoria categoria = validar(nome, precoCusto, categoriaId);

        produto.setNome(nome.trim());
        produto.setPrecoCusto(precoCusto);
        produto.setCategoria(categoria);
        produtoRepository.salvar(produto);
        notificador.notificar();
        return produto;
    }

    /** Histórico do produto, do cálculo mais recente para o mais antigo. */
    public List<HistoricoPreco> listarHistorico(Produto produto) {
        List<HistoricoPreco> historico = new ArrayList<>(historicoRepository.listarPorProduto(produto));
        historico.sort(Comparator.comparing(HistoricoPreco::getDataCalculo).reversed());
        return historico;
    }

    /** Valida os dados e devolve a categoria encontrada no repositório. */
    private Categoria validar(String nome, Double precoCusto, Long categoriaId) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório.");
        }
        if (precoCusto == null) {
            throw new IllegalArgumentException("O preço de custo é obrigatório.");
        }
        if (precoCusto <= 0) {
            throw new IllegalArgumentException("O preço de custo deve ser maior que zero.");
        }
        if (categoriaId == null) {
            throw new IllegalArgumentException("A categoria do produto é obrigatória.");
        }
        return categoriaRepository.buscarPorId(categoriaId)
                .orElseThrow(() -> new IllegalArgumentException("A categoria selecionada não existe mais."));
    }
}
