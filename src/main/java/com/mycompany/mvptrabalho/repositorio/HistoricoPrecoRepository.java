package com.mycompany.mvptrabalho.repositorio;

import com.mycompany.mvptrabalho.model.HistoricoPreco;
import com.mycompany.mvptrabalho.model.Produto;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Contrato de acesso aos históricos de preço. Não há operação de alteração
 * nem de exclusão: histórico só é acrescentado.
 */
public interface HistoricoPrecoRepository {

    void adicionar(HistoricoPreco historico);

    List<HistoricoPreco> listarPorProduto(Produto produto);

    /** Data do cálculo mais recente registrado, se houver algum. */
    Optional<LocalDate> buscarDataUltimoCalculo();
}
