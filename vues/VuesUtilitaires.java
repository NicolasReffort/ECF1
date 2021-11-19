package vues;

import Utilitaires.Outils;
import entites.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

/****
 * Classe du même package que les vues. Contient les méthodes d'affichage et CONSTANTES pour les vues.
 */
public class VuesUtilitaires {

    // CONSTANTES POUR LES NOMS DES ATTRIBUTS
    public static final String RAISONSOCIALE = "Raison sociale";
    public static final String VILLE = "Ville";
    public static final String CODEPOSTAL = "Code Postal";
    public static final String NUMERORUE = "Numéro de rue";
    public static final String RUE = "Nom de la Rue";
    public static final String COURRIEL = "Courriel";
    public static final String TELEPHONE = "Téléphone";
    public static final String COMMENTAIRES = "Commentaires";
    public static final String ID = "Identifiant";
    public static final String MERCIDE = "Merci de bien vouloir compléter correctement le champ : " ;
    public static final String ERREUR_INCONNUE = "Erreur inconnue. Merci de contacter votre service informatique. ";

    public static final int WIDTH_NORMAL = 600 ;
    public static final int HEIGHT_NORMAL = 600 ;
    public static final int MIN_WIDTH_NORMAL = 350 ;
    public static final int MIN_HEIGHT_NORMAL = 500 ;

    // filles
    public static final String CHIFFRESDAFFAIRES = "Chiffre d'affaires";
    public static final String NB_EMPLOYES = "Nombre d'employés";
    public static final String DATEDEPROSPECTION = "Date de prospection";
    public static final String EST_IL_INTERESSE = "Intéressé : O/N.";

     public static void PreparerBoutonAccueil(JButton button, JFrame jframe )
    {
        button.setText("Retour Accueil");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jframe.dispose();
                Accueil accueil = new Accueil();
            }
        });
    }

    /***
     * Fixe la taille d'une frame et sette son content
     * @param jFrame une Jframe
     * @param contentPane qu'on veut setter pour la jframe
     */
    public static void PreparerlaPage(JFrame jFrame, Container contentPane){

        //REMPLISSAGE DE LA PAGE AVEC LE PANE PRINCIPAL
        jFrame.setContentPane(contentPane);
        //TAILLE
        jFrame.setSize(WIDTH_NORMAL, HEIGHT_NORMAL);
        jFrame.setMinimumSize(new Dimension(MIN_WIDTH_NORMAL, MIN_HEIGHT_NORMAL));
    }

}

