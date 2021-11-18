package entites;

import Exceptions.MonExceptionMaison;
import Utilitaires.Outils;
import vues.VuesUtilitaires;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Prospect extends Societe {

    //RELATIF A LA CLASSE-----------------------------------------------------------------------------------------------
    private static int compteurProspects = 0 ;




    //RELATIF AUX INSTANCES--------------------------------------------------------------------------------------------
    private LocalDate dateProspection;
    private String propsectEstInteresse;
    private Outils.TypeSociete PROSPECT ;


    //SETTERS ET GETTERS-----------------------------------------------------------------------------------------------
    public static int getCompteurProspects() {
        return compteurProspects;
    }

    public LocalDate getDateProspection() {
        return dateProspection;
    }
    public void setDateProspection(LocalDate dateProspection) throws MonExceptionMaison{
        this.dateProspection = dateProspection;}

    public String getPropsectEstInteresse() {return propsectEstInteresse;}

    /***
     * valeur issue de la conversion de la checkbox en OUI ou NON
     * @param propsectEstInteresse
     * @throws MonExceptionMaison si nul vide ou blanc
     */
    public void setPropsectEstInteresse(String propsectEstInteresse) throws MonExceptionMaison {

        if (propsectEstInteresse == null || propsectEstInteresse.isEmpty() || propsectEstInteresse.isBlank() ){
            throw new MonExceptionMaison(VuesUtilitaires.MERCIDE + VuesUtilitaires.EST_IL_INTERESSE) ;
        }

        else {
            this.propsectEstInteresse = propsectEstInteresse.toUpperCase();
        }

    }

    public Outils.TypeSociete getType() {
        return PROSPECT;
    }

    //CONSTRUCTEURS----------------------------------------------------------------------------------------------------
    public Prospect(String raisonSociale, String ville, String numeroRue, String rue, String codePostal, String telephone,
                    String courriel, String commentaires, LocalDate dateProspection, String propsectEstInteresse) throws MonExceptionMaison {
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
