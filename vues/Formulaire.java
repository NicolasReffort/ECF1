package vues;

import Exceptions.MonExceptionMaison;
import Utilitaires.Outils;
import entites.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;

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
    private JTextField AttributFille1TexteField;
    private JTextField AttributFille2TexteField;
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

    private Double CAenDouble; // UTILISE POUR POUVOIR SETTER PLUS FACILEMENT ----
    public Double getCAenDouble() {return CAenDouble;}
    private void setCAenDouble(Double CAenDouble) throws NullPointerException, NumberFormatException {
        this.CAenDouble = CAenDouble;}

    private int NbEmployesInt;// UTILISE POUR POUVOIR SETTER PLUS FACILEMENT
    private void setNbEmployesInt(int nbEmployesInt) {NbEmployesInt = nbEmployesInt;}
    public int getNbEmployesInt() {return NbEmployesInt;    }

    private Client client ; // ATTRIBUT FOURNI POUR LA CREATION POUR JONGLER + FACILEMENT DANS LA PAGE
    public Client getClient() {return client;}
    public void setClient(Client client) {this.client = client;    }

    private Prospect prospect ; // ATTRIBUT FOURNI POUR LA CREATION POUR JONGLER + FACILEMENT DANS LA PAGE
    public Prospect getProspect() {return prospect;}
    public void setProspect(Prospect prospect) {this.prospect = prospect;}

    private Outils outils = new Outils();

    //PREMIER CONSTRUCTEUR : LA FONCTION CREER -------------------------------------------------------------------------
    public Formulaire(Outils.TypeSociete typeSociete) {

       Outils.PreparerlaPage(this ,contentPaneFormulaire);

        // REMPLISSAGE DE LA VUE SI CEST UN CLIENT
        if (typeSociete == Outils.TypeSociete.CLIENT) {
            AttributFille1TexteField.setText(VuesUtilitaires.CHIFFRESDAFFAIRES.toUpperCase());
            AttributFille2TexteField.setText(VuesUtilitaires.NB_EMPLOYES.toUpperCase());
            // ON AFFICHE LE FUTUR IDENTIFIANT QUI SERA SSOCIE AU CLIENT CREE
            champID.setText( Integer.toString(Client.getCompteurClients() + 1 ));
        }

        else {
            AttributFille1TexteField.setText(VuesUtilitaires.DATEDEPROSPECTION);
            AttributFille2TexteField.setText(VuesUtilitaires.EST_IL_INTERESSE);
            // ON AFFICHE LE FUTUR IDENTIFIANT QUI SERA SSOCIE AU CLIENT CREE
            champID.setText( String.valueOf(Prospect.getCompteurProspects() + 1 ));
        }

        buttonOk.setText("CRÉER CE" + typeSociete.toString().toUpperCase(Locale.ROOT));

        //FORMULAIRE DEVIENT VISIBLE
        setVisible(true);

       buttonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //ACTIONS A ENCLENCHER POUR  OK SI CEST UN CLIENT ------------------------------------------------------
                if (typeSociete == Outils.TypeSociete.CLIENT) {

                    //ESSAI DU CA
                    try {
                            setCAenDouble( Double.parseDouble(champFille1.getText())) ;

                    }catch (NullPointerException npe) {
                        System.out.println("Merci de saisir un " + VuesUtilitaires.CHIFFRESDAFFAIRES + ".");
                    }
                    catch (NumberFormatException nfe )  {
                        System.out.println("Votre " + VuesUtilitaires.CHIFFRESDAFFAIRES + " saisi n'est pas correct");
                    }

                    //ESSAI DU NB D EMPLOYES
                    try {
                        setNbEmployesInt(Integer.parseInt(champFille2.getText())); }
                    catch (NumberFormatException nfe1 )  {
                        JOptionPane.showMessageDialog(null, "Votre "
                                + VuesUtilitaires.NB_EMPLOYES + " saisi n'est pas correct")
                    ;
                    }

                    try { //ESSAYER DE CONSTRUIRE AVEC LES SAISIES ET LA VERIFICATION DU TYPAGE FAITE A L AFFICHAGE

                        new Client(champRaisonSociale.getText(),champVille.getText(), champNumeroRue.getText(), champRue.getText(),
                                champCodePostal.getText(), champTelephone.getText(), champCourriel.getText(),
                                champCommentaires.getText(), getCAenDouble() , getNbEmployesInt())
                        ;
                        dispose(); // RETOUR A L ACCUEIL SI LA CREATION A FONCTIONNE

                    }
                    catch (MonExceptionMaison mem) { // SI JAMAIS UN DES SETTERS NE FONCTIONNE PAS, ON RECUPERE LE MSG
                        //D ERREUR PERSO
                        JOptionPane.showMessageDialog(null,mem.getMessage() );
                    }
                    ;

                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPaneFormulaire.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    // DEUXIEME CONSTRUCTEUR : MODIFICATION/SUPPESSION -----------------------------------------------------------------

    /***
     *
     * @param societe Société choisie (dans la combo box) que l'on souhaite utiliser pour construire la vue
     *                du formulaire de modification/suppression.
     * @param supprimerOuModifier Motclé pour connaître la marche à suivre (supp/modif)
     */
    public Formulaire(String supprimerOuModifier, Societe societe) {

        // chargement du formulaire et du content aux dimensions voulues.
        Outils.PreparerlaPage(this, contentPaneFormulaire);

        //remplissage des champs formulaire et de leur contenu pour les attributs commun à toutes les sociétés
        RemplirChampsCommunsFormulaire(societe);

        //CASTING SOCIETE ARRIVANTE: le résulat est stocké en v.i.
        outils.DirecteurDeCasting(societe);
        if (outils.itsClient) {
            Client clientCaste = ((Client)societe);
            setClient(clientCaste); // stockage résulat en vi
            JOptionPane.showMessageDialog(null, "Vous avez sélectionné le client : "
                    + client.getRaisonSociale());
        }
        else
        {
            Prospect prospectCaste = ((Prospect)societe);
            setProspect(prospectCaste);
            JOptionPane.showMessageDialog(null, "Vous avez sélectionné le prospect : "
                    + prospect.getRaisonSociale());
        }

        //remplissage des champs du formulaire et de leur contenu avec les attributs spécifiques à chaque type de société
        if (outils.itsClient) {

            AttributFille1TexteField.setText(VuesUtilitaires.CHIFFRESDAFFAIRES.toUpperCase());
            AttributFille2TexteField.setText(VuesUtilitaires.NB_EMPLOYES.toUpperCase());
            champFille1.setText( String.valueOf(client.getCA()));
            champFille2.setText(String.valueOf(client.getNbEmployes()));
        }
        else
        {
            AttributFille1TexteField.setText(VuesUtilitaires.DATEDEPROSPECTION);
            AttributFille2TexteField.setText(VuesUtilitaires.EST_IL_INTERESSE);
            // FAIRE AVEC LES PROSPECTS§§§§§§§§§§§§§§§
        }


        //-------------------MODIFICATION---------------------------------------
        if(supprimerOuModifier.equals("modifier")) {

            //.....d'un client
            if (outils.itsClient) {

                buttonOk.setText("MODIFIER " + client.getRaisonSociale());

                //ACTIONS DU BOUTON OK
                buttonOk.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                            //ESSAI DU CA //////////////////////////CATCHER plus bas
                            try {
                                setCAenDouble(Double.parseDouble(champFille1.getText()));

                            } catch (NullPointerException npe) {
                                System.out.println("Merci de saisir un " + VuesUtilitaires.CHIFFRESDAFFAIRES + ".");
                            } catch (NumberFormatException nfe) {
                                System.out.println("Votre" + VuesUtilitaires.CHIFFRESDAFFAIRES + " saisi n'est pas correct.");
                            }

                            //ESSAI DU NB D EMPLOYES
                            try {
                                setNbEmployesInt(Integer.parseInt(champFille2.getText()));
                            } catch (NumberFormatException nfe1) {
                                JOptionPane.showMessageDialog(null, "Votre "
                                        + VuesUtilitaires.NB_EMPLOYES +" saisi " +
                                        "n'est pas correct")
                                ;
                            }

                            try { //ESSAYER DE MODIFIER AVEC LES SAISIES ET LA VERIFICATION DU TYPAGE FAITE A L AFFICHAGE

                                client.setRaisonSociale(champRaisonSociale.getText());
                                client.setVille(champVille.getText());
                                client.setCodePostal(champCodePostal.getText());
                                client.setNumeroRue(champNumeroRue.getText());
                                client.setRue(champRue.getText());
                                client.setTelephone(champTelephone.getText());
                                client.setCourriel(champCourriel.getText());
                                client.setCommentaires(champCommentaires.getText());
                                client.setCA(Double.parseDouble(champFille1.getText()));
                                client.setNbEmployes(Integer.parseInt(champFille2.getText()));
                                dispose(); // RETOUR A L ACCUEIL SI LA MODIFICATION A FONCTIONNE
                                Accueil accueil = new Accueil();

                            } catch (MonExceptionMaison mem) { // SI JAMAIS UN DES SETTERS NE FONCTIONNE PAS, ON RECUPERE LE MSG
                                //D ERREUR PERSO
                                JOptionPane.showMessageDialog(null, mem.getMessage());
                            }
                            ;

                        //onOK();
                    }
                });

                buttonCancel.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose(); // RETOUR A L ACCUEIL SI LA MODIFICATION A FONCTIONNE
                        Accueil accueil = new Accueil();                     }
                });

                this.setVisible(true);
        }

        //.... ou d'un prospect:
        else {
            buttonOk.setText("MODIFIER" + prospect.toString().toUpperCase()  );

            //Action du bouton OK
            buttonOk.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    //ESSAYER DE MODIFIER AVEC LES DONNEES SAISIES ET LES VERIFICATIONS DU TYPAGE FAITES PAR L AFFICHAGE
                    try {
                        prospect.setRaisonSociale(champRaisonSociale.getText());
                        prospect.setVille(champVille.getText());
                        prospect.setCodePostal(champCodePostal.getText());
                        prospect.setNumeroRue(champNumeroRue.getText());
                        prospect.setRue(champRue.getText());
                        prospect.setTelephone(champTelephone.getText());
                        prospect.setCourriel(champCourriel.getText());
                        //prospect.setCA(Double.parseDouble(champFille1.getText())); TO DO FAIRE LES ATT PROSPECT
                        //prospect.setNbEmployes(Integer.parseInt(champFille2.getText()));

                        dispose(); // RETOUR A L ACCUEIL SI LA MODIFICATION A FONCTIONNE
                        Accueil accueil = new Accueil();

                    } catch (MonExceptionMaison mem) { // SI JAMAIS UN DES SETTERS NE FONCTIONNE PAS, ON RECUPERE LE MSG
                        //D ERREUR PERSO
                        JOptionPane.showMessageDialog(null, mem.getMessage());
                    }
                    ;

                }
            });

            this.setVisible(true);

        } /// fin du else Modif Clients



        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPaneFormulaire.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        }
        // FIN DE LA PARTIE POUR LA MODIFICATION ---------------------------------

        //DEBUT SUPPRESSION
        if(supprimerOuModifier.equals("supprimer")) {

            buttonOk.setText("SUPPRIMER");

            buttonOk.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    if (outils.itsClient) {
                        client = null ;
                    }
                    else{prospect = null ;}
                    System.out.println(ListeClients.getListeTousClients().toString());
                    System.out.println(client);
                    dispose();
                    Accueil accueil = new Accueil();
                };

            }
            );
        }

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                dispose();
                Accueil accueil = new Accueil();
            }
        });


        this.setVisible(true);

    }

    private void onOK() {
    }

    private void onCancel() {
        dispose();
        Accueil accueil = new Accueil();


    }

    public void RemplirChampsCommunsFormulaire(Societe societe){

        champRaisonSociale.setText(societe.getRaisonSociale());
        champVille.setText(societe.getVille());
        champCodePostal.setText(societe.getCodePostal());
        champNumeroRue.setText(societe.getNumeroRue());
        champRue.setText(societe.getRue());
        champTelephone.setText(societe.getTelephone());
        champCourriel.setText(societe.getCourriel());
        champCommentaires.setText(societe.getCommentaires());
        champID.setText( Integer.toString(societe.getIdentifiant()) );
    }

}
