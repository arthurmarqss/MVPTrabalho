package com.mycompany.mvptrabalho.view;

import com.mycompany.mvptrabalho.presenter.PrincipalPresenter;
import javax.swing.JDesktopPane;

public class PrincipalTela extends javax.swing.JFrame implements PrincipalView {

    private PrincipalPresenter presenter;

    public PrincipalTela() {
        initComponents();
    }

    public JDesktopPane getAreaTrabalho() {
        return areaTrabalho;
    }

    @Override
    public void setPresenter(PrincipalPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void exibir() {
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        areaTrabalho = new javax.swing.JDesktopPane();
        barraMenu = new javax.swing.JMenuBar();
        menuDados = new javax.swing.JMenu();
        itemIncluirProdutos = new javax.swing.JMenuItem();
        itemBuscarProdutos = new javax.swing.JMenuItem();
        itemCategorias = new javax.swing.JMenuItem();
        itemCalcularMargem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Supermercado");

        areaTrabalho.setPreferredSize(new java.awt.Dimension(1100, 720));
        getContentPane().add(areaTrabalho, java.awt.BorderLayout.CENTER);

        menuDados.setText("Dados");

        itemIncluirProdutos.setText("Incluir produtos");
        itemIncluirProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemIncluirProdutosActionPerformed(evt);
            }
        });
        menuDados.add(itemIncluirProdutos);

        itemBuscarProdutos.setText("Buscar produtos");
        itemBuscarProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBuscarProdutosActionPerformed(evt);
            }
        });
        menuDados.add(itemBuscarProdutos);

        itemCategorias.setText("Categorias");
        itemCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCategoriasActionPerformed(evt);
            }
        });
        menuDados.add(itemCategorias);

        itemCalcularMargem.setText("Calcular margem de lucro");
        itemCalcularMargem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCalcularMargemActionPerformed(evt);
            }
        });
        menuDados.add(itemCalcularMargem);

        barraMenu.add(menuDados);

        setJMenuBar(barraMenu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemIncluirProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemIncluirProdutosActionPerformed
        presenter.aoClicarIncluirProdutos();
    }//GEN-LAST:event_itemIncluirProdutosActionPerformed

    private void itemBuscarProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBuscarProdutosActionPerformed
        presenter.aoClicarBuscarProdutos();
    }//GEN-LAST:event_itemBuscarProdutosActionPerformed

    private void itemCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCategoriasActionPerformed
        presenter.aoClicarCategorias();
    }//GEN-LAST:event_itemCategoriasActionPerformed

    private void itemCalcularMargemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCalcularMargemActionPerformed
        presenter.aoClicarCalcularMargem();
    }//GEN-LAST:event_itemCalcularMargemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane areaTrabalho;
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JMenuItem itemBuscarProdutos;
    private javax.swing.JMenuItem itemCalcularMargem;
    private javax.swing.JMenuItem itemCategorias;
    private javax.swing.JMenuItem itemIncluirProdutos;
    private javax.swing.JMenu menuDados;
    // End of variables declaration//GEN-END:variables
}
