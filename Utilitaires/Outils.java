package Utilitaires;

import entites.Client;
import entites.Prospect;
import entites.Societe;

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



}
