package entites;

import Exceptions.MonExceptionMaison;
import vues.VuesUtilitaires;

public abstract class Societe {

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
    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public String getRaisonSociale() {return raisonSociale;}

    /***
     *
     * @param raisonSociale
     * @throws MonExceptionMaison si nul vide blanc
     */
    public void setRaisonSociale(String raisonSociale) throws MonExceptionMaison {

        if ( (raisonSociale == null) || raisonSociale.isEmpty() || raisonSociale.isBlank()) {

            throw new MonExceptionMaison(VuesUtilitaires.MERCIDE + VuesUtilitaires.RAISONSOCIALE);
        }
        else {this.raisonSociale = raisonSociale;}
    }

    public String getNumeroRue() {return numeroRue;}

    /***
     *
     * @param numeroRue
     * @throws MonExceptionMaison si nul vide blanc
     */
    public void setNumeroRue(String numeroRue) throws MonExceptionMaison{

        if (  numeroRue == null || numeroRue.isEmpty() || numeroRue.isBlank()) {
            throw new MonExceptionMaison(VuesUtilitaires.MERCIDE + VuesUtilitaires.NUMERORUE) ;
        }
        else {this.numeroRue = numeroRue;}
    }

    public String getRue() {return rue;}

    /***
     *
     * @param rue
     * @throws MonExceptionMaison si nul vide blanc
     */
    public void setRue(String rue) throws MonExceptionMaison {

        if (  rue == null || rue.isEmpty() || rue.isBlank()) {

            throw new MonExceptionMaison( VuesUtilitaires.MERCIDE+ VuesUtilitaires.RUE);

        }
        else {this.rue = rue;}
    }

    public String getCodePostal() {return codePostal;}

    /***
     *
     * @param codePostal
     * @throws MonExceptionMaison si nul vide blanc
     */
    public void setCodePostal(String codePostal) throws MonExceptionMaison {

        if (  codePostal == null || codePostal.isEmpty() || codePostal.isBlank()) {
            throw new MonExceptionMaison( VuesUtilitaires.MERCIDE + VuesUtilitaires.CODEPOSTAL ) ;
        }
        else {this.codePostal = codePostal;}

    }

    public String getTelephone() {return telephone;}

    /***
     *
     * @param telephone
     * @throws MonExceptionMaison  si nul vide blanc
     */
    public void setTelephone(String telephone) throws MonExceptionMaison {

        if (telephone == null || telephone.isEmpty() || telephone.isBlank()) {
            throw new MonExceptionMaison( VuesUtilitaires.MERCIDE + VuesUtilitaires.TELEPHONE ) ;
        }

        else if (telephone.length() < 10 ){
            throw new MonExceptionMaison( VuesUtilitaires.MERCIDE + VuesUtilitaires.TELEPHONE ) ;
        };
        this.telephone = telephone;
    }

    public String getCourriel() {return courriel;}

    /***
     *
     * @param courriel
     * @throws MonExceptionMaison  si nul vide blanc
     */
    public void setCourriel(String courriel) throws MonExceptionMaison {

        if ( courriel == null || courriel.isEmpty() || courriel.isBlank()) {
            throw new MonExceptionMaison( VuesUtilitaires.MERCIDE + VuesUtilitaires.COURRIEL ) ;
        }

        if (  !courriel.contains("@") ){
            throw new MonExceptionMaison("Merci de bien vouloir rentrer un courriel contenant '@'.") ;
        }

        else this.courriel = courriel;
    }

    public String getCommentaires() {return commentaires;}
    public void setCommentaires(String commentaires) {this.commentaires = commentaires;}

    public String getVille() {return Ville;}

    /***
     *
     * @param ville
     * @throws MonExceptionMaison si nul vide blanc
     */
    public void setVille(String ville) throws MonExceptionMaison {
        if (ville == null || ville.isEmpty() || ville.isBlank()) {
            throw new MonExceptionMaison( VuesUtilitaires.MERCIDE + VuesUtilitaires.VILLE) ;
        }
        else
        {Ville = ville;}
    }

    //CONSTRUCTEURS ----------------------------------------------------------------------------------------------------
    public Societe(String raisonSociale, String Ville, String numeroRue, String rue, String codePostal, String telephone,
                   String courriel,String commentaires) throws MonExceptionMaison {

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
