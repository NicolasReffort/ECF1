package vues;

import Utilitaires.Outils;
import entites.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class Affichage extends JFrame {

    private JPanel contentPane;
    private JButton buttonCancel;
    private JTable table1;

    private String[] NOMS_COLONNES;
    public void setNOMS_COLONNES(String[] NOMS_COLONNES) {this.NOMS_COLONNES = NOMS_COLONNES;}

    private int nbLignes;
    public void setNbLignes(int nbLignes) {this.nbLignes = nbLignes;}

    public Affichage(Outils.TypeSociete typeSociete) {

        Outils.PreparerlaPage(this, contentPane);
        buttonCancel.setText("Retour à l'accueil");
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
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

        //COLONNES COMMUNES A TOUTES LES SOCIETES
        String[] NOMS_COLONNES = {VuesUtilitaires.RAISONSOCIALE, VuesUtilitaires.VILLE, VuesUtilitaires.CODEPOSTAL,
                VuesUtilitaires.NUMERORUE, VuesUtilitaires.RUE, VuesUtilitaires.COURRIEL, VuesUtilitaires.TELEPHONE};
        //
        if (typeSociete == Outils.TypeSociete.CLIENT){
            setNbLignes(ListeClients.getListeTousClients().size());}
        else  { setNbLignes(ListeProspects.getListeTousProspects().size()); };

        //Instanciation et paramétrage du model pour la table
        DefaultTableModel defaultTableModel = new DefaultTableModel(NOMS_COLONNES, nbLignes);

        // ajout des colonnes spécifiques
        if (typeSociete == Outils.TypeSociete.CLIENT){
            defaultTableModel.addColumn(VuesUtilitaires.CHIFFRESDAFFAIRES);
            defaultTableModel.addColumn(VuesUtilitaires.NB_EMPLOYES);
        }
        else{
            defaultTableModel.addColumn(VuesUtilitaires.DATEDEPROSPECTION);
            defaultTableModel.addColumn(VuesUtilitaires.EST_IL_INTERESSE);
        };

        VuesUtilitaires.RemplirJtable(table1, defaultTableModel, typeSociete);//Remplissage de la table

        //PANE VISIBLE
        setVisible(true);
    }




}



