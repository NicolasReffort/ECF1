package vues;

import Exceptions.MonExceptionMaison;
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

    private Double CAenDouble; // ATTRIBUT  POUR JONGLER + FACILEMENT DANS LA PAGE
    public Double getCAenDouble() {return CAenDouble;}
    private void setCAenDouble(Double CAenDouble) throws NullPointerException, NumberFormatException {
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

    private Outils outils = new Outils();

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //PREMIER CONSTRUCTEUR : LA FONCTION CREER -------------------------------------------------------------------------
    public Formulaire(Outils.TypeSociete typeSociete) {

       VuesUtilitaires.PreparerlaPage(this ,contentPaneFormulaire);
       VuesUtilitaires.RemplirNomsChampsFilles(typeSociete, attributFilleTexteField1, attributFilleTexteField2, champID);
       buttonOk.setText("CRÉER CE" + typeSociete.toString().toUpperCase());
       buttonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //ACTIONS SUR OK
                // ....si c'est un client ----------------------------
                if (typeSociete == Outils.TypeSociete.CLIENT) {

                    try {
                        // on teste le typage correct des variables
                        testerlesTypesDesAttributsFille(typeSociete);
                        //et on essaie de setter
                        new Client(champRaisonSociale.getText(),champVille.getText(), champNumeroRue.getText(),
                                champRue.getText(),champCodePostal.getText(), champTelephone.getText(),
                                champCourriel.getText(),champCommentaires.getText(),
                                getCAenDouble() , getNbEmployesInt() )
                        ;
                        onCancel();

                    }
                    catch (MonExceptionMaison mem) {
                        JOptionPane.showMessageDialog(null, mem.getMessage());
                    }catch (NullPointerException npe) {
                        JOptionPane.showMessageDialog(null,"Merci de saisir un "
                                + VuesUtilitaires.CHIFFRESDAFFAIRES + ".");
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null,"Votre"
                                + VuesUtilitaires.CHIFFRESDAFFAIRES + " ou votre " +VuesUtilitaires.NB_EMPLOYES
                                + " saisi n'est pas au format correct.");
                    }
                }
                // ... ou un prospect ----------------------------
                else {

                    try {
                        testerlesTypesDesAttributsFille(typeSociete);
                        new Prospect( champRaisonSociale.getText(),
                                champVille.getText(),
                                champNumeroRue.getText(),
                                champRue.getText(),
                                champCodePostal.getText(),
                                champTelephone.getText(),
                                champCourriel.getText(),
                                champCommentaires.getText(),
                                getDateProspection(),
                                champFille2.getText()
                        );
                        onCancel();
                    }
                    catch (NullPointerException npe) {
                        JOptionPane.showMessageDialog(null,"Merci de saisir un "
                                + VuesUtilitaires.CHIFFRESDAFFAIRES + ".");
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null,"Votre"
                                + VuesUtilitaires.CHIFFRESDAFFAIRES + " ou votre " +
                                VuesUtilitaires.NB_EMPLOYES + " saisi n'est pas au bon correct.");
                    }
                    catch (MonExceptionMaison mem) {
                        JOptionPane.showMessageDialog(null, mem.getMessage());
                    }
                }
            }
        });

        //FORMULAIRE DEVIENT VISIBLE
        setVisible(true);

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Accueil accueil = new Accueil();
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

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    // DEUXIEME CONSTRUCTEUR : MODIFICATION/SUPPESSION -----------------------------------------------------------------

    /***
     *
     * @param societe Société choisie (dans la combo box) que l'on souhaite utiliser pour construire la vue
     *                du formulaire de modification/suppression.
     * @param supprimerOuModifier Motclé pour connaître la marche à suivre (supp/modif)
     */
    public Formulaire(String supprimerOuModifier, Societe societe) {

        // chargement du formulaire et du content aux dimensions voulues.
        VuesUtilitaires.PreparerlaPage(this, contentPaneFormulaire);

        //remplissage des champs formulaire et de leur contenu pour les attributs commun à toutes les sociétés
        RemplirChampsCommunsFormulaire(societe);

        //Casting de la société arrivante : le résulat est stocké en v.i. d'outils
        outils.DirecteurDeCasting(societe);

        if (outils.itsClient) {
            Client clientCaste = ((Client)societe);
            setClient(clientCaste); // stockage résulat en vi
            JOptionPane.showMessageDialog(null, "Vous avez sélectionné le client : "
                    + client.getRaisonSociale());
            VuesUtilitaires.RemplirNomsChampsFilles( Outils.TypeSociete.CLIENT , attributFilleTexteField1, attributFilleTexteField2);
            VuesUtilitaires.RemplirContenuChampsFilles(getClient(), champFille1, champFille2);
        }
        else
        {
            Prospect prospectCaste = ((Prospect)societe);
            setProspect(prospectCaste);
            JOptionPane.showMessageDialog(null, "Vous avez sélectionné le prospect : "
                    + prospect.getRaisonSociale());
            VuesUtilitaires.RemplirNomsChampsFilles( Outils.TypeSociete.PROSPECT , attributFilleTexteField1, attributFilleTexteField2);
            VuesUtilitaires.RemplirContenuChampsFilles(getProspect(), champFille1, champFille2);
        }

        //-------------------MODIFICATION---------------------------------------
        if(supprimerOuModifier.equals("modifier")) {

            buttonOk.setText("MODIFIER " + societe.getRaisonSociale().toUpperCase()  );

            //.....d'un client
            if (outils.itsClient) {

                //ACTIONS DU BOUTON OK
                buttonOk.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        try {
                            // on teste le typage correct des variables
                             testerlesTypesDesAttributsFille(Outils.TypeSociete.CLIENT);
                             
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

                             dispose(); // retour accueil si tout va bien
                             Accueil accueil = new Accueil();

                        } catch (NullPointerException npe) {
                                System.out.println("Merci de saisir un " + VuesUtilitaires.CHIFFRESDAFFAIRES + ".");
                        } catch (NumberFormatException nfe) {
                                System.out.println("Votre" + VuesUtilitaires.CHIFFRESDAFFAIRES + " ou votre " +
                                        VuesUtilitaires.NB_EMPLOYES + " saisi n'est pas au format correct.");
                        }
                        catch (MonExceptionMaison mem) {
                                //D ERREUR PERSO
                                JOptionPane.showMessageDialog(null, mem.getMessage());
                        }
                        //to do : catcher TOUTES EXCEPTIONS
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

            //Action du bouton OK
            buttonOk.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    //ESSAYER DE MODIFIER AVEC LES DONNEES SAISIES ET LES VERIFICATIONS DU TYPAGE FAITES PAR L AFFICHAGE
                    try {
                        // on teste le typage correct des variables
                        testerlesTypesDesAttributsFille(Outils.TypeSociete.PROSPECT);

                        //tentative de setter
                        prospect.setRaisonSociale(champRaisonSociale.getText());
                        prospect.setVille(champVille.getText());
                        prospect.setCodePostal(champCodePostal.getText());
                        prospect.setNumeroRue(champNumeroRue.getText());
                        prospect.setRue(champRue.getText());
                        prospect.setTelephone(champTelephone.getText());
                        prospect.setCourriel(champCourriel.getText());
                        prospect.setDateProspection(dateProspection);
                        prospect.setPropsectEstInteresse(champFille2.getText());

                        dispose(); // RETOUR A L ACCUEIL SI LA MODIFICATION A FONCTIONNE
                        Accueil accueil = new Accueil();

                    }
                    catch (MonExceptionMaison mem) { // SI JAMAIS UN DES SETTERS NE FONCTIONNE PAS, ON RECUPERE LE MSG
                        //D ERREUR PERSO
                        JOptionPane.showMessageDialog(null, mem.getMessage());
                    }
                    catch (Exception ex){
                        JOptionPane.showMessageDialog(null, "Erreur inconnue, merci de reprendre votre saisie");
                    }
                    ;
                }
            });

        }

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

        //-------------------SUPPRESSION---------------------------------------
        if(supprimerOuModifier.equals("supprimer")) {

            buttonOk.setText("SUPPRIMER " + societe.getRaisonSociale().toUpperCase()  );

            buttonOk.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    if ( JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer"
                            + societe.getRaisonSociale() + "?" )  ==  JOptionPane.YES_OPTION )
                    {
                        if (outils.itsClient) {
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

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        this.setVisible(true);

    }
    //FIN DEUXIEME CONSTRUCTEUR ***

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

    /***
     * Vérifie que les attributs filles sont bien typés.
     * @param typeSociete
     * @throws MonExceptionMaison si la date de prospection est nulle ou au mauvais format
     * @throws NumberFormatException si CA/NB employés = mauvais type
     * @throws NullPointerException
     */
    public void testerlesTypesDesAttributsFille(Outils.TypeSociete typeSociete) throws MonExceptionMaison,
            NumberFormatException, NullPointerException {

        if (typeSociete == Outils.TypeSociete.CLIENT) {
                setCAenDouble(Double.parseDouble(champFille1.getText()));
                setNbEmployesInt(Integer.parseInt(champFille2.getText()));
        }

        else {
                setDateProspection(Outils.StringToLocalDate(champFille1.getText()));
        }
    }

    /***
     * Supprime un client
     * @param clientAsuprrimer
     */
    public void deleteThat(Client clientAsuprrimer){

        ListeClients.getListeTousClients().remove(clientAsuprrimer);
        clientAsuprrimer = null ;

    }

    /***
     * supprime un prospect
     * @param prospectAsupprimer
     */
    public void deleteThat(Prospect prospectAsupprimer){

        ListeProspects.getListeTousProspects().remove(prospectAsupprimer);
        prospectAsupprimer = null ;


    }



}
