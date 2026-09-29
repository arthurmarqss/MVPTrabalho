package com.mycompany.mvptrabalho.servico;

import com.mycompany.mvptrabalho.model.HistoricoPreco;
import com.mycompany.mvptrabalho.model.Produto;
import com.mycompany.mvptrabalho.repositorio.HistoricoPrecoRepository;
import com.mycompany.mvptrabalho.repositorio.ProdutoRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

/**
 * Cálculo global dos preços de venda e regra do intervalo mínimo entre
 * cálculos.
 */
public class CalculoPrecoServico {

    public static final int INTERVALO_MINIMO_DIAS = 10;

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final ProdutoRepository produtoRepository;
    private final HistoricoPrecoRepository historicoRepository;
    private final NotificadorAlteracoes notificador;

    public CalculoPrecoServico(ProdutoRepository produtoRepository, HistoricoPrecoRepository historicoRepository,
            NotificadorAlteracoes notificador) {
        this.produtoRepository = produtoRepository;
        this.historicoRepository = historicoRepository;
        this.notificador = notificador;
    }

    /**
     * Preço de venda = Preço de custo x (1 + Percentual de lucro / 100),
     * arredondado para duas casas decimais.
     */
    public Double calcularPrecoVenda(Double precoCusto, Double percentualLucro) {
        return arredondar(precoCusto * (1 + percentualLucro / 100));
    }

    /**
     * Calcula o preço de todos os produtos na data informada, atualiza os
     * valores atuais de cada um e registra um histórico por produto.
     *
     * @return os produtos processados, já com os novos valores
     */
    public List<Produto> calcularTodos(LocalDate dataCalculo) throws RegraNegocioException {
        if (dataCalculo == null) {
            throw new IllegalArgumentException("A data do cálculo é obrigatória.");
        }
        validarIntervalo(dataCalculo);

        List<Produto> produtos = produtoRepository.listarTodos();
        if (produtos.isEmpty()) {
            throw new RegraNegocioException("Não há produtos cadastrados para calcular.");
        }

        for (Produto produto : produtos) {
            // Percentual vigente da categoria NA DATA do cálculo.
            Double percentual = produto.getCategoria().getPercentualLucro();
            Double precoVenda = calcularPrecoVenda(produto.getPrecoCusto(), percentual);

            produto.registrarPrecoCalculado(percentual, precoVenda);
            historicoRepository.adicionar(new HistoricoPreco(produto, dataCalculo, percentual, precoVenda));
            produtoRepository.salvar(produto);
        }

        notificador.notificar();
        return produtos;
    }

    public Optional<LocalDate> buscarDataUltimoCalculo() {
        return historicoRepository.buscarDataUltimoCalculo();
    }

    private void validarIntervalo(LocalDate dataCalculo) throws RegraNegocioException {
        Optional<LocalDate> ultimoCalculo = historicoRepository.buscarDataUltimoCalculo();
        if (ultimoCalculo.isEmpty()) {
            return;
        }

        long dias = ChronoUnit.DAYS.between(ultimoCalculo.get(), dataCalculo);
        if (dias < INTERVALO_MINIMO_DIAS) {
            LocalDate proximaData = ultimoCalculo.get().plusDays(INTERVALO_MINIMO_DIAS);
            throw new RegraNegocioException("O novo cálculo ainda não pode ser realizado. "
                    + "O último cálculo foi em " + ultimoCalculo.get().format(FORMATO_DATA)
                    + " e o próximo só é permitido a partir de " + proximaData.format(FORMATO_DATA) + ".");
        }
    }

    private Double arredondar(double valor) {
        // BigDecimal.valueOf evita erros de arredondamento do double (ex.: 1.005).
        return BigDecimal.valueOf(valor).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
