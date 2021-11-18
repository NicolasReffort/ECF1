package Utilitaires;

import Exceptions.MonExceptionMaison;
import entites.Client;
import entites.Prospect;
import entites.Societe;
import vues.VuesUtilitaires;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Outils {

    //RELATIF A LA CLASSE
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static DateTimeFormatter getDateTimeFormatter() {        return DATE_TIME_FORMATTER;}

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
            return LocalDate.parse(string, getDateTimeFormatter());
        }

        catch (NullPointerException npe ){
            throw new MonExceptionMaison(VuesUtilitaires.MERCIDE + VuesUtilitaires.DATEDEPROSPECTION);
        }
        catch (IllegalArgumentException iae){
            throw new MonExceptionMaison( VuesUtilitaires.MERCIDE + "date (format jj-mm-aaaa) ");
        }
        catch (DateTimeParseException dtpe){
            throw new MonExceptionMaison( VuesUtilitaires.MERCIDE + "date (format jj-mm-aaaa) ");
        }

    }




}
