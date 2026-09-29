package com.mycompany.mvptrabalho.repositorio;

import com.mycompany.mvptrabalho.model.Categoria;
import com.mycompany.mvptrabalho.model.Produto;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementação em memória do repositório de produtos.
 */
public class ProdutoRepositoryMemoria implements ProdutoRepository {

    private final Map<Long, Produto> produtos = new LinkedHashMap<>();
    private long proximoId = 1;

    @Override
    public void salvar(Produto produto) {
        if (produto.getId() == null) {
            produto.setId(proximoId++);
        }
        produtos.put(produto.getId(), produto);
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        return Optional.ofNullable(produtos.get(id));
    }

    @Override
    public List<Produto> listarTodos() {
        return new ArrayList<>(produtos.values());
    }

    @Override
    public List<Produto> buscarPorNome(String trecho) {
        String busca = trecho.toLowerCase();
        return produtos.values().stream()
                .filter(p -> p.getNome().toLowerCase().contains(busca))
                .toList();
    }

    @Override
    public List<Produto> buscarPorNomeCategoria(String trecho) {
        String busca = trecho.toLowerCase();
        return produtos.values().stream()
                .filter(p -> p.getCategoria().getNome().toLowerCase().contains(busca))
                .toList();
    }

    @Override
    public boolean existeProdutoComCategoria(Categoria categoria) {
        return produtos.values().stream()
                .anyMatch(p -> p.getCategoria().getId().equals(categoria.getId()));
    }
}
