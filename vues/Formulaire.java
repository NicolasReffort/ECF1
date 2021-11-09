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

    public final String ATTRIBUTCLIENT1 = "CHIFFRE D'AFFAIRES" ;
    public final String ATTRIBUTCLIENT2 = "NOMBRE D'EMPLOYES" ;
    public final String ATTRIBUTPROSPECT1 = "DATE DE PROSPECTION";
    public final String ATTRIBUTPROSPECT2 = "EST INTERESSE : O/N.";

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

    //PREMIER CONSTRUCTEUR : LA FONCTION CREER -------------------------------------------------------------------
    public Formulaire(String clientOuProspect) {

        //REMPLISSAGE DE LA PAGE AVEC LE PANE PRINCIPAL
        setContentPane(contentPaneFormulaire);

        // REMPLISSAGE DE LA VUE SI CEST UN CLIENT
        if (clientOuProspect.equals("client")) {
            AttributFille1TexteField.setText(ATTRIBUTCLIENT1);
            AttributFille2TexteField.setText(ATTRIBUTCLIENT2);
            // ON AFFICHE LE FUTUR IDENTIFIANT QUI SERA SSOCIE AU CLIENT CREE
            champID.setText( Integer.toString(Client.getCompteurClients() + 1 ));
        }

        else {
            AttributFille1TexteField.setText(ATTRIBUTPROSPECT2);
            AttributFille2TexteField.setText(ATTRIBUTPROSPECT2);
            // ON AFFICHE LE FUTUR IDENTIFIANT QUI SERA SSOCIE AU CLIENT CREE
            champID.setText( String.valueOf(Prospect.getCompteurProspects() + 1 ));
        }

        buttonOk.setText("CRÉER CE" + clientOuProspect.toUpperCase(Locale.ROOT));

        //TAILLE
        setSize(800,900);
        setMinimumSize(new Dimension(150,156));
        //FORMULAIRE DEVIENT VISIBLE
        setVisible(true);

       buttonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //ACTIONS A ENCLENCHER POUR  OK SI CEST UN CLIENT ------------------------------------------------------
                if (clientOuProspect.equals("client")) {

                    //ESSAI DU CA
                    try {
                            setCAenDouble( Double.parseDouble(champFille1.getText())) ;

                    }catch (NullPointerException npe) {
                        System.out.println("Merci de saisir un CA");
                    }
                    catch (NumberFormatException nfe )  {
                        System.out.println("Votre CA saisi n'est pas correct");
                    }

                    //ESSAI DU NB D EMPLOYES
                    try {
                        setNbEmployesInt(Integer.parseInt(champFille2.getText())); }
                    catch (NumberFormatException nfe1 )  {
                        JOptionPane.showMessageDialog(null, "Votre nombre d'employé saisi n'est pas correct")
                    ;
                    }

                    try { //ESSAYER DE CONSTRUIRE AVEC LES SAISIES ET LA VERIFICATION DU TYPAGE FAITE A L AFFICHAGE

                        new Client(champRaisonSociale.getText(),champVille.getText(), champNumeroRue.getText(), champRue.getText(),
                                champCodePostal.getText(), champTelephone.getText(), champCourriel.getText(),
                                champCommentaires.getText(), getCAenDouble() , getNbEmployesInt())
                        ;
                        dispose(); // RETOUR A L ACCUEIL SI LA CREATION A FONCTIONNE
                        JOptionPane.showMessageDialog(null, ListeClients.getListeTousClients().toString());
                        Accueil accueil = new Accueil();
                        accueil.setVisible(true);

                    }
                    catch (MonExceptionMaison mem) { // SI JAMAIS UN DES SETTERS NE FONCTIONNE PAS, ON RECUPERE LE MSG
                        //D ERREUR PERSO
                        JOptionPane.showMessageDialog(null,mem.getMessage() );
                    }
                    ;

                    JOptionPane.showMessageDialog(null, // POUR DEV
                            champRaisonSociale.getText() + champNumeroRue.getText() + champRue.getText() +
                                    champCodePostal.getText() + champTelephone.getText() + champCourriel.getText()
                                    + champCommentaires.getText()+ getCAenDouble() + getNbEmployesInt()
                    );

                }
                //onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
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
     *                du formulaire de modification.
     * @param suppressionOuModification Indice pour connaître la marche à suivre (supp/modif)
     */
    public Formulaire(String suppressionOuModification, Societe societe) {

        //REMPLISSAGE DE LA PAGE AVEC LE PANE PRINCIPAL
        setContentPane(contentPaneFormulaire);

        //REMPLISSAGE AVEC LES ATTRIBUTS-MERE
        champRaisonSociale.setText(societe.getRaisonSociale());
        champVille.setText(societe.getVille());
        champCodePostal.setText(societe.getCodePostal());
        champNumeroRue.setText(societe.getNumeroRue());
        champRue.setText(societe.getRue());
        champTelephone.setText(societe.getTelephone());
        champCourriel.setText(societe.getCourriel());
        champCommentaires.setText(societe.getCommentaires());
        champID.setText( Integer.toString(societe.getIdentifiant()) );

        //CASTING DES SOCIETES
        Outils.DirecteurDeCasting(societe);

        if (Outils.itsClient) {

            // ON CHARGE AVEC LES DONNEeS ASSOCIEES SPECIFIQUES CLIENT
            AttributFille1TexteField.setText(ATTRIBUTCLIENT1);
            AttributFille2TexteField.setText(ATTRIBUTCLIENT2);
            champFille1.setText( String.valueOf(client.getCA()));
            champFille2.setText(String.valueOf(client.getNbEmployes()));
        }
        else
        {
            //setProspect(((Prospect)societe));

            // ON CHARGE AVEC LES DONNEES ASSOCIEES SPECIFIQUES PROSPECT
            AttributFille1TexteField.setText("DATE PROSPECTION");
            AttributFille2TexteField.setText("PROSPECT INTERESSE");
            // FAIRE AVEC LES PROSPECTS§§§§§§§§§§§§§§§
        }

        //TAILLE
        setSize(800, 900);
        setMinimumSize(new Dimension(150, 156));

//-------------------modification--------------------------------------
        if(suppressionOuModification.equals("modification")) {


        //.....d'un client
        if (Outils.itsClient) {

            buttonOk.setText("MODIFIER CE CLIENT");

            //FORMULAIRE DEVIENT VISIBLE
            setVisible(true);

            //ACTIONS DU BOUTON OK
            buttonOk.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                        //ESSAI DU CA //////////////////////////CATCHER plus bas
                        try {
                            setCAenDouble(Double.parseDouble(champFille1.getText()));

                        } catch (NullPointerException npe) {
                            System.out.println("Merci de saisir un C.A");
                        } catch (NumberFormatException nfe) {
                            System.out.println("Votre C.A saisi n'est pas correct");
                        }

                        //ESSAI DU NB D EMPLOYES
                        try {
                            setNbEmployesInt(Integer.parseInt(champFille2.getText()));
                        } catch (NumberFormatException nfe1) {
                            JOptionPane.showMessageDialog(null, "Votre nombre d'employé saisi " +
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

                            dispose(); // RETOUR A L ACCUEIL SI LES MODIFICATIONS ONT FONCTIONNE
                            JOptionPane.showMessageDialog(null, ListeClients.getListeTousClients().toString());
                            Accueil accueil = new Accueil();
                            accueil.setVisible(true);

                        } catch (MonExceptionMaison mem) { // SI JAMAIS UN DES SETTERS NE FONCTIONNE PAS, ON RECUPERE LE MSG
                            //D ERREUR PERSO
                            JOptionPane.showMessageDialog(null, mem.getMessage());
                        }
                        ;

                        JOptionPane.showMessageDialog(null, // POUR DEV
                                client.toString()
                        );

                    //onOK();
                }
            });

            buttonCancel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
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
            }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        }

        //.... ou d'un prospect:
        else {
            buttonOk.setText("MODIFIER CE PROSPECT");

            //FORMULAIRE DEVIENT VISIBLE
            setVisible(true);
            //ACTION DU BOUTON OK
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
                        JOptionPane.showMessageDialog(null, ListeProspects.getListeTousProspects().toString());
                        Accueil accueil = new Accueil();
                        accueil.setVisible(true);

                    } catch (MonExceptionMaison mem) { // SI JAMAIS UN DES SETTERS NE FONCTIONNE PAS, ON RECUPERE LE MSG
                        //D ERREUR PERSO
                        JOptionPane.showMessageDialog(null, mem.getMessage());
                    }
                    ;

                    JOptionPane.showMessageDialog(null, // POUR DEV
                            prospect.toString() + " a été modifié"
                    );

                    //onOK();
                }
            });

        }

            buttonCancel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    onCancel();
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
            }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        }
        // FIN DE LA PARTIE POUR LA MODIFICATION ---------------------------------

        //DEBUT SUPPRESSION CLIENT
        if(suppressionOuModification.equals("suppression")) {

            if (Outils.itsClient){
                AttributFille1TexteField.setText("CHIFFRE D'AFFAIRES ");
                AttributFille2TexteField.setText("NOMBRE DE SALARIES");

                //FORMULAIRE DEVIENT VISIBLE
                setVisible(true);

                buttonOk.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                    }
                });

            }

        }
    }

    private void onOK() {
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
        Accueil accueil = new Accueil();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

}
