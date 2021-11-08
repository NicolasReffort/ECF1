package entites;

import Utilitaires.MonExceptionMaison;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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


    //GETTERS ET SETTERS------------------------------------------------------------------------------------------------

    public int getIdentifiant() {return identifiant;}

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public String getRaisonSociale() {return raisonSociale;}

    public void setRaisonSociale(String raisonSociale) throws  MonExceptionMaison {

        if (raisonSociale.isBlank()) {
            throw new MonExceptionMaison("Merci de bien vouloir ne pas rentrer un champ vide ou nul") ;
        }
        else {this.raisonSociale = raisonSociale;}

    }

    public String getNumeroRue() {

        return numeroRue;}

    public void setNumeroRue(String numeroRue) throws MonExceptionMaison{

        if (numeroRue.isBlank() || numeroRue.isEmpty()) {
            throw new MonExceptionMaison("Merci de bien vouloir ne pas rentrer un numéro de rue vide ou nul") ;
        }
        else {this.numeroRue = numeroRue;}
    }

    public String getRue() {return rue;}

    public void setRue(String rue) throws MonExceptionMaison {

        if (rue.isBlank() || rue.isEmpty()) {
            throw new MonExceptionMaison("Merci de bien vouloir ne pas rentrer une Rue vide ou nul") ;
        }
        else {this.rue = rue;}
    }

    public String getCodePostal() {return codePostal;}

    public void setCodePostal(String codePostal) throws MonExceptionMaison {

        if (codePostal.isBlank() || codePostal.isEmpty()) {
            throw new MonExceptionMaison("Merci de bien vouloir ne pas rentrer un Code Postal vide ou nul") ;
        }
        else {this.codePostal = codePostal;}

    }

    public String getTelephone() {return telephone;}

    public void setTelephone(String telephone) throws MonExceptionMaison {

        if (telephone.isBlank() || telephone.isEmpty()) {
            throw new MonExceptionMaison("Merci de bien vouloir ne pas rentrer un numéro de téléphone vide ou nul") ;
        }

        else if (telephone.length() < 10 ){
            throw new MonExceptionMaison("Merci de bien vouloir rentrer un numéro de téléphone d'au moins 10 caractères") ;
        };

        this.telephone = telephone;
    }

    public String getCourriel() {return courriel;}
    public void setCourriel(String courriel) throws MonExceptionMaison {

        if (courriel.isBlank() || courriel.isEmpty()) {
            throw new MonExceptionMaison("Merci de bien vouloir ne pas rentrer un courriel vide ou nul") ;
        }

        if (  !courriel.contains("@") ){
            throw new MonExceptionMaison("Merci de bien vouloir rentrer un courriel contenant '@'.") ;
        }

        this.courriel = courriel;
    }

    public String getCommentaires() {return commentaires;}
    public void setCommentaires(String commentaires) {
        try{
            this.commentaires = commentaires;
        }
        catch (NullPointerException nullPointerException){}

    }

    public String getVille() {return Ville;}
    public void setVille(String ville) throws MonExceptionMaison {
        if (ville.isBlank()) {throw new MonExceptionMaison("Le champ Ville est vide.") ;
        }
        else
        {Ville = ville;}
    }

    //CONSTRUCTEURS ----------------------------------------------------------------------------------------------------
    public Societe(String raisonSociale, String Ville, String numeroRue, String rue, String codePostal, String telephone,
                   String courriel,String commentaires) throws MonExceptionMaison {
        setRaisonSociale( raisonSociale);
        setNumeroRue(numeroRue);
        setRue(rue);
        setCodePostal(codePostal);
        setTelephone(telephone);
        setCourriel(courriel);
        setCommentaires(commentaires);
    }

    //TESTEURS DE ...-----------------------------------------------------------------------------------------------

    /***
     *
     * @param nouvelleValeurSouhaitee String récupéré par la vue
     * @return le string converti en entier
     * @throws MonExceptionMaison
     */
    public int StringToInt (String nouvelleValeurSouhaitee) throws MonExceptionMaison{

        try {
            return Integer.parseInt(nouvelleValeurSouhaitee);
        }

        catch (NumberFormatException nfe) {
            throw new MonExceptionMaison("Ceci ne constitue pas un entier valide (attendu = entier positif)");
        }

    }

    /***
     *
     * @param nouvelleValeurSouhaitee String récupéré par la vue
     * @return Un Double
     * @throws MonExceptionMaison
     */
    public Double StringToDouble (String nouvelleValeurSouhaitee) throws MonExceptionMaison{

        try {
            return Double.parseDouble(nouvelleValeurSouhaitee);
        }

        catch (NumberFormatException nfe) {
            throw new MonExceptionMaison("Ceci ne constitue pas un nombre valide (attendu = nombre double");
        }

    }

    public LocalDate StringToLocalDate(String nouvelleValeurSouhaitee)throws MonExceptionMaison{

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return LocalDate.parse(nouvelleValeurSouhaitee, formatter);
        }

        catch (DateTimeParseException dtpe) {
            throw new MonExceptionMaison("Ceci ne constitue pas une date au format valide (attendu = format dd-MM-yyyy");
        }
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
