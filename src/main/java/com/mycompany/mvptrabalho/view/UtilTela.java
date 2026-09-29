package com.mycompany.mvptrabalho.view;

import java.awt.Component;
import java.beans.PropertyVetoException;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public final class UtilTela {

    private UtilTela() {
    }

    public static void exibirCentralizada(JInternalFrame tela) {
        JDesktopPane area = tela.getDesktopPane();
        if (area != null) {
            int x = Math.max(0, (area.getWidth() - tela.getWidth()) / 2);
            int y = Math.max(0, (area.getHeight() - tela.getHeight()) / 2);
            tela.setLocation(x, y);
        }
        tela.setVisible(true);
        tela.toFront();
        try {
            tela.setSelected(true);
        } catch (PropertyVetoException e) {
            System.err.println("Não foi possível selecionar a janela: " + e.getMessage());
        }
    }

    public static void definirEditavel(JTextField campo, boolean editavel) {
        campo.setEditable(editavel);
        campo.setBackground(UIManager.getColor(editavel ? "TextField.background" : "Panel.background"));
    }

    public static void configurarTabela(JTable tabela, int... colunasADireita) {
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getTableHeader().setReorderingAllowed(false);
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        for (int coluna : colunasADireita) {
            tabela.getColumnModel().getColumn(coluna).setCellRenderer(direita);
        }
    }

    public static void preencherTabela(JTable tabela, List<String[]> linhas) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);
        for (String[] linha : linhas) {
            modelo.addRow(linha);
        }
    }

    public static void mensagem(Component origem, String mensagem) {
        JOptionPane.showMessageDialog(origem, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void erro(Component origem, String mensagem) {
        JOptionPane.showMessageDialog(origem, mensagem, "Atenção", JOptionPane.WARNING_MESSAGE);
    }

    public static boolean confirmar(Component origem, String mensagem) {
        Object[] opcoes = {"Sim", "Não"};
        int resposta = JOptionPane.showOptionDialog(origem, mensagem, "Confirmação de exclusão",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[1]);
        return resposta == 0;
    }
}
