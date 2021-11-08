package entites;

import java.util.ArrayList;
import java.util.List;

public class ListeProspects {
    //RELATIF A LA CLASSE----------------------------------------------------------------------------------------------------
    private static List<Prospect> listeTousProspects = new ArrayList<Prospect>();


    public static List<Prospect>getListeTousProspects() {return listeTousProspects;}
    public static void setListeTousClients(List<Prospect>listeTousProspects) {ListeProspects.listeTousProspects = listeTousProspects;}

}