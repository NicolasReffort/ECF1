package entites;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListeClients {
    //RELATIF A LA CLASSE----------------------------------------------------------------------------------------------------
    private static List<Client> listeTousClients = new ArrayList<Client>();

    public static List<Client> getListeTousClients() {
        return listeTousClients;
    }

    public static List<Client> ObtenirListeTriee() {
        getListeTousClients().sort((p2, p1)->p2.getRaisonSociale().compareTo(p1.getRaisonSociale()));
        return listeTousClients;
    }

    public static void ajouterListeClients(Client client) {
        ListeClients.getListeTousClients().add(client);
    }

}
