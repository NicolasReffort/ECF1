package Utilitaires;

import Exceptions.MonExceptionEntites;
import entites.Client;
import entites.Prospect;
import entites.Societe;
import vues.VuesUtilitaires;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Outils {

    //RELATIF A LA CLASSE
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static DateTimeFormatter getDateTimeFormatter() {        return DATE_TIME_FORMATTER;}

    //ENUMS
    public enum TypeSociete{CLIENT,PROSPECT}


    //TESTEUR DE ...-----------------------------------------------------------------------------------------------
    /***
     *
     * @param string LocalDate en String dont on veut vérifier le bon typage
     * @return Une LocalDate bien typée
     * @throws MonExceptionEntites Si mauvais format ou Npe
     */
    public static LocalDate StringToLocalDate(String string)throws MonExceptionEntites {

        try {
            return LocalDate.parse(string, getDateTimeFormatter());
        }

        catch (NullPointerException npe ){
            throw new MonExceptionEntites(VuesUtilitaires.MERCIDE + VuesUtilitaires.DATEDEPROSPECTION);
        }
        catch (IllegalArgumentException iae){
            throw new MonExceptionEntites( VuesUtilitaires.MERCIDE + "date (argument inadéquat) ");
        }
        catch (DateTimeParseException dtpe){
            throw new MonExceptionEntites( VuesUtilitaires.MERCIDE + "date (format jj-mm-aaaa) ");
        }
    }


}
