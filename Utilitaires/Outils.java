package Utilitaires;

import Exceptions.MonExceptionMaison;
import entites.Client;
import entites.Prospect;
import entites.Societe;
import vues.Accueil;
import vues.Affichage;
import vues.Formulaire;
import vues.VuesUtilitaires;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Outils {

    //RELATIF AUX INSTANCES
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

    //TESTEUR DE ...-----------------------------------------------------------------------------------------------

    /***
     *
     * @param string LocalDate en String dont on veut vérifier le bon typage
     * @return Une LocalDate bien typée
     * @throws MonExceptionMaison Si mauvais format ou Npe
     */
    public static LocalDate StringToLocalDate(String string)throws MonExceptionMaison {


        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return LocalDate.parse(string, formatter);
        }

        catch (NullPointerException npe ){
            throw new MonExceptionMaison(VuesUtilitaires.MERCIDE + VuesUtilitaires.DATEDEPROSPECTION);

        }
        catch (DateTimeParseException dtpe) {
            throw new MonExceptionMaison("Ceci ne constitue pas une date au format valide (attendu = format dd-MM-yyyy");
        }
    }




}
