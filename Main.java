import Exceptions.MonExceptionMaison;
import entites.*;
import vues.*;

import java.time.LocalDate;

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


        Accueil accueil = new Accueil();
    }
}
