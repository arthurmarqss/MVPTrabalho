package com.mycompany.mvptrabalho.repositorio;

import com.mycompany.mvptrabalho.model.Categoria;
import java.util.List;
import java.util.Optional;

public interface CategoriaRepository {

    void salvar(Categoria categoria);

    void remover(Categoria categoria);

    Optional<Categoria> buscarPorId(Long id);

    Optional<Categoria> buscarPorNome(String nome);

    List<Categoria> listarTodas();
}
