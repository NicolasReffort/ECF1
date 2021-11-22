package Utilitaires;

import Exceptions.MonExceptionEntites;
import entites.*;
import vues.VuesUtilitaires;

import java.io.*;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Outils {

    //RELATIF A LA CLASSE
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER_FICHIER_LOG
            = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public static DateTimeFormatter getDateTimeFormatter() {        return DATE_TIME_FORMATTER;}
    public static final String NOM_FICHIER_CLIENT_TXT = "persistanceDesDonnéesCLIENT.txt";
    public static final String NOM_FICHIER_PROSPECT_TXT = "persistanceDesDonnéesPROSPECT.txt";
    public static final String NOM_FICHIERS_LOG = "LOG.txt";

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
            throw new MonExceptionEntites( VuesUtilitaires.MERCIDE + "date (format JJ/MM/AAAA ) ");
        }
    }

    /***
     * CHARGER DONNNES CLIENT ET PROSPECT : FICHIER --> COLLECTION
     * appelé au démarrage de chaque accueil
     * @throws IOException si fichier absent
     * @throws MonExceptionEntites si fichier corrompu
     */
    public static void chargerDonnes() throws IOException, MonExceptionEntites {

        // VIDER LES COLLECTIONS SINON ON CUMULE LES CHARGEMENTS
        ListeClients.getListeTousClients().removeAll(ListeClients.getListeTousClients());
        ListeProspects.getListeTousProspects().removeAll(ListeProspects.getListeTousProspects());

        try {
            FileInputStream monFileInputStream = new FileInputStream(NOM_FICHIER_CLIENT_TXT);
            ObjectInputStream monObjectIntputStream = new ObjectInputStream(monFileInputStream);

            List<Client> listeLoaded = (List<Client>) monObjectIntputStream.readObject(); //casting suite lecture object
            ListeClients.setListeTousClients(listeLoaded);

            monObjectIntputStream.close();


        } catch (IOException ioe ){
            ioe.printStackTrace();
        }

        catch (Exception e){
            throw new MonExceptionEntites("L'accès à notre base de données est momentanément interrompu. " +
                    "Merci de bien vouloir contacter votre responsable informatique");

        }

        try {
            FileInputStream monFileInputStream = new FileInputStream(NOM_FICHIER_PROSPECT_TXT);
            ObjectInputStream monObjectIntputStream = new ObjectInputStream(monFileInputStream);


            List<Prospect> listeLoaded = (List<Prospect>) monObjectIntputStream.readObject();
            ListeProspects.setListeTousProspects(listeLoaded);

            monObjectIntputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            throw new MonExceptionEntites("L'accès à notre base de données est momentanément interrompu. " +
                    "Merci de bien vouloir contacter votre responsable informatique");

        }


        // anciens compteur Repris
        try {
            int lastId = ListeClients.ConnaitreDernierIdAttribue();
            Client.setCompteurClients(lastId);
        }
        catch (MonExceptionEntites mee){
            EnregistrerEnLog("Pas possible de setter le compteur client ");
        }

        try {
            int lastIdProspect = ListeProspects.ConnaitreDernierIdAttribue();
            Prospect.setCompteurProspects(lastIdProspect);
        }
        catch (MonExceptionEntites mee){
            EnregistrerEnLog("Pas possible de setter le compteur Prospect");
        }

        MajCompteursEntites();// mAJ compteur entité

    }

    /***
     *
     * @param typeSociete liste d'entités à sauvegarder
     * @throws MonExceptionEntites si pb connexion base
     */
    public static void sauvegarderDonnees(TypeSociete typeSociete) throws MonExceptionEntites {

        try {

            if (typeSociete == TypeSociete.CLIENT) {

                //CLIENT
                    FileOutputStream monFileOutputStream = new FileOutputStream(NOM_FICHIER_CLIENT_TXT);
                    ObjectOutputStream monObjectOutputStream = new ObjectOutputStream(monFileOutputStream);
                    monObjectOutputStream.writeObject(ListeClients.getListeTousClients());
                    monObjectOutputStream.close();
                    monFileOutputStream.close();
                    EnregistrerEnLog("\n Ecriture  " + NOM_FICHIER_CLIENT_TXT +  "terminée avec succès...\n");

            }

            else {

                //PROSPECT
                    FileOutputStream monFileOutputStream = new FileOutputStream(NOM_FICHIER_PROSPECT_TXT);
                    ObjectOutputStream monObjectOutputStream = new ObjectOutputStream(monFileOutputStream);
                    monObjectOutputStream.writeObject(ListeProspects.getListeTousProspects());
                    monObjectOutputStream.close();
                    monFileOutputStream.close();
                    EnregistrerEnLog("\nEcriture sur  " + NOM_FICHIER_PROSPECT_TXT +  "terminée avec succès...\n");

            }

        }

        catch (FileNotFoundException e) {
            EnregistrerEnLog("Fichier non trouvé.");


        }
        catch (IOException e) {
            EnregistrerEnLog(e.getLocalizedMessage());
        }
        catch (Exception e){
            throw new MonExceptionEntites("L'accès à notre base de données est momentanément interrompu. " +
                        "Merci de bien vouloir contacter votre responsable informatique");
        }


    }

    public static void MajCompteursEntites(){

        // anciens compteurRepris
        try {
            int lastIdClient = ListeClients.ConnaitreDernierIdAttribue();
            Client.setCompteurClients(lastIdClient);
            EnregistrerEnLog("Compteur client remis à " + lastIdClient );
        }
        catch (MonExceptionEntites mee){
            EnregistrerEnLog("Pas possible de reprendre le compteur client");
        }

        try {
            int lastIdProspect = ListeProspects.ConnaitreDernierIdAttribue();
            Prospect.setCompteurProspects(lastIdProspect);
            EnregistrerEnLog("compteur prospect remis a " + lastIdProspect );

        }
        catch (MonExceptionEntites mee){
            EnregistrerEnLog("Pas possible de reprendre le compteur prospect");
        }
    }

    /***
     * Enregistrer des infos dans le fichier log
     */
    public static void EnregistrerEnLog(String messageAenregistrer){

        try {
            FileOutputStream monFileOutputStream = new FileOutputStream(NOM_FICHIERS_LOG);
            ObjectOutputStream monObjectOutputStream = new ObjectOutputStream(monFileOutputStream);

            monObjectOutputStream.writeObject(messageAenregistrer); // écriture message
            LocalDate date = LocalDate.now();
            monObjectOutputStream.writeObject(date.format(DATE_TIME_FORMATTER_FICHIER_LOG));

            monObjectOutputStream.close();
            monFileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
