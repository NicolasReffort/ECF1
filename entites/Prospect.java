package entites;

import Exceptions.MonExceptionEntites;
import Utilitaires.Outils;
import vues.VuesUtilitaires;

import java.time.LocalDate;

/***
 *  * Chaque entité correspondant à un prospect physique.
 */

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
    public static void setCompteurProspects(int compteurProspects) {Prospect.compteurProspects = compteurProspects;    }

    public LocalDate getDateProspection() {
        return dateProspection;
    }

    /***
     *
     * @param dateProspection bien typée au préalable
     */
    public void setDateProspection(LocalDate dateProspection) {
        this.dateProspection = dateProspection;}

    public String getPropsectEstInteresse() {return propsectEstInteresse;}

    /***
     * valeur issue de la conversion de la checkbox en OUI ou NON
     * @param propsectEstInteresse
     * @throws MonExceptionEntites si nul vide ou blanc
     */
    public void setPropsectEstInteresse(String propsectEstInteresse) throws MonExceptionEntites {

        if (propsectEstInteresse == null || propsectEstInteresse.trim().isEmpty()){
            throw new MonExceptionEntites(VuesUtilitaires.MERCIDE + VuesUtilitaires.EST_IL_INTERESSE) ;
        }

        else {
            this.propsectEstInteresse = propsectEstInteresse.toUpperCase();
        }

    }

    public Outils.TypeSociete getType() {
        return PROSPECT;
    }

    /***
     * nb : penser à ajouter dans la ListeProspect le cas échéant
     * @param raisonSociale
     * @param ville
     * @param numeroRue
     * @param rue
     * @param codePostal
     * @param telephone
     * @param courriel
     * @param commentaires
     * @param dateProspection
     * @param propsectEstInteresse
     * @throws MonExceptionEntites si intérêt propspect nul vide blanc. (ne devrait pas arriver)
     */
    //CONSTRUCTEURS----------------------------------------------------------------------------------------------------
    public Prospect(String raisonSociale, String ville, String numeroRue, String rue, String codePostal, String telephone,
                    String courriel, String commentaires, LocalDate dateProspection, String propsectEstInteresse,
                    int ancienidentifiantOuZero) throws MonExceptionEntites {
        super(raisonSociale, ville,numeroRue, rue, codePostal, telephone, courriel, commentaires);


        if (ancienidentifiantOuZero == 0) // veut dire que prospect est nouveau (jamais chargé auparavant).
        {
            compteurProspects++;
            setIdentifiant(compteurProspects);
        }

        else { //on reprend l'ancien numéro identifiant sans augmenter le compteur.
            setIdentifiant(ancienidentifiantOuZero);
        }

        setDateProspection(dateProspection);
        setPropsectEstInteresse(propsectEstInteresse);

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
