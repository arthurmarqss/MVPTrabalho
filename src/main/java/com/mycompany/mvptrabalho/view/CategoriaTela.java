package com.mycompany.mvptrabalho.view;

import com.mycompany.mvptrabalho.presenter.CategoriaPresenter;
import java.util.List;

public class CategoriaTela extends javax.swing.JInternalFrame implements CategoriaView {

    private CategoriaPresenter presenter;

    public CategoriaTela() {
        initComponents();
        UtilTela.configurarTabela(tblCategorias, 1);
        tblCategorias.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && presenter != null) {
                presenter.aoSelecionarLinha();
            }
        });
    }

    @Override
    public void setPresenter(CategoriaPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void exibir() {
        UtilTela.exibirCentralizada(this);
    }

    @Override
    public void fechar() {
        dispose();
    }

    @Override
    public String getNome() {
        return txtNome.getText();
    }

    @Override
    public void setNome(String nome) {
        txtNome.setText(nome);
    }

    @Override
    public String getPercentual() {
        return txtPercentual.getText();
    }

    @Override
    public void setPercentual(String percentual) {
        txtPercentual.setText(percentual);
    }

    @Override
    public void setCamposEditaveis(boolean editaveis) {
        UtilTela.definirEditavel(txtNome, editaveis);
        UtilTela.definirEditavel(txtPercentual, editaveis);
        if (editaveis) {
            txtNome.requestFocusInWindow();
        }
    }

    @Override
    public void setModo(String modo) {
        lblModo.setText(modo);
    }

    @Override
    public void setBotoesHabilitados(boolean novo, boolean editar, boolean excluir,
            boolean salvar, boolean cancelar, boolean fechar) {
        btnNovo.setEnabled(novo);
        btnEditar.setEnabled(editar);
        btnExcluir.setEnabled(excluir);
        btnSalvar.setEnabled(salvar);
        btnCancelar.setEnabled(cancelar);
        btnFechar.setEnabled(fechar);
    }

    @Override
    public void exibirCategorias(List<String[]> linhas) {
        UtilTela.preencherTabela(tblCategorias, linhas);
    }

    @Override
    public void setTabelaHabilitada(boolean habilitada) {
        tblCategorias.setEnabled(habilitada);
    }

    @Override
    public int getLinhaSelecionada() {
        return tblCategorias.getSelectedRow();
    }

    @Override
    public void selecionarLinha(int indice) {
        if (indice < 0) {
            tblCategorias.clearSelection();
        } else {
            tblCategorias.setRowSelectionInterval(indice, indice);
            tblCategorias.scrollRectToVisible(tblCategorias.getCellRect(indice, 0, true));
        }
    }

    @Override
    public boolean confirmar(String mensagem) {
        return UtilTela.confirmar(this, mensagem);
    }

    @Override
    public void exibirMensagem(String mensagem) {
        UtilTela.mensagem(this, mensagem);
    }

    @Override
    public void exibirErro(String mensagem) {
        UtilTela.erro(this, mensagem);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDetalhes = new javax.swing.JPanel();
        lblModo = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblPercentual = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtPercentual = new javax.swing.JTextField();
        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        pnlCadastradas = new javax.swing.JPanel();
        scrCategorias = new javax.swing.JScrollPane();
        tblCategorias = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Categorias de Produtos");

        pnlDetalhes.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalhes da Categoria"));

        lblModo.setText("Modo: Visualização");

        lblNome.setText("Categoria:");

        lblPercentual.setText("Percentual de lucro (%):");

        txtNome.setEditable(false);

        txtPercentual.setEditable(false);

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnSalvar.setText("Salvar");
        btnSalvar.setEnabled(false);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDetalhesLayout = new javax.swing.GroupLayout(pnlDetalhes);
        pnlDetalhes.setLayout(pnlDetalhesLayout);
        pnlDetalhesLayout.setHorizontalGroup(
            pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlDetalhesLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblModo, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlDetalhesLayout.createSequentialGroup()
                            .addGroup(pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblNome)
                                .addComponent(lblPercentual))
                            .addGap(30, 30, 30)
                            .addGroup(pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPercentual, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(pnlDetalhesLayout.createSequentialGroup()
                            .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
        );
        pnlDetalhesLayout.setVerticalGroup(
            pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlDetalhesLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblModo)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNome)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPercentual)
                        .addComponent(txtPercentual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(24, 24, 24)
                    .addGroup(pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNovo)
                        .addComponent(btnEditar)
                        .addComponent(btnExcluir)
                        .addComponent(btnSalvar)
                        .addComponent(btnCancelar)
                        .addComponent(btnFechar))
                    .addContainerGap())
        );

        pnlCadastradas.setBorder(javax.swing.BorderFactory.createTitledBorder("Categorias cadastradas"));

        tblCategorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Categoria", "Percentual de lucro (%)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        scrCategorias.setViewportView(tblCategorias);

        javax.swing.GroupLayout pnlCadastradasLayout = new javax.swing.GroupLayout(pnlCadastradas);
        pnlCadastradas.setLayout(pnlCadastradasLayout);
        pnlCadastradasLayout.setHorizontalGroup(
            pnlCadastradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlCadastradasLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrCategorias, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                    .addContainerGap())
        );
        pnlCadastradasLayout.setVerticalGroup(
            pnlCadastradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlCadastradasLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrCategorias, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlDetalhes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlCadastradas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlDetalhes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(pnlCadastradas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        presenter.aoClicarNovo();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        presenter.aoClicarEditar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        presenter.aoClicarExcluir();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        presenter.aoClicarSalvar();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        presenter.aoClicarCancelar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        presenter.aoClicarFechar();
    }//GEN-LAST:event_btnFecharActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel lblModo;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblPercentual;
    private javax.swing.JPanel pnlCadastradas;
    private javax.swing.JPanel pnlDetalhes;
    private javax.swing.JScrollPane scrCategorias;
    private javax.swing.JTable tblCategorias;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPercentual;
    // End of variables declaration//GEN-END:variables
}
