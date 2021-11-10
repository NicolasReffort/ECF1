package entites;

import Exceptions.MonExceptionMaison;
import Utilitaires.Outils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Prospect extends Societe {

    //RELATIF A LA CLASSE-----------------------------------------------------------------------------------------------
    private static int compteurProspects = 0 ;


    public void dateProspectionConvertor(String datePropsectionEnstring) throws MonExceptionMaison {

        if (datePropsectionEnstring.isEmpty() || datePropsectionEnstring.isBlank() ){
            throw new MonExceptionMaison("Merci de bien vouloir saisir une date. ") ;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(datePropsectionEnstring, formatter);
            this.setDateProspection(date);
        }

        catch (DateTimeParseException dtpe){
            throw new MonExceptionMaison("Merci de bien vouloir saisir une date au format jj/mm/aaaa ");
        }
    }

    //RELATIF AUX INSTANCES--------------------------------------------------------------------------------------------
    private LocalDate dateProspection;
    private int propsectEstInteresse;
    private Outils.TypeSociete PROSPECT ;



    //SETTERS ET GETTERS-----------------------------------------------------------------------------------------------
    public static int getCompteurProspects() {
        return compteurProspects;
    }
    public LocalDate getDateProspection() {
        return dateProspection;
    }

    public void setDateProspection(LocalDate dateProspection) { // TO DO VERIFIER QUE LE STRING EST NON VIDE

        this.dateProspection = dateProspection;
    }




    public int getPropsectEstInteresse() {return propsectEstInteresse;}
    public void setPropsectEstInteresse(int propsectEstInteresse) throws MonExceptionMaison {

        if ( (propsectEstInteresse != 1) && (propsectEstInteresse !=0) ) {
            throw new MonExceptionMaison("Merci de saisir une valeur correcte pour l'intéressement du prospect : oui ou non.");
        }

        this.propsectEstInteresse = propsectEstInteresse;
    }

    public Outils.TypeSociete getType() {
        return PROSPECT;
    }

    //CONSTRUCTEURS----------------------------------------------------------------------------------------------------
    public Prospect(String raisonSociale, String ville, String numeroRue, String rue, String codePostal, String telephone,
                    String courriel, String commentaires, LocalDate dateProspection, int propsectEstInteresse) throws MonExceptionMaison {
        super(raisonSociale, ville,numeroRue, rue, codePostal, telephone, courriel, commentaires);

        compteurProspects++;
        setIdentifiant(compteurProspects);
        setDateProspection(dateProspection);
        setPropsectEstInteresse(propsectEstInteresse);
        ListeProspects.getListeTousProspects().add(this);
    }

    //TO STRING----------------------------------------------------------------------------------------------------

    @Override
    public String toString() {

        return super.toString()+
                ", ID= " + compteurProspects +
                ", dateProspection=" + dateProspection +
                ", PropsectEstInteresse='" + getPropsectEstInteresse() + '\'' +
                '}';
    }
}
