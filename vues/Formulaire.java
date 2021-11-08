package vues;

import Utilitaires.MonExceptionMaison;
import Utilitaires.Outils;
import entites.Client;
import entites.ListeClients;
import entites.Prospect;
import entites.Societe;

import javax.swing.*;
import javax.swing.text.Utilities;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLOutput;

public class Formulaire extends JFrame {
    private JPanel contentPaneFormulaire;
    private JButton buttonOk;
    private JButton buttonCancel;
    private JTextField RAISONSOCIALETextField;
    private JTextField CODEPOSTALTextField;
    private JTextField VILLETextField;
    private JTextField NUMERORUETextField;
    private JTextField RUETextField;
    private JTextField TELEPHONETextField;
    private JTextField COURRIELTextField;
    private JTextField ATTRIBUTFILLE1TextField;
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
    private JTextField COMMENTAIRESTexteField;
    private JTextField ATTRIBUTFILLE2TexteField;
    private JTextField champFille2;
    private JTextField champCommentaires;
    private JTextField IDTextField;
    private JTextField champID;

    private Double CAenDouble; // UTILISE POUR POUVOIR SETTER PLUS FACILEMENT ----
    public Double getCAenDouble() {return CAenDouble;}
    private void setCAenDouble(Double CAenDouble) throws NullPointerException, NumberFormatException {this.CAenDouble = CAenDouble;    }

    private int NbEmployesInt;// UTILISE POUR POUVOIR SETTER PLUS FACILEMENT
    private void setNbEmployesInt(int nbEmployesInt) {NbEmployesInt = nbEmployesInt;}
    public int getNbEmployesInt() {return NbEmployesInt;    }

    private String clientOrProspect; // ATTRIBUT FOURNI POUR LA CREATION POUR JONGLER + FACILEMENT DANS LA PAGE
    public String getClientOrProspect() {return clientOrProspect;}

    private Client clientAModifier; // ENTITES A MODIFIER SUPPRIMER, POUR JONGLER + FACILEMENT
    private Prospect prospectAModifier;

    //PREMIER CONSTRUCTEUR : LA FONCTION AFFICHER -------------------------------------------------------------------
    public Formulaire(String clientOuProspect) {

        //REMPLISSAGE DE LA PAGE AVEC LE PANE PRINCIPAL
        setContentPane(contentPaneFormulaire);
        // AFFICHAGE SELON CLIENT OU PROSPECT DES ATTRIBUTS SPECIFIQUES
        if (clientOuProspect.equals("client")) {
            ATTRIBUTFILLE1TextField.setText("CHIFFRE D'AFFAIRES ");
            ATTRIBUTFILLE2TexteField.setText("NOMBRE DE SALARIES");
            // ON AFFICHE LE FUTUR IDENTIFIANT QUI SERA SSOCIE AU CLIENT CREE

            champID.setText( Integer.toString(Client.getCompteurClients() + 1 ));

            buttonOk.setText("CRÉER CE CLIENT");
        }
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

    // DEUXIEME CONSTRUCTEUR : MODIFICATION. ---------------------------------------------------------------------------
    public Formulaire(Societe societe) {

        //CASTING DES SOCIETES
        Outils outil = new Outils();
        outil.DirecteurDeCasting(societe);

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

        //REMPLISSAGE DE LA PAGE AVEC LE PANE PRINCIPAL
        setContentPane(contentPaneFormulaire);

        //TAILLE
        setSize(800, 900);
        setMinimumSize(new Dimension(150, 156));

        //SI CEST UN CLIENT
        if (outil.isItsClient()) {
            //CASTING
            Client client = ((Client)societe);

            // ON CHARGE AVEC LES DONNES ASSOCIEES SPECIFIQUES A UN CLIENT
            ATTRIBUTFILLE1TextField.setText("CHIFFRE D'AFFAIRES ");
            champFille1.setText(Double.toString(client.getCA()));

            ATTRIBUTFILLE2TexteField.setText("NOMBRE DE SALARIES");
            champFille2.setText(Integer.toString(client.getNbEmployes()));

            buttonOk.setText("MODIFIER CE CLIENT");

            //FORMULAIRE DEVIENT VISIBLE
            setVisible(true);
            //ACTION DU BOUTON OK
            buttonOk.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                        //ESSAI DU CA
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
                            client.setCA(Double.parseDouble(champFille1.getText()));
                            client.setNbEmployes(Integer.parseInt(champFille2.getText()));

                            dispose(); // RETOUR A L ACCUEIL SI LA MODIFICATION A FONCTIONNE
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

        //SI CEST UN PROSPECT QU ON VEUT MODIFIER
        else {
            //CASTING
            Prospect prospect = ((Prospect)societe);

            // ON CHARGE AVEC LES DONNES ASSOCIEES SPECIFIQUES A UN CLIENT
            ATTRIBUTFILLE1TextField.setText("DATE PROSPECTION");
            //champFille1.setText(Double.toString(Prospect.getCA()));

            ATTRIBUTFILLE2TexteField.setText("PROSPECT INTERESSE");
           // champFille2.setText(Integer.toString(client.getNbEmployes()));

            buttonOk.setText("MODIFIER CE PROSPECT");

            //FORMULAIRE DEVIENT VISIBLE
            setVisible(true);
            //ACTION DU BOUTON OK
            buttonOk.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    //ESSAI DU CA
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
                        JOptionPane.showMessageDialog(null, ListeClients.getListeTousClients().toString());
                        Accueil accueil = new Accueil();
                        accueil.setVisible(true);

                    } catch (MonExceptionMaison mem) { // SI JAMAIS UN DES SETTERS NE FONCTIONNE PAS, ON RECUPERE LE MSG
                        //D ERREUR PERSO
                        JOptionPane.showMessageDialog(null, mem.getMessage());
                    }
                    ;

                    JOptionPane.showMessageDialog(null, // POUR DEV
                            prospect.toString()
                    );

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
            }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);




        }



    }

    private void onOK() {
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

}
