package com.mycompany.mvptrabalho.repositorio;

import com.mycompany.mvptrabalho.model.Categoria;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementação em memória: os dados vivem em um Map enquanto a aplicação
 * estiver aberta.
 */
public class CategoriaRepositoryMemoria implements CategoriaRepository {

    private final Map<Long, Categoria> categorias = new LinkedHashMap<>();
    private long proximoId = 1;

    @Override
    public void salvar(Categoria categoria) {
        if (categoria.getId() == null) {
            categoria.setId(proximoId++);
        }
        categorias.put(categoria.getId(), categoria);
    }

    @Override
    public void remover(Categoria categoria) {
        categorias.remove(categoria.getId());
    }

    @Override
    public Optional<Categoria> buscarPorId(Long id) {
        return Optional.ofNullable(categorias.get(id));
    }

    @Override
    public Optional<Categoria> buscarPorNome(String nome) {
        return categorias.values().stream()
                .filter(c -> c.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    @Override
    public List<Categoria> listarTodas() {
        // Devolve uma cópia para que ninguém altere a coleção interna.
        return new ArrayList<>(categorias.values());
    }
}
