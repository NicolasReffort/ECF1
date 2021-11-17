package vues;

import Utilitaires.Outils;
import entites.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/****
 * Classe du même package que les vues. Contient les méthodes d'affichage et variables pour les vues.
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
    public static final String MERCIDE = "Merci de bien vouloir compléter correctement le champ : " ;

    public static final int WIDTH_NORMAL = 800 ;
    public static final int HEIGHT_NORMAL = 900 ;
    public static final int MIN_WIDTH_NORMAL = 800 ;
    public static final int MIN_HEIGHT_NORMAL = 900 ;




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

    public static void PreparerlaPage(Formulaire formulaire, Container contentPaneFormulaire){
        //REMPLISSAGE DE LA PAGE AVEC LE PANE PRINCIPAL
        formulaire.setContentPane(contentPaneFormulaire);
        //TAILLE
        formulaire.setSize(WIDTH_NORMAL, HEIGHT_NORMAL);
        formulaire.setMinimumSize(new Dimension(MIN_WIDTH_NORMAL, MIN_HEIGHT_NORMAL));

    }

    public static void PreparerlaPage(Accueil accueil, Container contentPaneFormulaire){
        //REMPLISSAGE DE LA PAGE AVEC LE PANE PRINCIPAL
        accueil.setContentPane(contentPaneFormulaire);
        //TAILLE
        accueil.setSize(WIDTH_NORMAL, HEIGHT_NORMAL);
        accueil.setMinimumSize(new Dimension(MIN_WIDTH_NORMAL, MIN_HEIGHT_NORMAL));

    }

    public static void PreparerlaPage(Affichage affichage, JPanel contentPaneFormulaire){
        //REMPLISSAGE DE LA PAGE AVEC LE PANE PRINCIPAL
        affichage.setContentPane(contentPaneFormulaire);
        //TAILLE
        affichage.setSize(WIDTH_NORMAL, HEIGHT_NORMAL);
        affichage.setMinimumSize(new Dimension(MIN_WIDTH_NORMAL, MIN_HEIGHT_NORMAL));
    }

    public static void RemplirNomsChampsFilles(Outils.TypeSociete typeSociete,
                                               JTextField attributFilleTexteField1, JTextField attributFilleTexteField2,
                                               JTextField champId){

         switch (typeSociete){

             case CLIENT :
                 attributFilleTexteField1.setText(VuesUtilitaires.CHIFFRESDAFFAIRES.toUpperCase());
                 attributFilleTexteField2.setText(VuesUtilitaires.NB_EMPLOYES.toUpperCase());
                 // On affiche le futur identifiant de la Fille à créer
                 champId.setText( Integer.toString(Client.getCompteurClients() + 1 ));
                 ;
                 break;


             case PROSPECT:
                 attributFilleTexteField1.setText(VuesUtilitaires.DATEDEPROSPECTION.toUpperCase());
                 attributFilleTexteField2.setText(VuesUtilitaires.EST_IL_INTERESSE.toUpperCase() );
                 champId.setText( Integer.toString(Prospect.getCompteurProspects() + 1 ));;
                 break;
         }
    }

    public static void RemplirNomsChampsFilles(Outils.TypeSociete typeSociete,
                                               JTextField attributFilleTexteField1, JTextField attributFilleTexteField2){

        switch (typeSociete){

            case CLIENT :
                attributFilleTexteField1.setText(VuesUtilitaires.CHIFFRESDAFFAIRES.toUpperCase());
                attributFilleTexteField2.setText(VuesUtilitaires.NB_EMPLOYES.toUpperCase());
                ;
                break;


            case PROSPECT:
                attributFilleTexteField1.setText(VuesUtilitaires.DATEDEPROSPECTION.toUpperCase());
                attributFilleTexteField2.setText(VuesUtilitaires.EST_IL_INTERESSE.toUpperCase() );
                break;
        }
    }

    public static void RemplirContenuChampsFilles(Client client, JTextField champFille1, JTextField champFille2){

         champFille1.setText(String.valueOf(client.getCA()));
         champFille2.setText(String.valueOf(client.getNbEmployes()));
    }

    public static void RemplirContenuChampsFilles(Prospect prospect, JTextField champFille1, JTextField champFille2){

        champFille1.setText( (prospect.getDateProspection().toString() ));
        champFille2.setText((prospect.getPropsectEstInteresse()));
    }





}

