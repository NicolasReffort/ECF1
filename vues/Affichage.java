package vues;

import Exceptions.MonExceptionMaison;
import Utilitaires.Outils;
import entites.*;
import jdk.swing.interop.SwingInterOpUtils;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
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

    private String[] NOMS_COLONNES;
    public void setNOMS_COLONNES(String[] NOMS_COLONNES) {this.NOMS_COLONNES = NOMS_COLONNES;}

    private int nbLignes;
    public void setNbLignes(int nbLignes) {this.nbLignes = nbLignes;}

    public Affichage(Outils.TypeSociete typeSociete) {

        //PANE CHARGE
        setContentPane(contentPane);
        //TAILLE
        setSize(200, 200);
        setMinimumSize(new Dimension(200, 100));

        // CALCUL POUR LE MODEL ADEQUAT SELON LE TYPE DE SOCIETE
        if (typeSociete == Outils.TypeSociete.CLIENT){
            String[] NOMS_COLONNES = {VuesUtilitaires.RAISONSOCIALE, VuesUtilitaires.VILLE, VuesUtilitaires.CODEPOSTAL,
                        VuesUtilitaires.NUMERORUE, VuesUtilitaires.RUE, VuesUtilitaires.COURRIEL, VuesUtilitaires.TELEPHONE,
                        VuesUtilitaires.CHIFFRESDAFFAIRES, VuesUtilitaires.NB_EMPLOYES // <-----ATTRIBUTS CLIENT
            };
            setNbLignes(ListeClients.getListeTousClients().size());
        }

        else{
            String[] NOMS_COLONNES = {VuesUtilitaires.RAISONSOCIALE, VuesUtilitaires.VILLE, VuesUtilitaires.CODEPOSTAL,
                        VuesUtilitaires.NUMERORUE, VuesUtilitaires.RUE, VuesUtilitaires.COURRIEL, VuesUtilitaires.TELEPHONE,
                        VuesUtilitaires.DATEDEPROSPECTION, VuesUtilitaires.EST_IL_INTERESSE // <-----ATTRIBUTS PROSPECT
            };
           setNbLignes(ListeProspects.getListeTousProspects().size());
        };

        //Instanciation et paramétrage du model pour la table
        DefaultTableModel defaultTableModel = new DefaultTableModel(NOMS_COLONNES, nbLignes);
        VuesUtilitaires.RemplirJtable(table1, defaultTableModel, typeSociete);
        table1.setVisible(true);
        //PANE VISIBLE
        setVisible(true);
        getRootPane().setDefaultButton(buttonOK);



    }


}



