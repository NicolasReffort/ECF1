package vues;

import Exceptions.MonExceptionMaison;
import Utilitaires.Outils;
import entites.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Affichage extends JFrame {

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable table1;
    public Outils outils = new Outils(); // est ce une bonne idée de le mettre en variable d'instance ?

    public Affichage(Outils.TypeSociete typeSociete) {

        //PANE CHARGE
        setContentPane(contentPane);
        //AJOUTER CBX AU RESTE

        //TAILLE
        setSize(400, 200);
        setMinimumSize(new Dimension(200, 100));

        TableModel dataModel = new AbstractTableModel() {

            private final String[] entetes = { "RaisonSociale", "Ville", "COde Postal", "Rue", };


            @Override
            public int getRowCount() {
                return ListeClients.getListeTousClients().size();
            }

            @Override
            public int getColumnCount() {
                return entetes.length -1 ;
            }


            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return null;
            }
        };

        table1.setModel(dataModel);

        for (int i = 0; i<ListeClients.getListeTousClients().size(); i++ ){
            table1.setValueAt(ListeClients.getListeTousClients().get(i).getRaisonSociale(), 1,i);
        }

        for (int i = 0; i<ListeClients.getListeTousClients().size(); i++ ){
            table1.setValueAt(ListeClients.getListeTousClients().get(i).getRaisonSociale(), 1,1);
        }

        table1.setValueAt(ListeClients.getListeTousClients().get(1).getRaisonSociale(), 1,1);


        table1.setVisible(true);
        //PANE VISIBLE
        setVisible(true);
        getRootPane().setDefaultButton(buttonOK);

    }
}
