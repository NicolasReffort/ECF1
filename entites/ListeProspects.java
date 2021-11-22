package entites;

import Exceptions.MonExceptionEntites;

import java.util.ArrayList;
import java.util.List;

public class ListeProspects {
    //RELATIF A LA CLASSE----------------------------------------------------------------------------------------------------
    private static List<Prospect> listeTousProspects = new ArrayList<Prospect>();

    public static List<Prospect> getListeTousProspects() {
        return listeTousProspects;
    }
    public static void setListeTousProspects(List<Prospect> listeTousProspects) {
        ListeProspects.listeTousProspects = listeTousProspects;    }

    public static List<Prospect> ObtenirListeTriee() {
        getListeTousProspects().sort((p2, p1) -> p2.getRaisonSociale().compareTo(p1.getRaisonSociale()));
        return listeTousProspects;
    }

    public static void ajouterListeProspects(Prospect prospect) {
        listeTousProspects.add(prospect);
    }

    public static int ConnaitreDernierIdAttribue() throws MonExceptionEntites {

        try {
            Prospect dernierProspect = listeTousProspects.get(listeTousProspects.size() - 1);
            return dernierProspect.getIdentifiant();
        }
        catch (IndexOutOfBoundsException iob){
            throw new MonExceptionEntites("LISTE PROSPECT VIDE");
        }

    }

}
