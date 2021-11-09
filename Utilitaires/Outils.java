package Utilitaires;

import Exceptions.MonExceptionMaison;
import entites.Client;
import entites.Prospect;
import entites.Societe;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Outils {
    //DE CLASSSSSE
    public static boolean itsClient = false;
    public static boolean itsProspect = false;

    public static void setItsClient(boolean itsClient) {itsClient = itsClient;}
    public static void setItsProspect(boolean itsProspect) {itsProspect = itsProspect;}

    //ENUMS
    public enum TypeSociete{CLIENT,PROSPECT}

    public static void DirecteurDeCasting(Societe societe){

        if (societe instanceof Client) {
            setItsClient(true);
            setItsProspect(false);
        }

        else
        {
            setItsProspect(true);
            setItsProspect(false);
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





}
