package com.mycompany.mvptrabalho.repositorio;

import com.mycompany.mvptrabalho.model.Categoria;
import com.mycompany.mvptrabalho.model.Produto;
import java.util.List;
import java.util.Optional;

/**
 * Contrato de acesso aos produtos.
 */
public interface ProdutoRepository {

    /** Inclui o produto (gerando o id) ou atualiza um já existente. */
    void salvar(Produto produto);

    Optional<Produto> buscarPorId(Long id);

    List<Produto> listarTodos();

    /** Produtos cujo nome contém o trecho informado. */
    List<Produto> buscarPorNome(String trecho);

    /** Produtos cujo nome da categoria contém o trecho informado. */
    List<Produto> buscarPorNomeCategoria(String trecho);

    boolean existeProdutoComCategoria(Categoria categoria);
}
