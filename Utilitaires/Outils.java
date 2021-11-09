package Utilitaires;

import entites.Client;
import entites.Prospect;
import entites.Societe;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Outils {

    private boolean itsClient = false;
    private boolean itsProspect = false;

    public boolean isItsClient() {return itsClient;}
    public void setItsClient(boolean itsClient) {this.itsClient = itsClient;}
    public boolean isItsProspect() {return itsProspect;}

    public void setItsProspect(boolean itsProspect) {this.itsProspect = itsProspect;}

    public Societe DirecteurDeCasting(Societe societe){

        if (societe instanceof Client) {
            Client client = ((Client) societe);
            setItsClient(true);
            return client;
        }

        else
        {Prospect propsect = ((Prospect) societe);
            setItsProspect(true);
            return propsect;
        }
    }

    //TESTEURS DE ...-----------------------------------------------------------------------------------------------

    public LocalDate StringToLocalDate(String nouvelleValeurSouhaitee)throws MonExceptionMaison{

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return LocalDate.parse(nouvelleValeurSouhaitee, formatter);
        }

        catch (DateTimeParseException dtpe) {
            throw new MonExceptionMaison("Ceci ne constitue pas une date au format valide (attendu = format dd-MM-yyyy");
        }
    }



}
