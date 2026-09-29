package com.mycompany.mvptrabalho.repositorio;

import com.mycompany.mvptrabalho.model.Categoria;
import com.mycompany.mvptrabalho.model.Produto;
import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {

    void salvar(Produto produto);

    Optional<Produto> buscarPorId(Long id);

    List<Produto> listarTodos();

    List<Produto> buscarPorNome(String trecho);

    List<Produto> buscarPorNomeCategoria(String trecho);

    boolean existeProdutoComCategoria(Categoria categoria);
}
