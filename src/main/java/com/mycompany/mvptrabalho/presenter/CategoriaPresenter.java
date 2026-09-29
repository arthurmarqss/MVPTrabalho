package com.mycompany.mvptrabalho.presenter;

import com.mycompany.mvptrabalho.model.Categoria;
import com.mycompany.mvptrabalho.servico.CategoriaServico;
import com.mycompany.mvptrabalho.servico.RegraNegocioException;
import com.mycompany.mvptrabalho.view.CategoriaView;
import java.util.ArrayList;
import java.util.List;

/**
 * Presenter do cadastro mestre-detalhe de categorias. Controla os três
 * modos da tela (seção 10 da especificação) e o estado dos botões.
 */
public class CategoriaPresenter {

    private enum Modo {
        VISUALIZACAO("Modo: Visualização"),
        INCLUSAO("Modo: Inclusão"),
        EDICAO("Modo: Edição");

        private final String descricao;

        Modo(String descricao) {
            this.descricao = descricao;
        }
    }

    private final CategoriaView view;
    private final CategoriaServico categoriaServico;

    // Mesma ordem das linhas da tabela.
    private List<Categoria> categorias = new ArrayList<>();
    private Categoria selecionada;
    private Modo modo;
    // Evita reagir aos eventos de seleção disparados pelo próprio Presenter.
    private boolean atualizandoTabela;

    public CategoriaPresenter(CategoriaView view, CategoriaServico categoriaServico) {
        this.view = view;
        this.categoriaServico = categoriaServico;

        view.setPresenter(this);
        carregarTabela(null);
        entrarModoVisualizacao();
        view.exibir();
    }

    public void aoSelecionarLinha() {
        if (atualizandoTabela || modo != Modo.VISUALIZACAO) {
            return;
        }
        int linha = view.getLinhaSelecionada();
        selecionada = linha >= 0 ? categorias.get(linha) : null;
        entrarModoVisualizacao();
    }

    public void aoClicarNovo() {
        modo = Modo.INCLUSAO;
        view.setNome("");
        view.setPercentual("");
        habilitarEdicao();
    }

    public void aoClicarEditar() {
        if (selecionada == null) {
            view.exibirErro("Selecione uma categoria na tabela.");
            return;
        }
        modo = Modo.EDICAO;
        habilitarEdicao();
    }

    public void aoClicarExcluir() {
        if (selecionada == null) {
            view.exibirErro("Selecione uma categoria na tabela.");
            return;
        }
        String nome = selecionada.getNome();
        if (!view.confirmar("Deseja realmente excluir a categoria \"" + nome + "\"?")) {
            return;
        }
        try {
            categoriaServico.excluir(selecionada.getId());
            view.exibirMensagem("Categoria \"" + nome + "\" excluída com sucesso!");
            carregarTabela(null);
            entrarModoVisualizacao();
        } catch (RegraNegocioException | IllegalArgumentException e) {
            view.exibirErro(e.getMessage());
        }
    }

    public void aoClicarSalvar() {
        try {
            String nome = view.getNome();
            Double percentual = Formatador.paraDouble(view.getPercentual(), "Percentual de lucro");

            Categoria salva;
            if (modo == Modo.INCLUSAO) {
                salva = categoriaServico.incluir(nome, percentual);
            } else {
                salva = categoriaServico.alterar(selecionada.getId(), nome, percentual);
            }

            view.exibirMensagem("Item salvo com sucesso!");
            carregarTabela(salva);
            entrarModoVisualizacao();
        } catch (IllegalArgumentException e) {
            // Continua no mesmo modo para o usuário corrigir os dados.
            view.exibirErro(e.getMessage());
        }
    }

    public void aoClicarCancelar() {
        entrarModoVisualizacao();
    }

    public void aoClicarFechar() {
        view.fechar();
    }

    private void entrarModoVisualizacao() {
        modo = Modo.VISUALIZACAO;
        view.setModo(modo.descricao);
        view.setCamposEditaveis(false);
        view.setTabelaHabilitada(true);

        boolean temSelecao = selecionada != null;
        view.setNome(temSelecao ? selecionada.getNome() : "");
        view.setPercentual(temSelecao ? Formatador.decimal(selecionada.getPercentualLucro()) : "");
        view.setBotoesHabilitados(true, temSelecao, temSelecao, false, false, true);
    }

    /** Inclusão e edição: só Salvar e Cancelar ficam habilitados. */
    private void habilitarEdicao() {
        view.setModo(modo.descricao);
        view.setCamposEditaveis(true);
        view.setTabelaHabilitada(false);
        view.setBotoesHabilitados(false, false, false, true, true, false);
    }

    /**
     * Recarrega a tabela e seleciona a categoria informada (ou a primeira,
     * se ela for null ou não existir mais).
     */
    private void carregarTabela(Categoria aSelecionar) {
        atualizandoTabela = true;
        try {
            categorias = categoriaServico.listarTodas();
            List<String[]> linhas = new ArrayList<>();
            for (Categoria categoria : categorias) {
                linhas.add(new String[]{categoria.getNome(), Formatador.decimal(categoria.getPercentualLucro())});
            }
            view.exibirCategorias(linhas);

            int indice = aSelecionar == null ? -1 : categorias.indexOf(aSelecionar);
            if (indice < 0 && !categorias.isEmpty()) {
                indice = 0;
            }
            selecionada = indice >= 0 ? categorias.get(indice) : null;
            view.selecionarLinha(indice);
        } finally {
            atualizandoTabela = false;
        }
    }
}
