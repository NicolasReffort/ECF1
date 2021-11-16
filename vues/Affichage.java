package vues;

import Utilitaires.Outils;
import entites.ListeClients;
import entites.ListeProspects;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class Affichage extends JFrame {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable table;
    private JPanel jpanelduJtable;
    private JPanel jpannelBoutons;

    private  String[] nomsColonnes;
    public void setNomsColonnes(String[] nomsColonnes) {this.nomsColonnes = nomsColonnes;}

    Object[][] donnees ;
    public void setDonnees(Object[][] donnees) {this.donnees = donnees;}

    public Affichage(Outils.TypeSociete typeSociete) {
        ;
        Outils.PreparerlaPage(this, contentPane);

        //COLONNES COMMUNES A TOUTES LES SOCIETES

        if (typeSociete == Outils.TypeSociete.CLIENT){

            String[] nomsColonnes = { VuesUtilitaires.RAISONSOCIALE, VuesUtilitaires.VILLE,
                    VuesUtilitaires.CODEPOSTAL, VuesUtilitaires.NUMERORUE, VuesUtilitaires.RUE,
                    VuesUtilitaires.COURRIEL, VuesUtilitaires.TELEPHONE,
                    VuesUtilitaires.CHIFFRESDAFFAIRES, VuesUtilitaires.NB_EMPLOYES}; //attr.client


            Object[][] donnees = new Object[ListeClients.getListeTousClients().size()][nomsColonnes.length];

            for ( int i = 0 ; i < ListeClients.getListeTousClients().size(); i++ )
            {
                int j = 0 ;
                donnees[i][j] = ListeClients.getListeTousClients().get(i).getRaisonSociale();
                j++ ;
                donnees[i][j] = ListeClients.getListeTousClients().get(i).getVille();
                j++;
                donnees[i][j] = ListeClients.getListeTousClients().get(i).getCodePostal();
                j++;
                donnees[i][j] = ListeClients.getListeTousClients().get(i).getNumeroRue();
                j++;
                donnees[i][j] = ListeClients.getListeTousClients().get(i).getRue();
                j++;
                donnees[i][j] = ListeClients.getListeTousClients().get(i).getCourriel();
                j++;
                donnees[i][j] = ListeClients.getListeTousClients().get(i).getTelephone();
                j++;
                donnees[i][j] = ListeClients.getListeTousClients().get(i).getCA();
                j++;
                donnees[i][j] = ListeClients.getListeTousClients().get(i).getNbEmployes();
            }

            setNomsColonnes(nomsColonnes);
            setDonnees(donnees);

        }

        else {

            String[] nomsColonnes = { VuesUtilitaires.RAISONSOCIALE, VuesUtilitaires.VILLE,
                    VuesUtilitaires.CODEPOSTAL, VuesUtilitaires.NUMERORUE, VuesUtilitaires.RUE,
                    VuesUtilitaires.COURRIEL, VuesUtilitaires.TELEPHONE,
                    VuesUtilitaires.DATEDEPROSPECTION, VuesUtilitaires.EST_IL_INTERESSE}; // attrib. prospect

            Object[][] donnees = new Object[ListeProspects.getListeTousProspects().size()][nomsColonnes.length];

            for ( int i = 0 ; i < ListeProspects.getListeTousProspects().size(); i++ )
            {
                int j = 0 ;
                donnees[i][j] = ListeProspects.getListeTousProspects().get(i).getRaisonSociale();
                j++ ;
                donnees[i][j] = ListeProspects.getListeTousProspects().get(i).getVille();
                j++;
                donnees[i][j] = ListeProspects.getListeTousProspects().get(i).getCodePostal();
                j++;
                donnees[i][j] = ListeProspects.getListeTousProspects().get(i).getNumeroRue();
                j++;
                donnees[i][j] = ListeProspects.getListeTousProspects().get(i).getRue();
                j++;
                donnees[i][j] = ListeProspects.getListeTousProspects().get(i).getCourriel();
                j++;
                donnees[i][j] = ListeProspects.getListeTousProspects().get(i).getTelephone();
                j++;
                donnees[i][j] = ListeProspects.getListeTousProspects().get(i).getDateProspection();
                j++;

                // traduction prospect intéressé 1/0 en Oui/Non
                if ( ListeProspects.getListeTousProspects().get(i).getPropsectEstInteresse() == 1) {
                    donnees[i][j] = "Oui";
                }
                else donnees[i][j] = "Non";

            }

            setNomsColonnes(nomsColonnes);
            setDonnees(donnees);

        }

        // création et raccordement du modèle pour Jtable
        DefaultTableModel defaultTableModel = new DefaultTableModel(donnees, nomsColonnes);
        table.setModel(defaultTableModel);


        //affichage du modèle
        contentPane.setLayout(new BorderLayout()); // plante sans cela.
        contentPane.add(table.getTableHeader(), BorderLayout.PAGE_START);
        contentPane.add(table, BorderLayout.CENTER);

        table.setVisible(true);

        table.setModel(defaultTableModel);

        System.out.println(defaultTableModel.getDataVector());

        System.out.println(table.getTableHeader());

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
