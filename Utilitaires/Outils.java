package Utilitaires;

import Exceptions.MonExceptionEntites;
import entites.*;
import vues.VuesUtilitaires;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Outils {

    //RELATIF A LA CLASSE
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static DateTimeFormatter getDateTimeFormatter() {        return DATE_TIME_FORMATTER;}
    public static final String NOMFICHIERTXT = "persistanceDesDonnées.txt";

    //ENUMS
    public enum TypeSociete{CLIENT,PROSPECT}


    //TESTEUR DE ...-----------------------------------------------------------------------------------------------
    /***
     *
     * @param string LocalDate en String dont on veut vérifier le bon typage
     * @return Une LocalDate bien typée
     * @throws MonExceptionEntites Si mauvais format ou Npe
     */
    public static LocalDate StringToLocalDate(String string)throws MonExceptionEntites {

        try {
            return LocalDate.parse(string, getDateTimeFormatter());
        }

        catch (NullPointerException npe ){
            throw new MonExceptionEntites(VuesUtilitaires.MERCIDE + VuesUtilitaires.DATEDEPROSPECTION);
        }
        catch (IllegalArgumentException iae){
            throw new MonExceptionEntites( VuesUtilitaires.MERCIDE + "date (argument inadéquat) ");
        }
        catch (DateTimeParseException dtpe){
            throw new MonExceptionEntites( VuesUtilitaires.MERCIDE + "date (format jj-mm-aaaa) ");
        }
    }

    /***
     * CHARGER DONNNES CLIENT ET PROSPECT : FICHIER --> COLLECTION
     * appelé au démarrage de chaque accueil
     * @throws IOException
     * @throws MonExceptionEntites
     */
    public static void chargerDonnes() throws IOException, MonExceptionEntites {

        FileReader fichierSauvegarde = new FileReader(NOMFICHIERTXT);

        /***
         * LECTURE ET CHARGEMENT
         */
        try (BufferedReader monbufferedReader =new BufferedReader(fichierSauvegarde)) //fichier à charger
        {
            String ligne;

            // VIDER LES COLLECTIONS SINON ON CUMULE LES CHARGEMENTS
            // CHERCHER LE DERNIER IDENTIFIANT DANS LE FICHIER
            ListeClients.getListeTousClients().removeAll(ListeClients.getListeTousClients());
            ListeProspects.getListeTousProspects().removeAll(ListeProspects.getListeTousProspects());

            while (  ( ligne = monbufferedReader.readLine() ) != null ) {

                if (ligne.equals("*C")) {


                    try {
                        ListeClients.getListeTousClients().add(new Client(
                                monbufferedReader.readLine(), //raison
                                monbufferedReader.readLine(), //ville
                                monbufferedReader.readLine(), //n° rue
                                monbufferedReader.readLine(), // rue
                                monbufferedReader.readLine(), //CP
                                monbufferedReader.readLine(), //tém
                                monbufferedReader.readLine(), //courriel
                                monbufferedReader.readLine(),   // commentaires
                                Double.parseDouble(monbufferedReader.readLine()), //CA TO DO mettre en try catch
                                Integer.parseInt(monbufferedReader.readLine()  ), //nb employés
                                Integer.parseInt(monbufferedReader.readLine() ) //ancien ID repris
                        )
                        );
                    }
                    catch (MonExceptionEntites e) {
                        e.printStackTrace();
                        System.exit(404);
                    }
                }

                if (ligne.equals("*P")) {
                    try {
                        ListeProspects.getListeTousProspects().add(new Prospect(
                                monbufferedReader.readLine(), //raison
                                monbufferedReader.readLine(), //ville
                                monbufferedReader.readLine(), //n° rue
                                monbufferedReader.readLine(), // rue
                                monbufferedReader.readLine(), //CP
                                monbufferedReader.readLine(), //tém
                                monbufferedReader.readLine(), //courriel
                                monbufferedReader.readLine(),   // commentaires
                                Outils.StringToLocalDate(monbufferedReader.readLine()),
                                monbufferedReader.readLine(), // Intéressé O/n
                                Integer.parseInt(monbufferedReader.readLine() ) // ancien ID

                        )
                        );
                    } catch (MonExceptionEntites mem) {
                        mem.printStackTrace();
                        System.exit(404);
                    }
                }


            } //fin while
        }
        catch(FileNotFoundException e){
            System.out.println("Erreur lecture " + "la sauvegarde " + fichierSauvegarde + "n'existe pas.");
        }

        // anciens compteur Repris
        try {
            int lastId = ListeClients.ConnaitreDernierIdAttribue();
            Client.setCompteurClients(lastId);
        }
        catch (MonExceptionEntites mee){
            System.out.println("La liste client nest pas remplie je pense");
        }

        try {
            int lastIdProspect = ListeProspects.ConnaitreDernierIdAttribue();
            Prospect.setCompteurProspects(lastIdProspect);
        }
        catch (MonExceptionEntites mee){
            System.out.println("La liste nest pas remplie je pense");
        }

    }

    /***
     * SAUVEGARDER DONNES CLIENT PROSPECT : COLLECTION --> FICHIER
     * appelé à chaque Edition( buttons créer modif supprimer);
     */
    public static void sauvegarderDonnees(){
        /***
         * ECRITURE POUR ENREGISTREMENT
         */

        File fichierSauvegarde = new File(NOMFICHIERTXT);

        try (BufferedWriter monbufferedWriter =new BufferedWriter(new FileWriter(fichierSauvegarde)) )
        {

            for(int i = 0; i < ListeClients.getListeTousClients().size(); i++)
            {
                monbufferedWriter.write("*C");
                monbufferedWriter.newLine();
                monbufferedWriter.write(ListeClients.getListeTousClients().get(i).getRaisonSociale() ) ;
                monbufferedWriter.newLine();
                monbufferedWriter.write(ListeClients.getListeTousClients().get(i).getVille() ) ;
                monbufferedWriter.newLine();
                monbufferedWriter.write(ListeClients.getListeTousClients().get(i).getNumeroRue() ) ;
                monbufferedWriter.newLine();
                monbufferedWriter.write(ListeClients.getListeTousClients().get(i).getRue() ) ;
                monbufferedWriter.newLine();
                monbufferedWriter.write(ListeClients.getListeTousClients().get(i).getCodePostal() ) ;
                monbufferedWriter.newLine();
                monbufferedWriter.write(ListeClients.getListeTousClients().get(i).getTelephone() ) ;
                monbufferedWriter.newLine();
                monbufferedWriter.write(ListeClients.getListeTousClients().get(i).getCourriel() ) ;
                monbufferedWriter.newLine();
                monbufferedWriter.write(ListeClients.getListeTousClients().get(i).getCommentaires() ) ;
                monbufferedWriter.newLine();
                monbufferedWriter.write(ListeClients.getListeTousClients().get(i).getCA().toString() ) ;
                monbufferedWriter.newLine();
                monbufferedWriter.write(String.valueOf(ListeClients.getListeTousClients().get(i).getNbEmployes())) ;
                monbufferedWriter.newLine();
                monbufferedWriter.write( String.valueOf(ListeClients.getListeTousClients().get(i).getIdentifiant()) ) ; // ID enregistré
                monbufferedWriter.newLine();

            }

            for(int i = 0; i < ListeProspects.getListeTousProspects().size(); i++)

            {
                monbufferedWriter.write("*P");
                monbufferedWriter.newLine();
                monbufferedWriter.write(ListeProspects.getListeTousProspects().get(i).getRaisonSociale() ) ;
                monbufferedWriter.newLine();
                monbufferedWriter.write(ListeProspects.getListeTousProspects().get(i).getVille() ) ;
                monbufferedWriter.newLine();
                monbufferedWriter.write(ListeProspects.getListeTousProspects().get(i).getNumeroRue() ) ;
                monbufferedWriter.newLine();
                monbufferedWriter.write(ListeProspects.getListeTousProspects().get(i).getRue() ) ;
                monbufferedWriter.newLine();
                monbufferedWriter.write(ListeProspects.getListeTousProspects().get(i).getCodePostal() ) ;
                monbufferedWriter.newLine();
                monbufferedWriter.write(ListeProspects.getListeTousProspects().get(i).getTelephone() ) ;
                monbufferedWriter.newLine();
                monbufferedWriter.write(ListeProspects.getListeTousProspects().get(i).getCourriel() ) ;
                monbufferedWriter.newLine();
                monbufferedWriter.write(ListeProspects.getListeTousProspects().get(i).getCommentaires() ) ;
                monbufferedWriter.newLine();
                monbufferedWriter.write(ListeProspects.getListeTousProspects().get(i).getDateProspection().format(Outils.getDateTimeFormatter()));
                monbufferedWriter.newLine();
                monbufferedWriter.write(ListeProspects.getListeTousProspects().get(i).getPropsectEstInteresse());
                monbufferedWriter.newLine();
                monbufferedWriter.write( String.valueOf(ListeProspects.getListeTousProspects().get(i).getIdentifiant()) ) ; // ID enregistré
                monbufferedWriter.newLine();

            }

        }
        catch (IOException e)
        {
            System.out.println("Echec écriture prospect");
        }



    }

    public static void enregistrerDernierIdentifiant(){

        // anciens compteurRepris
        try {
            int lastId = ListeClients.ConnaitreDernierIdAttribue();
            Client.setCompteurClients(lastId);
        }
        catch (MonExceptionEntites mee){
            System.out.println("La liste client nest pas remplie je pense");
        }

        try {
            int lastIdProspect = ListeProspects.ConnaitreDernierIdAttribue();
            Prospect.setCompteurProspects(lastIdProspect);
        }
        catch (MonExceptionEntites mee){
            System.out.println("La liste nest pas remplie je pense");
        }
    }



}
