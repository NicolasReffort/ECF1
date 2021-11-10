package vues;

import Utilitaires.Outils;
import entites.ListeClients;
import entites.ListeProspects;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
    public static void RemplirJtable(JTable table, DefaultTableModel defaultTableModel, Outils.TypeSociete typeSociete) {

        table.setModel(defaultTableModel);
        int nblignes = defaultTableModel.getColumnName().length(); // VOIR LES CSQ DE PRENDRE
        // public DefaultTableModel(Object[] columnNames,int rowCount) https://docs.oracle.com/javase/7/docs/api/javax/swing/table/DefaultTableModel.html#DefaultTableModel(java.lang.Object[],%20int)
        int nbColonnes = defaultTableModel.getColumnCount();

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
        }

    }
}

