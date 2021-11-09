package entites;

import Exceptions.MonExceptionMaison;
import Utilitaires.Outils;

public class Client extends Societe{



    //RELATIF A LA CLASSE----------------------------------------------------------------------------------------------
    private static int compteurClients = 0 ;


    //RELATIF AUX INSTANCES--------------------------------------------------------------------------------------------
    private Double CA;
    private int nbEmployes;
    public final double CA_MIN = 200 ;
    public final int NBSALARIE_MIN = 0 ;
    private Outils.TypeSociete CLIENT ;

    //SETTERS ET GETTERS------------------------------------------------------------------------------------------------
    public static int getCompteurClients() {return compteurClients;}

    public Double getCA() {return CA;}
    public void setCA(Double CA) throws MonExceptionMaison {

        if (CA<=CA_MIN ) { // TO DO TESTER LE STRING CA POUR ETRE SUR QUE PAS VIDE.
            throw new MonExceptionMaison("le CA indiqué ne peut être inférieur à " + CA_MIN) ;
        }

        this.CA = CA;
    }

    public int getNbEmployes() {return nbEmployes;}
    public void setNbEmployes(int nbEmployes) throws MonExceptionMaison{
        if ( nbEmployes <= NBSALARIE_MIN )
        {
            throw new MonExceptionMaison("le nombre de salarié indiqué ne peut être inférieur à " + NBSALARIE_MIN) ;
        }
        else {this.nbEmployes = nbEmployes;};
    }

    //CONSTRUCTEURS ----------------------------------------------------------------------------------------------------
    public Client(String raisonSociale,String ville, String numeroRue, String rue, String codePostal, String telephone,
                  String courriel, String commentaires, Double CA, int nbEmployes) throws MonExceptionMaison,
            NumberFormatException, NullPointerException {
        super(raisonSociale,ville, numeroRue, rue, codePostal, telephone, courriel, commentaires);
        compteurClients = compteurClients + 1 ;
        setIdentifiant(compteurClients);
        setCA(CA);
        setNbEmployes(nbEmployes);
        ListeClients.getListeTousClients().add(this);
    }

    //TO STRING --------------------------------------------------------------------------------------------------------


    @Override
    public String toString() {
        return super.toString() + "Client{" +
                "ID=" + getIdentifiant() +
                ", CA=" + CA +
                ", nbEmployes=" + nbEmployes +
                '}' + "\n";
    }
}
