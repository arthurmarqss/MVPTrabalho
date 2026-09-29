package com.mycompany.mvptrabalho.repositorio;

import com.mycompany.mvptrabalho.model.HistoricoPreco;
import com.mycompany.mvptrabalho.model.Produto;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HistoricoPrecoRepositoryMemoria implements HistoricoPrecoRepository {

    private final List<HistoricoPreco> historicos = new ArrayList<>();

    @Override
    public void adicionar(HistoricoPreco historico) {
        historicos.add(historico);
    }

    @Override
    public List<HistoricoPreco> listarPorProduto(Produto produto) {
        return historicos.stream()
                .filter(h -> h.getProduto().getId().equals(produto.getId()))
                .toList();
    }

    @Override
    public Optional<LocalDate> buscarDataUltimoCalculo() {
        return historicos.stream()
                .map(HistoricoPreco::getDataCalculo)
                .max(LocalDate::compareTo);
    }
}
