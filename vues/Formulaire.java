package vues;

import Exceptions.MonExceptionEntites;
import Utilitaires.Outils;
import entites.*;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;

public class Formulaire extends JFrame {

    private JPanel contentPaneFormulaire;
    private JButton buttonOk;
    private JButton buttonCancel;
    private JTextField RaisonSocialeTextField;
    private JTextField CodePostalTextField;
    private JTextField VilleTextField;
    private JTextField NumeroRueTextField;
    private JTextField RueTextField;
    private JTextField TelephoneTextField;
    private JTextField CourrielTextField;
    private JTextField attributFilleTexteField1;
    private JTextField attributFilleTexteField2;
    private JPanel champs;
    private JPanel nomDesChamps;
    private JTextField champFille1;
    private JTextField champRaisonSociale;
    private JTextField champVille;
    private JTextField champCodePostal;
    private JTextField champNumeroRue;
    private JTextField champRue;
    private JTextField champTelephone;
    private JTextField champCourriel;
    private JTextField CommentairesTexteField;
    private JTextField champFille2;
    private JTextField champCommentaires;
    private JTextField IdTextField;
    private JTextField champID;
    private JCheckBox checkBox1;

    private String reponseCheckBox; // Si coché, prospect intéressé = OUI

    public JTextField[] champsCommuns = { champRaisonSociale, champVille, champNumeroRue, champRue, champCodePostal,
    champTelephone, champCourriel, champCommentaires} ; //utilité de mettre en private ?

    public JTextField[] champsFilles = { champFille1, champFille2};

    private Double CAenDouble; // ATTRIBUT  POUR JONGLER + FACILEMENT DANS LA PAGE
    public Double getCAenDouble() {return CAenDouble;}
    private void setCAenDouble(Double CAenDouble) {
        this.CAenDouble = CAenDouble;}

    private int NbEmployesInt;// ATTRIBUT  POUR JONGLER + FACILEMENT DANS LA PAGE
    private void setNbEmployesInt(int nbEmployesInt) {NbEmployesInt = nbEmployesInt;}
    public int getNbEmployesInt() {return NbEmployesInt;    }

    private LocalDate dateProspection;// // ATTRIBUT  POUR JONGLER + FACILEMENT DANS LA PAGE
    public LocalDate getDateProspection() {return dateProspection;}
    public void setDateProspection(LocalDate dateProspection) {this.dateProspection = dateProspection;}

    private Client client ; // ATTRIBUT  POUR JONGLER + FACILEMENT DANS LA PAGE
    public Client getClient() {return client;}
    public void setClient(Client client) {this.client = client;    }

    private Prospect prospect ; // ATTRIBUT FPOUR JONGLER + FACILEMENT DANS LA PAGE
    public Prospect getProspect() {return prospect;}
    public void setProspect(Prospect prospect) {this.prospect = prospect;}

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //PREMIER CONSTRUCTEUR : LA FONCTION CREER -------------------------------------------------------------------------

    /***
     * Construit un formulaire de création adapté au type de société.
     * @param typeSociete
     */
    public Formulaire(Outils.TypeSociete typeSociete) {

       VuesUtilitaires.PreparerlaPage(this ,contentPaneFormulaire);
       RemplirNomsChampsFilles(typeSociete, attributFilleTexteField1, attributFilleTexteField2,
               checkBox1, champID);

       if (typeSociete == Outils.TypeSociete.PROSPECT){
           champFille2.setVisible(false); // pas besoin de ce champ, on a une checkox.

       }

       buttonOk.setText("CRÉER CE" + typeSociete.toString().toUpperCase());

        //ACTIONS SUR OK
        buttonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // ....si c'est un client ----------------------------
                if (typeSociete == Outils.TypeSociete.CLIENT) {

                    try {
                        // on teste le typage correct des variables
                        testerTypesAttributsFille(typeSociete);// attributs bien typés stockés en vi de la frame
                        //et on essaie de setter
                        ListeClients.ajouterListeClients(new Client(champRaisonSociale.getText(),
                                champVille.getText(), champNumeroRue.getText(),
                                champRue.getText(),champCodePostal.getText(), champTelephone.getText(),
                                champCourriel.getText(),champCommentaires.getText(),
                                getCAenDouble() , getNbEmployesInt()
                        ) ) ;
                        onCancel();
                    }
                    catch (MonExceptionEntites mem) {
                        JOptionPane.showMessageDialog(null, mem.getMessage());
                    }
                    catch (Exception ex){
                        JOptionPane.showMessageDialog(null, VuesUtilitaires.ERREUR_INCONNUE);
                        System.out.println(ex.getMessage());
                        System.exit(404);
                    }
                }
                // ... ou un prospect ----------------------------
                else {

                    if (checkBox1.isSelected()){
                        reponseCheckBox = "OUI";
                    }
                    else {reponseCheckBox = "NON";}

                    try {
                        testerTypesAttributsFille(typeSociete);
                        ListeProspects.ajouterListeProspects(new Prospect( champRaisonSociale.getText(),
                                champVille.getText(),
                                champNumeroRue.getText(),
                                champRue.getText(),
                                champCodePostal.getText(),
                                champTelephone.getText(),
                                champCourriel.getText(),
                                champCommentaires.getText(),
                                getDateProspection(),
                                reponseCheckBox
                        ));
                        onCancel();
                    }
                    catch (MonExceptionEntites mem) {
                        JOptionPane.showMessageDialog(null, mem.getMessage());
                    }
                    catch (Exception ex){
                        JOptionPane.showMessageDialog(null, VuesUtilitaires.ERREUR_INCONNUE);
                        System.out.println(ex.getMessage());
                        System.exit(404);
                    }


                }
            }
        });

        //FORMULAIRE DEVIENT VISIBLE
        setVisible(true);

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();

            }
        });

        // when cross is clicked
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {onCancel();}
        }
        );
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    // DEUXIEME CONSTRUCTEUR : MODIFICATION/SUPPESSION -----------------------------------------------------------------

    /***
     * Construit un formulaire de modification ou suppression pour une société.
     * @param societe Société choisie (dans la combo box)
     * @param supprimerOuModifier Motclé pour connaître la marche à suivre
     */
    public Formulaire(String supprimerOuModifier, Societe societe) {

        // chargement du formulaire et du content aux dimensions voulues.
        VuesUtilitaires.PreparerlaPage(this, contentPaneFormulaire);

        JOptionPane.showMessageDialog(null, "Vous avez sélectionné "
                + societe.getRaisonSociale());

        //remplissage des champs formulaire et de leur contenu pour les attributs commun à toutes les sociétés
        RemplirChampsCommunsFormulaire(societe);

        //Casting société arrivante
        if (societe instanceof Client) {
            Client clientCaste = ((Client)societe);
            setClient(clientCaste); // stockage résulat en vi dans la frame
            RemplirNomsChampsFilles( Outils.TypeSociete.CLIENT, attributFilleTexteField1,
                    attributFilleTexteField2, checkBox1
            );
            RemplirContenuChampsFilles(getClient(), champFille1, champFille2);
        }
        else
        {
            Prospect prospectCaste = ((Prospect)societe);
            setProspect(prospectCaste);
            RemplirNomsChampsFilles( Outils.TypeSociete.PROSPECT , attributFilleTexteField1,
                    attributFilleTexteField2, checkBox1);
            champFille2.setVisible(false); // on n'a pas besoin d'afficher ce champ, on a une checkbox pour ça.
            RemplirContenuChampsFilles(getProspect(), champFille1, checkBox1);
        }

        //-------------------MODIFICATION---------------------------------------
        if(supprimerOuModifier.equals("modifier")) {

            buttonOk.setText("MODIFIER " + societe.getRaisonSociale().toUpperCase()  );

            //.....d'un client
            if (societe instanceof Client) {

                //ACTIONS DU BOUTON OK
                buttonOk.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        try {
                            // on teste le typage correct des variables
                             testerTypesAttributsFille(Outils.TypeSociete.CLIENT);
                             
                             //et on essaie de setter
                             client.setRaisonSociale(champRaisonSociale.getText());
                             client.setVille(champVille.getText());
                             client.setCodePostal(champCodePostal.getText());
                             client.setNumeroRue(champNumeroRue.getText());
                             client.setRue(champRue.getText());
                             client.setTelephone(champTelephone.getText());
                             client.setCourriel(champCourriel.getText());
                             client.setCommentaires(champCommentaires.getText());
                             client.setCA(getCAenDouble());
                             client.setNbEmployes(getNbEmployesInt());

                             onCancel();

                        }
                        catch (MonExceptionEntites mem) {
                            JOptionPane.showMessageDialog(null, mem.getMessage());
                        }
                        catch (Exception ex){
                            JOptionPane.showMessageDialog(null, VuesUtilitaires.ERREUR_INCONNUE);
                            System.out.println(ex.getMessage());
                            System.exit(404);
                        }
                    }
                });

        }

        //.... ou d'un prospect:
        else {

            //Action du bouton OK
            buttonOk.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    if (checkBox1.isSelected()){
                        reponseCheckBox = "OUI";
                    }
                    else {reponseCheckBox = "NON";}

                    //ESSAYER DE MODIFIER AVEC LES DONNEES SAISIES ET LES VERIFICATIONS DU TYPAGE FAITES PAR L AFFICHAGE
                    try {
                        // on teste le typage correct des variables
                        testerTypesAttributsFille(Outils.TypeSociete.PROSPECT);

                        //tentative de setter
                        prospect.setRaisonSociale(champRaisonSociale.getText());
                        prospect.setVille(champVille.getText());
                        prospect.setCodePostal(champCodePostal.getText());
                        prospect.setNumeroRue(champNumeroRue.getText());
                        prospect.setRue(champRue.getText());
                        prospect.setTelephone(champTelephone.getText());
                        prospect.setCourriel(champCourriel.getText());
                        prospect.setDateProspection(getDateProspection());
                        prospect.setPropsectEstInteresse(reponseCheckBox); // O/N

                        onCancel();
                    }
                    catch (MonExceptionEntites mem) {
                        JOptionPane.showMessageDialog(null, mem.getMessage());
                    }
                    catch (Exception ex){
                        JOptionPane.showMessageDialog(null, VuesUtilitaires.ERREUR_INCONNUE);
                        System.out.println(ex.getMessage());
                        System.exit(404);
                    }
                    ;
                }
            });

        }
        }

        //-------------------SUPPRESSION---------------------------------------
        if(supprimerOuModifier.equals("supprimer")) {

            buttonOk.setText("SUPPRIMER " + societe.getRaisonSociale().toUpperCase());

            RendreContenuChampsEditable(false, champsCommuns);
            RendreContenuChampsEditable(false, champsFilles);
            checkBox1.setVisible(false); // on cache la check box inutile


            buttonOk.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    if ( JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer"
                            + societe.getRaisonSociale() + "?" )  ==  JOptionPane.YES_OPTION )
                    {
                        if (societe instanceof Client) {
                            deleteThat(client);
                        }

                        else{
                            deleteThat(prospect);
                        }
                    }
                    onCancel();
                };
            }
            );
        }

        //FORMULAIRE DEVIENT VISIBLE
        setVisible(true);

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();

            }
        });

        // when cross is clicked
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {onCancel();            }
        });
    }

    //FIN DEUXIEME CONSTRUCTEUR ***

    private void onCancel() {
        Accueil accueil = new Accueil();
        dispose();
    }

    /***
     * Remplissage du nom des champs, pas leur contenu.
     * @param societe
     */
    public void RemplirChampsCommunsFormulaire(Societe societe){

        champRaisonSociale.setText(societe.getRaisonSociale());
        champVille.setText(societe.getVille());
        champCodePostal.setText(societe.getCodePostal());
        champNumeroRue.setText(societe.getNumeroRue());
        champRue.setText(societe.getRue());
        champTelephone.setText(societe.getTelephone());
        champCourriel.setText(societe.getCourriel());
        champCommentaires.setText(societe.getCommentaires());
        champID.setText( Integer.toString(societe.getIdentifiant()));
    }

    /***
     * Vérifie que les attributs filles sont bien typés.
     * ((inutilement complex de le stocker dans VuesUtili.))
     * @param typeSociete
     * @throws MonExceptionEntites si la date de prospection est nulle ou au mauvais format
     * @throws NumberFormatException si CA/NB employés = mauvais type
     * @throws NullPointerException
     */
    public void testerTypesAttributsFille(Outils.TypeSociete typeSociete) throws MonExceptionEntites              {

        if (typeSociete == Outils.TypeSociete.CLIENT) {

            try {
                setCAenDouble(Double.parseDouble(champFille1.getText()));
            }
            catch (NullPointerException | NumberFormatException npe ){
                throw new MonExceptionEntites(VuesUtilitaires.MERCIDE+VuesUtilitaires.CHIFFRESDAFFAIRES);
            }

            try {
                setNbEmployesInt(Integer.parseInt(champFille2.getText()));
            }
            catch (NumberFormatException nfe ){
                throw new MonExceptionEntites(VuesUtilitaires.MERCIDE+VuesUtilitaires.NB_EMPLOYES);
            }

        }

        else {
            setDateProspection(Outils.StringToLocalDate(champFille1.getText()));
        }
    }

    /***
     * Pour création Prospect Client // factorisable avec l'autre avec un paramètre pour compteurclient+1??
     * @param typeSociete
     * @param attributFilleTexteField1
     * @param attributFilleTexteField2
     * @param champId champ spécialement généré pour la création
     * @param checkbox
     */
    public static void RemplirNomsChampsFilles(Outils.TypeSociete typeSociete,
                                               JTextField attributFilleTexteField1,
                                               JTextField attributFilleTexteField2,
                                               JCheckBox checkbox,
                                               JTextField champId){

        switch (typeSociete){

            case CLIENT :
                attributFilleTexteField1.setText(VuesUtilitaires.CHIFFRESDAFFAIRES.toUpperCase());
                attributFilleTexteField2.setText(VuesUtilitaires.NB_EMPLOYES.toUpperCase());

                // On affiche le futur identifiant de la Fille à créer
                champId.setText( Integer.toString(Client.getCompteurClients() + 1 ));

                //pas besoin d'afficher la checkbox
                checkbox.setVisible(false);
                ;
                break;


            case PROSPECT:
                attributFilleTexteField1.setText(VuesUtilitaires.DATEDEPROSPECTION.toUpperCase());
                champId.setText( Integer.toString(Prospect.getCompteurProspects() + 1 ));;
                checkbox.setText("EST INTERESSE.");

                //pas besoin d'afficher les champs pour la date de prospection, la checkbox suffit.
                attributFilleTexteField2.setVisible(false);


                break;
        }
    }

    /***
     * Pour Modifi/Supp Client-Prospect
     * @param typeSociete
     * @param attributFilleTexteField1
     * @param attributFilleTexteField2
     * @param checkbox
     * @param
     */
    public static void RemplirNomsChampsFilles(Outils.TypeSociete typeSociete,
                                               JTextField attributFilleTexteField1,
                                               JTextField attributFilleTexteField2,
                                               JCheckBox checkbox){

        switch (typeSociete){

            case CLIENT :
                attributFilleTexteField1.setText(VuesUtilitaires.CHIFFRESDAFFAIRES.toUpperCase());
                attributFilleTexteField2.setText(VuesUtilitaires.NB_EMPLOYES.toUpperCase());
                //pas besoin d'afficher la checkbox
                checkbox.setVisible(false);
                break;


            case PROSPECT:
                attributFilleTexteField1.setText(VuesUtilitaires.DATEDEPROSPECTION.toUpperCase());
                checkbox.setText("EST INTERESSE.");

                //pas besoin d'afficher les champs pour la date de prospection, la checkbox suffit.
                attributFilleTexteField2.setVisible(false);
                break;
        }
    }

    /***
     * Charge le contenu des champs spécifiques au client
     * @param client
     * @param champFille1 C.a
     * @param champFille2 nb employes
     */
    public static void RemplirContenuChampsFilles(Client client, JTextField champFille1, JTextField champFille2){

        champFille1.setText(String.valueOf(client.getCA()));
        champFille2.setText(String.valueOf(client.getNbEmployes()));
    }

    /***
     *Charge le contenu des champs spécifiques au prospect
     * @param prospect
     * @param champFille1 date prospection
     * @param checkbox pour changer l'intéressement du prospect OUI/NON
     */
    public static void RemplirContenuChampsFilles(Prospect prospect, JTextField champFille1,
                                                  JCheckBox checkbox){

        champFille1.setText( (prospect.getDateProspection().format(Outils.getDateTimeFormatter()) ));

        if(prospect.getPropsectEstInteresse().equals("OUI")){ //charger checkbox avec le choix enregistré.
            checkbox.setSelected(true);
        }
        else checkbox.setSelected(false);
    }

    /***
     * Supprime un client
     * @param clientAsuprrimer
     */
    public static void deleteThat(Client clientAsuprrimer){

        ListeClients.getListeTousClients().remove(clientAsuprrimer);
        clientAsuprrimer = null ;

    }

    /***
     * supprime un prospect
     * @param prospectAsupprimer
     */
    public static void deleteThat(Prospect prospectAsupprimer){

        ListeProspects.getListeTousProspects().remove(prospectAsupprimer);
        prospectAsupprimer = null ;

    }

    /***
     *
     * @param bool true => setEditable(true) ;
     * @param champs Un tableau de Jtextfield que l'on souhaite rendre éditable à souhait.
     */
    public static void RendreContenuChampsEditable (Boolean bool, JTextField[] champs){

        for (int i = 0 ; i < champs.length ; i ++ ) {
            champs[i].setEditable(bool);
        }
    }

}
