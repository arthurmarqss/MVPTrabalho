package com.mycompany.mvptrabalho.repositorio;

import com.mycompany.mvptrabalho.model.Categoria;
import java.util.List;
import java.util.Optional;

/**
 * Contrato de acesso às categorias. Quem usa o repositório não sabe (nem
 * precisa saber) se os dados estão em memória ou em um banco de dados.
 */
public interface CategoriaRepository {

    /** Inclui a categoria (gerando o id) ou atualiza uma já existente. */
    void salvar(Categoria categoria);

    void remover(Categoria categoria);

    Optional<Categoria> buscarPorId(Long id);

    /** Busca pelo nome exato, sem diferenciar maiúsculas de minúsculas. */
    Optional<Categoria> buscarPorNome(String nome);

    List<Categoria> listarTodas();
}
