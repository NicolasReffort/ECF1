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
    private JButton buttonRetour;
    private JButton buttonQuitter;
    private JTable table;
    private JPanel jpanelduJtable;
    private JPanel jPanelBoutons;

    private  String[] nomsColonnes;
    public void setNomsColonnes(String[] nomsColonnes) {this.nomsColonnes = nomsColonnes;}

    Object[][] donnees ;
    public void setDonnees(Object[][] donnees) {this.donnees = donnees;}

    /***
     * Affiche tous les sociétés d'un type donné, sans le champ commentaires
     * @param typeSociete
     */
    public Affichage(Outils.TypeSociete typeSociete) {


        VuesUtilitaires.PreparerlaPage(this, contentPane);

        VuesUtilitaires.PreparerBoutonAccueil(buttonRetour, this);

        buttonRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        buttonQuitter.setText("Quitter l'application");

        setVisible(true); // Frame devient visible

        jPanelBoutons.setVisible(true);
        jPanelBoutons.setEnabled(true);



        if (typeSociete == Outils.TypeSociete.CLIENT){

            String[] nomsColonnes = { VuesUtilitaires.RAISONSOCIALE, VuesUtilitaires.VILLE,
                    VuesUtilitaires.CODEPOSTAL, VuesUtilitaires.NUMERORUE, VuesUtilitaires.RUE,
                    VuesUtilitaires.COURRIEL, VuesUtilitaires.TELEPHONE,
                    VuesUtilitaires.CHIFFRESDAFFAIRES, VuesUtilitaires.NB_EMPLOYES, VuesUtilitaires.ID};
                    //attr.client


            Object[][] donnees = new Object[ListeClients.getListeTousClients().size()][nomsColonnes.length];

            for ( int i = 0 ; i < ListeClients.ObtenirListeTriee().size(); i++ )
            {
                int j = 0 ;
                donnees[i][j] = ListeClients.ObtenirListeTriee().get(i).getRaisonSociale();
                j++ ;
                donnees[i][j] = ListeClients.ObtenirListeTriee().get(i).getVille();
                j++;
                donnees[i][j] = ListeClients.ObtenirListeTriee().get(i).getCodePostal();
                j++;
                donnees[i][j] = ListeClients.ObtenirListeTriee().get(i).getNumeroRue();
                j++;
                donnees[i][j] = ListeClients.ObtenirListeTriee().get(i).getRue();
                j++;
                donnees[i][j] = ListeClients.ObtenirListeTriee().get(i).getCourriel();
                j++;
                donnees[i][j] = ListeClients.ObtenirListeTriee().get(i).getTelephone();
                j++;
                donnees[i][j] = ListeClients.ObtenirListeTriee().get(i).getCA();
                j++;
                donnees[i][j] = ListeClients.ObtenirListeTriee().get(i).getNbEmployes();
                j++;
                donnees[i][j] = ListeClients.ObtenirListeTriee().get(i).getIdentifiant();
            }
            setNomsColonnes(nomsColonnes);
            setDonnees(donnees);

        }



        else {

            String[] nomsColonnes = { VuesUtilitaires.RAISONSOCIALE, VuesUtilitaires.VILLE,
                    VuesUtilitaires.CODEPOSTAL, VuesUtilitaires.NUMERORUE, VuesUtilitaires.RUE,
                    VuesUtilitaires.COURRIEL, VuesUtilitaires.TELEPHONE,
                    VuesUtilitaires.DATEDEPROSPECTION, VuesUtilitaires.EST_IL_INTERESSE,VuesUtilitaires.ID};
                    // attrib. prospect

            Object[][] donnees = new Object[ListeProspects.ObtenirListeTriee().size()][nomsColonnes.length];

            for ( int i = 0 ; i < ListeProspects.ObtenirListeTriee().size(); i++ )
            {
                int j = 0 ;
                donnees[i][j] = ListeProspects.ObtenirListeTriee().get(i).getRaisonSociale();
                j++ ;
                donnees[i][j] = ListeProspects.ObtenirListeTriee().get(i).getVille();
                j++;
                donnees[i][j] = ListeProspects.ObtenirListeTriee().get(i).getCodePostal();
                j++;
                donnees[i][j] = ListeProspects.ObtenirListeTriee().get(i).getNumeroRue();
                j++;
                donnees[i][j] = ListeProspects.ObtenirListeTriee().get(i).getRue();
                j++;
                donnees[i][j] = ListeProspects.ObtenirListeTriee().get(i).getCourriel();
                j++;
                donnees[i][j] = ListeProspects.ObtenirListeTriee().get(i).getTelephone();
                j++;
                donnees[i][j] = ListeProspects.ObtenirListeTriee().get(i).getDateProspection();
                j++;
                donnees[i][j] = ListeProspects.ObtenirListeTriee().get(i).getPropsectEstInteresse();
                j++;
                donnees[i][j] = ListeProspects.ObtenirListeTriee().get(i).getIdentifiant();

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

        buttonQuitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


    }

    private void onCancel() {
        Accueil accueil = new Accueil();
        dispose();
    }
}
