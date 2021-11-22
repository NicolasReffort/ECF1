import Exceptions.MonExceptionMaison;
import Utilitaires.Outils;
import entites.*;
import vues.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws MonExceptionMaison {
        //RECUPERER DATE PROSPECTION= DEFAUT= AJD ----------------------------------------------------------------------
        LocalDate date = LocalDate.now();

        //INSTANCIATION DES JEUX D ESSAIS------------------------------------------------------------------------------
        Client client1 = new Client("BOB", "Toulouse", "564", "Rue des affaires",
                "89000","0606060615","bobby@roberte.gmail.com", "Bon client",
                56000.5, 1
        );

        Client client2 = new Client("LORRAINE CARTON", "PAM", "45", "Rue des bonnes" ,
                "89000","0606060615","Carton@.gmail.com", "Entreprise en " +
                "carton",
                160000.2, 11
        );

        Client client3 = new Client("MIGUEL PEINTURE", "SAINT DENIS", "1", "Rue des bonnes affaires",
                "89000","0606060615","miguelito@.gmail.com", "Sacré loustique",
                65000.0, 2
        );


        LocalDate dateProspection1 = Outils.StringToLocalDate("01-05-2000");
        LocalDate dateProspection2 = Outils.StringToLocalDate("01-02-2021");

        Prospect prospect2 = new Prospect("Martine Lecture", "Angoulême", "7", "Principale",
                "16000", "+05 45 38 70 03", "angou@feu.fr", "Mairie",
                dateProspection2, "NON"
        );

        Prospect prospect1 = new Prospect("Hugie LEs Bons Tyuaux", "LA", "562", "Madison",
                "1200", "+5512155952", "hugie@free.fr", "Client US", dateProspection1,
                "OUI"
        );

        Accueil accueil = new Accueil();
    }
}
