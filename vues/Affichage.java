package vues;

import Utilitaires.Outils;
import entites.ListeClients;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class Affichage extends JFrame {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable table;
    private JPanel jpanelduJtable;
    private JPanel jpannelBoutons;

    public Affichage() {
        Outils.PreparerlaPage(this, contentPane);
        getRootPane().setDefaultButton(buttonOK);

        //COLONNES COMMUNES A TOUTES LES SOCIETES
        String[] nomsColonnes = { VuesUtilitaires.RAISONSOCIALE, VuesUtilitaires.VILLE,
                VuesUtilitaires.CODEPOSTAL, VuesUtilitaires.NUMERORUE, VuesUtilitaires.RUE,
                VuesUtilitaires.COURRIEL, VuesUtilitaires.TELEPHONE};



        Object[][] donnees =  {

                    {
                        ListeClients.getListeTousClients().get(i).getRaisonSociale(),
                        ListeClients.getListeTousClients().get(i).getVille(),
                        ListeClients.getListeTousClients().get(i).getCodePostal(),
                        ListeClients.getListeTousClients().get(i).getNumeroRue(),
                        ListeClients.getListeTousClients().get(i).getRue(),
                        ListeClients.getListeTousClients().get(i).getCourriel(),
                        ListeClients.getListeTousClients().get(i).getTelephone(),
                    }

        };

        DefaultTableModel defaultTableModel = new DefaultTableModel(donnees, nomsColonnes);
        defaultTableModel.setColumnIdentifiers(nomsColonnes);
        table.setModel(defaultTableModel);
        table.setVisible(true);

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        setVisible(true);
    }


    private void onCancel() {dispose();}
}
