package com.mycompany.mvptrabalho.repositorio;

import com.mycompany.mvptrabalho.model.HistoricoPreco;
import com.mycompany.mvptrabalho.model.Produto;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HistoricoPrecoRepository {

    void adicionar(HistoricoPreco historico);

    List<HistoricoPreco> listarPorProduto(Produto produto);

    Optional<LocalDate> buscarDataUltimoCalculo();
}
