package entites;

import java.util.ArrayList;
import java.util.List;

public class ListeProspects {
    //RELATIF A LA CLASSE----------------------------------------------------------------------------------------------------
    private static List<Prospect> listeTousProspects = new ArrayList<Prospect>();

    public static List<Prospect> getListeTousProspects() {
        return listeTousProspects;
    }

    public static List<Prospect> ObtenirListeTriee() {
        getListeTousProspects().sort((p2, p1) -> p2.getRaisonSociale().compareTo(p1.getRaisonSociale()));
        return listeTousProspects;
    }

    public static void ajouterListeProspects(Prospect prospect) {
        listeTousProspects.add(prospect);
    }

}
