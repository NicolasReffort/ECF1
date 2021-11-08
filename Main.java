import Utilitaires.MonExceptionMaison;
import entites.*;
import vues.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) throws MonExceptionMaison {
        //RECUPERER DATE PROSPECTION= DEFAUT= AJD ----------------------------------------------------------------------
        LocalDate date = LocalDate.now();

        //INSTANCIATION DES JEUX D ESSAIS------------------------------------------------------------------------------
        Societe client1 = new Client("Robert", "Toulouse", "564", "Rue des bonnes affaires",
                "89000","0606060615","robert@roberte.gmail.com", "Sacré loustique",
                56000.5, 1
        );

        Accueil accueil = new Accueil();
        accueil.setVisible(true);
    }
}
