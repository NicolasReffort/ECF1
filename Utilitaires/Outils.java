package Utilitaires;

import Exceptions.MonExceptionMaison;
import entites.Client;
import entites.Prospect;
import entites.Societe;
import vues.Accueil;
import vues.Formulaire;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Outils {
    //D INSTANCE
    public boolean itsClient;
    public void setItsClient(boolean itsClient) {
        this.itsClient = itsClient;
    }

    //ENUMS
    public enum TypeSociete{CLIENT,PROSPECT}

    public void DirecteurDeCasting(Societe societe){

        if (societe instanceof Client) {
            setItsClient(true);
        }

        if (societe instanceof  Prospect)
        {
            setItsClient(false);
        }
    }



    //TESTEURS DE ...-----------------------------------------------------------------------------------------------

    public LocalDate StringToLocalDate(String nouvelleValeurSouhaitee)throws MonExceptionMaison {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return LocalDate.parse(nouvelleValeurSouhaitee, formatter);
        }

        catch (DateTimeParseException dtpe) {
            throw new MonExceptionMaison("Ceci ne constitue pas une date au format valide (attendu = format dd-MM-yyyy");
        }
    }

    public static void RetournerAccueil(){

        Accueil accueil = new Accueil();
    }

    public static void PreparerlaPage(Formulaire formulaire, Container contentPaneFormulaire){
        //REMPLISSAGE DE LA PAGE AVEC LE PANE PRINCIPAL
        formulaire.setContentPane(contentPaneFormulaire);
        //TAILLE
        formulaire.setSize(800, 900);
        formulaire.setMinimumSize(new Dimension(150, 156));
    }

    public static void PreparerlaPage(Accueil accueil, Container contentPaneFormulaire){
        //REMPLISSAGE DE LA PAGE AVEC LE PANE PRINCIPAL
        accueil.setContentPane(contentPaneFormulaire);
        //TAILLE
        accueil.setSize(800, 900);
        accueil.setMinimumSize(new Dimension(150, 156));
    }
















}
