package com.mycompany.mvptrabalho.servico;

import com.mycompany.mvptrabalho.model.Categoria;
import com.mycompany.mvptrabalho.repositorio.CategoriaRepository;
import com.mycompany.mvptrabalho.repositorio.ProdutoRepository;
import java.util.List;
import java.util.Optional;

public class CategoriaServico {

    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;
    private final NotificadorAlteracoes notificador;

    public CategoriaServico(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository,
            NotificadorAlteracoes notificador) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
        this.notificador = notificador;
    }

    public List<Categoria> listarTodas() {
        return categoriaRepository.listarTodas();
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.buscarPorId(id);
    }

    public Categoria incluir(String nome, Double percentualLucro) {
        validar(nome, percentualLucro, null);

        Categoria categoria = new Categoria(nome.trim(), percentualLucro);
        categoriaRepository.salvar(categoria);
        notificador.notificar();
        return categoria;
    }

    public Categoria alterar(Long id, String nome, Double percentualLucro) {
        Categoria categoria = categoriaRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada."));

        validar(nome, percentualLucro, id);

        categoria.setNome(nome.trim());
        categoria.setPercentualLucro(percentualLucro);
        categoriaRepository.salvar(categoria);
        notificador.notificar();
        return categoria;
    }

    public void excluir(Long id) throws RegraNegocioException {
        Categoria categoria = categoriaRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada."));

        if (produtoRepository.existeProdutoComCategoria(categoria)) {
            throw new RegraNegocioException("A categoria \"" + categoria.getNome()
                    + "\" não pode ser excluída, pois existem produtos associados a ela.");
        }

        categoriaRepository.remover(categoria);
        notificador.notificar();
    }

    private void validar(String nome, Double percentualLucro, Long idIgnorado) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome da categoria é obrigatório.");
        }
        if (percentualLucro == null) {
            throw new IllegalArgumentException("O percentual de lucro é obrigatório.");
        }
        if (percentualLucro < 0) {
            throw new IllegalArgumentException("O percentual de lucro deve ser maior ou igual a zero.");
        }

        Optional<Categoria> mesmoNome = categoriaRepository.buscarPorNome(nome.trim());
        if (mesmoNome.isPresent() && !mesmoNome.get().getId().equals(idIgnorado)) {
            throw new IllegalArgumentException("Já existe uma categoria com o nome \"" + nome.trim() + "\".");
        }
    }
}
