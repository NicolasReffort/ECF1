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

    /***
     * Fixe la taille d'une frame
     * @param jFrame une Jframe
     * @param contentPaneFormulaire qu'on veut setter pour la jframe
     */
    public static void PreparerlaPage(JFrame jFrame, Container contentPaneFormulaire){

        //REMPLISSAGE DE LA PAGE AVEC LE PANE PRINCIPAL
        jFrame.setContentPane(contentPaneFormulaire);
        //TAILLE
        jFrame.setSize(WIDTH_NORMAL, HEIGHT_NORMAL);
        jFrame.setMinimumSize(new Dimension(MIN_WIDTH_NORMAL, MIN_HEIGHT_NORMAL));
    }

    /***
     * Pour création Prospect Client // factorisable avec l'autre avec un paramètre pour compteurclient+1??
     * @param typeSociete
     * @param attributFilleTexteField1
     * @param attributFilleTexteField2
     * @param champId champ spécialement généré pour la création
     * @param checkbox
     */
    public static void RemplirNomsChampsFilles(Outils.TypeSociete typeSociete,
                                               JTextField attributFilleTexteField1,
                                               JTextField attributFilleTexteField2,
                                               JCheckBox checkbox,
                                               JTextField champId){

         switch (typeSociete){

             case CLIENT :
                 attributFilleTexteField1.setText(VuesUtilitaires.CHIFFRESDAFFAIRES.toUpperCase());
                 attributFilleTexteField2.setText(VuesUtilitaires.NB_EMPLOYES.toUpperCase());

                 // On affiche le futur identifiant de la Fille à créer
                 champId.setText( Integer.toString(Client.getCompteurClients() + 1 ));

                 //pas besoin d'afficher la checkbox
                 checkbox.setVisible(false);
                 ;
                 break;


             case PROSPECT:
                 attributFilleTexteField1.setText(VuesUtilitaires.DATEDEPROSPECTION.toUpperCase());
                 champId.setText( Integer.toString(Prospect.getCompteurProspects() + 1 ));;
                 checkbox.setText("EST INTERESSE.");

                 //pas besoin d'afficher les champs pour la date de prospection, la checkbox suffit.
                 attributFilleTexteField2.setVisible(false);


                 break;
         }
    }

    /***
     * Pour Modifi/Supp Client-Prospect
     * @param typeSociete
     * @param attributFilleTexteField1
     * @param attributFilleTexteField2
     * @param checkbox
     * @param
     */
    public static void RemplirNomsChampsFilles(Outils.TypeSociete typeSociete,
                                               JTextField attributFilleTexteField1,
                                               JTextField attributFilleTexteField2,
                                               JCheckBox checkbox){

        switch (typeSociete){

            case CLIENT :
                attributFilleTexteField1.setText(VuesUtilitaires.CHIFFRESDAFFAIRES.toUpperCase());
                attributFilleTexteField2.setText(VuesUtilitaires.NB_EMPLOYES.toUpperCase());
                //pas besoin d'afficher la checkbox
                checkbox.setVisible(false);
                break;


            case PROSPECT:
                attributFilleTexteField1.setText(VuesUtilitaires.DATEDEPROSPECTION.toUpperCase());
                checkbox.setText("EST INTERESSE.");

                //pas besoin d'afficher les champs pour la date de prospection, la checkbox suffit.
                attributFilleTexteField2.setVisible(false);
                break;
        }
    }


    /***
     * Charge le contenu des champs spécifiques au client
     * @param client
     * @param champFille1 C.a
     * @param champFille2 nb employes
     */
    public static void RemplirContenuChampsFilles(Client client, JTextField champFille1, JTextField champFille2){

         champFille1.setText(String.valueOf(client.getCA()));
         champFille2.setText(String.valueOf(client.getNbEmployes()));
    }

    /***
     *Charge le contenu des champs spécifiques au prospect
     * @param prospect
     * @param champFille1 date prospection
     * @param checkbox pour changer l'intéressement du prospect OUI/NON
     */
    public static void RemplirContenuChampsFilles(Prospect prospect, JTextField champFille1,
                                                  JCheckBox checkbox){

        champFille1.setText( (prospect.getDateProspection().format(Outils.getDateTimeFormatter()) ));

        if(prospect.getPropsectEstInteresse().equals("OUI")){ //charger checkbox avec le choix enregistré.
            checkbox.setSelected(true);
        }
        else checkbox.setSelected(false);
    }

    /***
     * Supprime un client
     * @param clientAsuprrimer
     */
    public static void deleteThat(Client clientAsuprrimer){

        ListeClients.getListeTousClients().remove(clientAsuprrimer);
        clientAsuprrimer = null ;

    }

    /***
     * supprime un prospect
     * @param prospectAsupprimer
     */
    public static void deleteThat(Prospect prospectAsupprimer){

        ListeProspects.getListeTousProspects().remove(prospectAsupprimer);
        prospectAsupprimer = null ;

    }

    /***
     *
     * @param bool true => setEditable(true) ;
     * @param champs Un tableau de Jtextfield que l'on souhaite rendre éditable à souhait.
     */
    public static void RendreContenuChampsEditable (Boolean bool, JTextField[] champs){

        for (int i = 0 ; i < champs.length ; i ++ ) {
            champs[i].setEditable(bool);
        }
    }





}

