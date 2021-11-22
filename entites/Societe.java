package entites;

import Exceptions.MonExceptionEntites;
import vues.VuesUtilitaires;

import java.io.Serializable;

public abstract class Societe implements Serializable {

    //RELATIF AUX INSTANCES---------------------------------------------------------------------------------------------
    private int identifiant;
    private String raisonSociale;
    private String Ville;
    private String numeroRue;
    private String rue;
    private String codePostal;
    private String telephone;
    private String courriel;
    private String commentaires;

    //TO DO / FACTORISER LE TEST STRING BASIQUE DES SETTERS. §§§§§§§§§§§§§§

    //GETTERS ET SETTERS------------------------------------------------------------------------------------------------

    public int getIdentifiant() {return identifiant;}
    public void setIdentifiant(int identifiant) {this.identifiant = identifiant;}

    public String getRaisonSociale() {return raisonSociale;}

    /***
     *
     * @param raisonSociale
     * @throws MonExceptionEntites si nul vide blanc
     */
    public void setRaisonSociale(String raisonSociale) throws MonExceptionEntites {

        if ( (raisonSociale == null) || raisonSociale.trim().isEmpty() ) {

            throw new MonExceptionEntites(VuesUtilitaires.MERCIDE + VuesUtilitaires.RAISONSOCIALE);
        }
        else {this.raisonSociale = raisonSociale;}
    }

    public String getNumeroRue() {return numeroRue;}

    /***
     *
     * @param numeroRue
     * @throws MonExceptionEntites si nul vide blanc
     */
    public void setNumeroRue(String numeroRue) throws MonExceptionEntites {

        if (  numeroRue == null || numeroRue.trim().isEmpty() ) {
            throw new MonExceptionEntites(VuesUtilitaires.MERCIDE + VuesUtilitaires.NUMERORUE) ;
        }
        else {this.numeroRue = numeroRue;}
    }

    public String getRue() {return rue;}

    /***
     *
     * @param rue
     * @throws MonExceptionEntites si nul vide blanc
     */
    public void setRue(String rue) throws MonExceptionEntites {

        if (  rue == null || rue.trim().isEmpty() ) {

            throw new MonExceptionEntites( VuesUtilitaires.MERCIDE+ VuesUtilitaires.RUE);

        }
        else {this.rue = rue;}
    }

    public String getCodePostal() {return codePostal;}

    /***
     *
     * @param codePostal
     * @throws MonExceptionEntites si nul vide blanc
     */
    public void setCodePostal(String codePostal) throws MonExceptionEntites {

        if (  codePostal == null || codePostal.trim().isEmpty() ) {
            throw new MonExceptionEntites( VuesUtilitaires.MERCIDE + VuesUtilitaires.CODEPOSTAL ) ;
        }
        else {this.codePostal = codePostal;}

    }

    public String getTelephone() {return telephone;}

    /***
     *
     * @param telephone
     * @throws MonExceptionEntites  si nul vide blanc
     */
    public void setTelephone(String telephone) throws MonExceptionEntites {

        if (telephone == null || telephone.trim().isEmpty() ) {
            throw new MonExceptionEntites( VuesUtilitaires.MERCIDE + VuesUtilitaires.TELEPHONE ) ;
        }

        else if (telephone.length() < 10 ){
            throw new MonExceptionEntites( VuesUtilitaires.MERCIDE + VuesUtilitaires.TELEPHONE ) ;
        };
        this.telephone = telephone;
    }

    public String getCourriel() {return courriel;}

    /***
     *
     * @param courriel
     * @throws MonExceptionEntites  si nul vide blanc
     */
    public void setCourriel(String courriel) throws MonExceptionEntites {

        if ( courriel == null || courriel.trim().isEmpty()) {
            throw new MonExceptionEntites( VuesUtilitaires.MERCIDE + VuesUtilitaires.COURRIEL ) ;
        }

        if (  !courriel.contains("@") ){
            throw new MonExceptionEntites("Merci de bien vouloir rentrer un courriel contenant '@'.") ;
        }

        else this.courriel = courriel;
    }

    public String getCommentaires() {return commentaires;}
    public void setCommentaires(String commentaires) {this.commentaires = commentaires;}

    public String getVille() {return Ville;}

    /***
     *
     * @param ville
     * @throws MonExceptionEntites si nul vide blanc
     */
    public void setVille(String ville) throws MonExceptionEntites {
        if (ville == null || ville.trim().isEmpty() ) {
            throw new MonExceptionEntites( VuesUtilitaires.MERCIDE + VuesUtilitaires.VILLE) ;
        }
        else
        {Ville = ville;}
    }

    //CONSTRUCTEURS ----------------------------------------------------------------------------------------------------
    public Societe(String raisonSociale, String Ville, String numeroRue, String rue, String codePostal, String telephone,
                   String courriel,String commentaires) throws MonExceptionEntites {

        setRaisonSociale( raisonSociale);
        setVille(Ville);
        setNumeroRue(numeroRue);
        setRue(rue);
        setCodePostal(codePostal);
        setTelephone(telephone);
        setCourriel(courriel);
        setCommentaires(commentaires);
    }
    //TOSTRING----------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return                 ",raisonSociale='" + raisonSociale
                + '\''
                + "Ville"  + getVille()
                + "\n" +
                ", numeroRue='" + numeroRue + '\''
                + "\n" +
                ", rue='" + rue + '\''
                + "\n" +
                ", codePostal='" + codePostal + '\''
                + "\n" +
                ", telephone='" + telephone + '\''
                + "\n" +
                ", courriel='" + courriel + '\''
                + "\n" +
                ", commentaires='" + commentaires + '\''
                + "\n" +
                '}';
    }

}
