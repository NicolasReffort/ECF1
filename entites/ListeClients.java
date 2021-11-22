package entites;

import Exceptions.MonExceptionEntites;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListeClients {
    //RELATIF A LA CLASSE----------------------------------------------------------------------------------------------------
    private static List<Client> listeTousClients = new ArrayList<Client>();

    public static List<Client> getListeTousClients() {
        return listeTousClients;
    }

    public static List<Client> ObtenirListeTrieeRaisonSociale() {
        getListeTousClients().sort((p2, p1)->p2.getRaisonSociale().compareTo(p1.getRaisonSociale()));
        return listeTousClients;
    }

    public static int ConnaitreDernierIdAttribue() throws MonExceptionEntites {

      try {
          Client dernierClient = listeTousClients.get(listeTousClients.size() - 1);
          return dernierClient.getIdentifiant();
      }

      catch (IndexOutOfBoundsException Iob){
          throw new MonExceptionEntites("LISTE CLIENT VIDE");

      }

    }

    public static void ajouterListeClients(Client client) {
        ListeClients.getListeTousClients().add(client);
    }

}
