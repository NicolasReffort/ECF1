package vues;

import Utilitaires.Outils;
import entites.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;

public class Affichage extends JFrame {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable table1;
    public Outils outils = new Outils(); // est ce une bonne idée de le mettre en variable d'instance ?

    public Affichage(String choix) {

        TableModel dataModel = new AbstractTableModel() {
        public int getColumnCount() { return 10; }
        public int getRowCount() { return 10;}
        public Object getValueAt(int row, int col) { return Integer.valueOf(row*col); }
        };
        JTable table1 = new JTable(dataModel);
        JScrollPane scrollpane = new JScrollPane(table1);

        //TAILLE
        setSize(800, 900);
        setMinimumSize(new Dimension(150, 156));
        //PANE CHARGE
        setContentPane(contentPane);
        //PANE VISIBLE
        setVisible(true);
        getRootPane().setDefaultButton(buttonOK);


    }

}