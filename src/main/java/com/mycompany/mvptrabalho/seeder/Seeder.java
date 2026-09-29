package com.mycompany.mvptrabalho.seeder;

import com.mycompany.mvptrabalho.model.Categoria;
import com.mycompany.mvptrabalho.servico.CalculoPrecoServico;
import com.mycompany.mvptrabalho.servico.CategoriaServico;
import com.mycompany.mvptrabalho.servico.ProdutoServico;
import com.mycompany.mvptrabalho.servico.RegraNegocioException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Popula os repositórios em memória a cada inicialização (seção 12 da
 * especificação). Usa os serviços, e não os repositórios diretamente, para
 * que os dados iniciais passem pelas mesmas validações do sistema.
 */
public class Seeder {

    private final CategoriaServico categoriaServico;
    private final ProdutoServico produtoServico;
    private final CalculoPrecoServico calculoPrecoServico;

    private final Map<String, Categoria> categorias = new HashMap<>();

    public Seeder(CategoriaServico categoriaServico, ProdutoServico produtoServico,
            CalculoPrecoServico calculoPrecoServico) {
        this.categoriaServico = categoriaServico;
        this.produtoServico = produtoServico;
        this.calculoPrecoServico = calculoPrecoServico;
    }

    public void executar() throws RegraNegocioException {
        criarCategorias();
        criarProdutos();
        criarHistoricoInicial();
    }

    private void criarCategorias() {
        categoria("Educação", 25.0);
        categoria("Papelaria", 30.0);
        categoria("Alimentação", 22.0);
        categoria("Lazer", 35.0);
        categoria("Entretenimento", 40.0);
        categoria("Higiene", 28.0);
        categoria("Limpeza", 25.0);
    }

    private void criarProdutos() {
        produto("Livro didático", "Educação", 45.00);
        produto("Livro paradidático", "Educação", 30.00);
        produto("Mochila escolar", "Educação", 70.00);
        produto("Caderno universitário", "Papelaria", 16.00);
        produto("Lápis grafite HB", "Papelaria", 1.20);
        produto("Caneta esferográfica azul", "Papelaria", 2.20);
        produto("Borracha branca", "Papelaria", 1.00);
        produto("Apontador com depósito", "Papelaria", 3.50);
        produto("Jogo de tabuleiro", "Lazer", 55.00);
        produto("Bola recreativa", "Lazer", 40.00);
        produto("Quebra-cabeça 500 peças", "Lazer", 35.00);
        produto("Fone de ouvido", "Entretenimento", 48.00);
        produto("Caixa de som portátil", "Entretenimento", 80.00);
        produto("Revista de passatempos", "Entretenimento", 12.00);
        produto("Biscoito integral", "Alimentação", 5.50);
        produto("Suco de uva 1 L", "Alimentação", 9.00);
        produto("Barra de cereal", "Alimentação", 3.20);
        produto("Sabonete", "Higiene", 2.80);
        produto("Creme dental", "Higiene", 5.50);
        produto("Detergente líquido", "Limpeza", 2.60);
        produto("Esponja multiuso", "Limpeza", 1.70);
    }

    /**
     * Primeiro cálculo há 10 dias, com a mesma regra da tela de cálculo:
     * a tela de histórico já tem dados e um novo cálculo hoje é permitido.
     */
    private void criarHistoricoInicial() throws RegraNegocioException {
        calculoPrecoServico.calcularTodos(LocalDate.now().minusDays(CalculoPrecoServico.INTERVALO_MINIMO_DIAS));
    }

    private void categoria(String nome, Double percentual) {
        categorias.put(nome, categoriaServico.incluir(nome, percentual));
    }

    private void produto(String nome, String nomeCategoria, Double precoCusto) {
        produtoServico.incluir(nome, precoCusto, categorias.get(nomeCategoria).getId());
    }
}
