package vues;

import Utilitaires.Outils;
import entites.ListeClients;
import entites.ListeProspects;
import entites.Societe;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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


    // filles
    public static final String CHIFFRESDAFFAIRES = "Chiffre d'affaires";
    public static final String NB_EMPLOYES = "Nombre d'employés";
    public static final String DATEDEPROSPECTION = "Date de prospection";
    public static final String EST_IL_INTERESSE = "Intéressé : O/N.";

    /****
     *
     * @param table Une Jtable pas encore rattachée à un Model de Table
     * @param defaultTableModel un model de Table qu'on aura déjà paramétré au préalable.
     * @param typeSociete le type de la société, pour savoir avec quelle liste hydrater.
     */
    public static void RemplirJtable(JTable table, DefaultTableModel defaultTableModel,
                                     Outils.TypeSociete typeSociete) {

        /*
        table.setModel(defaultTableModel);
        JScrollPane jscrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        int nblignes = defaultTableModel.getRowCount();
        int nbColonnes = defaultTableModel.getColumnCount();

        //Remplissage Jtable Client
        if (typeSociete == Outils.TypeSociete.CLIENT) {

            int j = 0 ;
            for (int i = 0; i < nblignes; i++) {
                table.setValueAt(ListeClients.getListeTousClients().get(i).getRaisonSociale(), i, j);
            }
            j++;
            for (int i = 0; i < nblignes; i++) {
                table.setValueAt(ListeClients.getListeTousClients().get(i).getVille(), i, j);
            }
            j++;
            for (int i = 0; i < nblignes; i++) {
                table.setValueAt(ListeClients.getListeTousClients().get(i).getCodePostal(), i, j);
            }
            j++;
            for (int i = 0; i < nblignes; i++) {
                table.setValueAt(ListeClients.getListeTousClients().get(i).getNumeroRue(), i, j);
            }
            j++;
            for (int i = 0; i < nblignes; i++) {
                table.setValueAt(ListeClients.getListeTousClients().get(i).getRue(), i, j);
            }
            j++;
            for (int i = 0; i < nblignes; i++) {
                table.setValueAt(ListeClients.getListeTousClients().get(i).getCourriel(), i, j);
            }
            j++;
            for (int i = 0; i < nblignes; i++) {
                table.setValueAt(ListeClients.getListeTousClients().get(i).getTelephone(), i, j);
            }
            j++;

            //attributs clients propres
            for (int i = 0; i < nblignes; i++) {
                table.setValueAt(ListeClients.getListeTousClients().get(i).getCA(), i, j);
            }
            j++;
            for (int i = 0; i < nblignes; i++) {
                table.setValueAt(ListeClients.getListeTousClients().get(i).getNbEmployes(), i, j);
            }


        }
        //Remplissage Jtable Prospect
        else{

            int j = 0 ;
            for (int i = 0; i < nblignes; i++) {
                table.setValueAt(ListeProspects.getListeTousProspects().get(i).getRaisonSociale(), i, j);
            }
            j++;
            for (int i = 0; i < nblignes; i++) {
                table.setValueAt(ListeProspects.getListeTousProspects().get(i).getVille(), i, j);
            }
            j++;
            for (int i = 0; i < nblignes; i++) {
                table.setValueAt(ListeProspects.getListeTousProspects().get(i).getCodePostal(), i, j);
            }
            j++;
            for (int i = 0; i < nblignes; i++) {
                table.setValueAt(ListeProspects.getListeTousProspects().get(i).getNumeroRue(), i, j);
            }
            j++;
            for (int i = 0; i < nblignes; i++) {
                table.setValueAt(ListeProspects.getListeTousProspects().get(i).getRue(), i, j);
            }
            j++;
            for (int i = 0; i < nblignes; i++) {
                table.setValueAt(ListeProspects.getListeTousProspects().get(i).getCourriel(), i, j);
            }
            j++;
            for (int i = 0; i < nblignes; i++) {
                table.setValueAt(ListeProspects.getListeTousProspects().get(i).getTelephone(), i, j);
            }
            j++;

            //attributs prospects propres
            for (int i = 0; i < nblignes; i++) {
                table.setValueAt(ListeProspects.getListeTousProspects().get(i).getDateProspection(), i, j);
            }
            j++;
            for (int i = 0; i < nblignes; i++) {
                table.setValueAt(ListeProspects.getListeTousProspects().get(i).getPropsectEstInteresse(), i, j);
            }

         */

    }

}

