package com.mycompany.mvptrabalho;

import com.mycompany.mvptrabalho.presenter.Navegador;
import com.mycompany.mvptrabalho.presenter.PrincipalPresenter;
import com.mycompany.mvptrabalho.repositorio.CategoriaRepository;
import com.mycompany.mvptrabalho.repositorio.CategoriaRepositoryMemoria;
import com.mycompany.mvptrabalho.repositorio.HistoricoPrecoRepository;
import com.mycompany.mvptrabalho.repositorio.HistoricoPrecoRepositoryMemoria;
import com.mycompany.mvptrabalho.repositorio.ProdutoRepository;
import com.mycompany.mvptrabalho.repositorio.ProdutoRepositoryMemoria;
import com.mycompany.mvptrabalho.seeder.Seeder;
import com.mycompany.mvptrabalho.servico.CalculoPrecoServico;
import com.mycompany.mvptrabalho.servico.CategoriaServico;
import com.mycompany.mvptrabalho.servico.NotificadorAlteracoes;
import com.mycompany.mvptrabalho.servico.ProdutoServico;
import com.mycompany.mvptrabalho.view.FabricaTelasSwing;
import com.mycompany.mvptrabalho.view.PrincipalTela;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Ponto de entrada: monta as camadas (repositórios, serviços), executa o
 * Seeder e só então abre a tela principal.
 */
public class MVPTrabalho {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Sem o visual do sistema operacional, o Swing usa o visual padrão.
        }

        CategoriaRepository categoriaRepository = new CategoriaRepositoryMemoria();
        ProdutoRepository produtoRepository = new ProdutoRepositoryMemoria();
        HistoricoPrecoRepository historicoRepository = new HistoricoPrecoRepositoryMemoria();
        NotificadorAlteracoes notificador = new NotificadorAlteracoes();

        CategoriaServico categoriaServico = new CategoriaServico(categoriaRepository, produtoRepository, notificador);
        ProdutoServico produtoServico = new ProdutoServico(produtoRepository, categoriaRepository,
                historicoRepository, notificador);
        CalculoPrecoServico calculoPrecoServico = new CalculoPrecoServico(produtoRepository, historicoRepository,
                notificador);

        try {
            new Seeder(categoriaServico, produtoServico, calculoPrecoServico).executar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao carregar os dados iniciais: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SwingUtilities.invokeLater(() -> {
            PrincipalTela tela = new PrincipalTela();
            Navegador navegador = new Navegador(new FabricaTelasSwing(tela.getAreaTrabalho()),
                    categoriaServico, produtoServico, calculoPrecoServico, notificador);
            new PrincipalPresenter(tela, navegador);
        });
    }
}
