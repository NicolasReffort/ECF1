package vues;

import Utilitaires.Outils;
import entites.ListeClients;
import entites.ListeProspects;
import entites.Societe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Accueil extends JFrame {
    private JPanel contentPane;
    private JButton gererUnClientButton;
    private JButton gererUnProspectButton;
    private JButton affichageButton;
    private JButton creerButton;
    private JButton modifierButton;
    private JButton supprimerButton;
    private JPanel jpanelChoixEdition;
    private JPanel panelCombobox;
    private JComboBox<Societe> comboBox1;
    private JButton buttonOK;
    private boolean gererunClientIsClicked = false; // flag == vrai si on clique sur GérerUnClient
    private boolean supprimerAlreadyClicked; // même principe pour supprimer et modifer. Evite de remplir deux fois la combobox.
    private boolean modifierAlreadyClicked ;

    //CONSTRUCTEUR -----------------------------------------------------------------------------------------------------
    public Accueil() {
        Outils.PreparerlaPage(this, contentPane );
        gererunClientIsClicked = false;
        supprimerAlreadyClicked = false;
        modifierAlreadyClicked = false;

        // ACTION SUR GESTION CLIENT
        gererUnClientButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                gererunClientIsClicked = true ;
                gererUnProspectButton.setVisible(false);
                jpanelChoixEdition.setVisible(true);// apparition des champs d'édition
                affichageButton.setText("Afficher tous les clients");
                creerButton.setText("Créer un client");
                modifierButton.setText("Modifier un client");
                supprimerButton.setText("Supprimer un client");
            }
        });

        // ACTION SUR GESTION PROSPECT
        gererUnProspectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gererunClientIsClicked = false ;
                gererUnClientButton.setVisible(false);
                jpanelChoixEdition.setVisible(true);
                affichageButton.setText("Afficher tous les prospects");
                creerButton.setText("Créer un prospect");
                modifierButton.setText("Modifier un prospect");
                supprimerButton.setText("Supprimer un prospect");
            }
        });

        // ACTION SUR CREATION :
        creerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                comboBox1.setVisible(false);
                if (gererunClientIsClicked){
                Formulaire formulaireCreation = new Formulaire(Outils.TypeSociete.CLIENT);
                }
                else  {Formulaire formulaireCreation = new Formulaire(Outils.TypeSociete.PROSPECT);};
            }});


        // ACTION SUR MODIFICATION :
        modifierButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (gererunClientIsClicked) {
                    System.out.println("Le gérer un client a été cliqué, je vais lancer la construction modification-client.");
                    CreerContenuBoutonModificationSuppression("modifier", Outils.TypeSociete.CLIENT);
                } else {
                    CreerContenuBoutonModificationSuppression("modifier", Outils.TypeSociete.PROSPECT);
                }
                ;
            }});

                //TO DO ; METTRE EN TRY CATCH POUR NPE
//                try {Object societeToHandle = comboBox1.getSelectedItem();}
//                catch (NullPointerException mem) {
//                    throw new MonExceptionMaison("Merci de séléctionner une société à éditer dans la liste déroulante");

        // ACTION SUR SUPPRESSION :
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (gererunClientIsClicked){
                    CreerContenuBoutonModificationSuppression("supprimer", Outils.TypeSociete.CLIENT);
                }
                else  {
                    CreerContenuBoutonModificationSuppression("supprimer", Outils.TypeSociete.PROSPECT);
                };
            }
        });


        // ACTION SUR AFFICHAGE :
        affichageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                comboBox1.setVisible(false);
                if (gererunClientIsClicked){
                    Affichage affichage = new Affichage(Outils.TypeSociete.CLIENT);
                }
                else  {Affichage affichage = new Affichage(Outils.TypeSociete.PROSPECT);
                };

            }
        });

        //par défaut au chargement: on voit les deux boutons sans les choix d'édition ni la combobox.
        this.setVisible(true);
        jpanelChoixEdition.setVisible(false);
        panelCombobox.setVisible(false);

    }

    /***
     *
     * @param comboBox Une comboBox préexistante qu'on souhaite remplir.
     * @param typeSociete Détermine la base de données/collection à utiliser pour remplir.
     */
    public void RemplirCombobox(JComboBox comboBox, Outils.TypeSociete typeSociete){

        switch (typeSociete){

            case CLIENT:
                for (int i = 0 ; i < ( ListeClients.getListeTousClients().size() ) ; i++) {
                    comboBox.addItem(ListeClients.getListeTousClients().get(i).toString());
                }
                break;

            case PROSPECT:
                for (int i = 0; i < ( ListeProspects.getListeTousProspects().size() ) ; i++) {
                    comboBox.addItem(ListeProspects.getListeTousProspects().get(i).toString());
                }
                break;
        }

    }

    public Societe RecupererSelectionCombobox(JComboBox comboBox, Outils.TypeSociete typeSociete){

        int indexCombobox = comboBox.getSelectedIndex(); // on récupère l'index de la sélection
        switch (typeSociete) {
            case CLIENT:
                return ListeClients.getListeTousClients().get(indexCombobox);
            case PROSPECT:
                return ListeProspects.getListeTousProspects().get(indexCombobox);
            default:
                throw new IllegalArgumentException();
        }
    }

    /****
     *
     * @param supprimerOuModifier créé les boutons supprimer ou modifier et leur combobox
     * @param typeSociete Outils.TypeSocite.CLIENT ou PROSPECT
     */
    public void CreerContenuBoutonModificationSuppression(String supprimerOuModifier, Outils.TypeSociete typeSociete){


        switch (supprimerOuModifier){

            case "supprimer":

                if (supprimerAlreadyClicked) {
                    JOptionPane.showMessageDialog(null,"Je ne peux pas construire les actions de " +
                            "supprimer. supprimerAlreadyClicked est"
                            + supprimerAlreadyClicked );
                }
                else {
                    JOptionPane.showMessageDialog(null, "supprimerAlreadyclick vaut : "
                            + supprimerAlreadyClicked);
                    supprimerButton.addActionListener(new ActionListener() {
                                                          @Override
                                                          public void actionPerformed(ActionEvent e) {
                                                              panelCombobox.setVisible(true);
                                                              if (supprimerAlreadyClicked == false) {
                                                                  RemplirCombobox(comboBox1, typeSociete);
                                                              } else System.out.println("Vous avez déjà cliqué sur le bouton supprimer");


                                                              // action sur combobox
                                                              comboBox1.addActionListener(new ActionListener() {
                                                                  @Override
                                                                  public void actionPerformed(ActionEvent e) {
                                                                      Societe societe = RecupererSelectionCombobox(comboBox1, typeSociete);
                                                                      Formulaire formulaire = new Formulaire(supprimerOuModifier, societe);
                                                                  }
                                                              });
                                                          }
                                                      }
                    );
                    supprimerAlreadyClicked = true;
                }

                break;

            case "modifier":

                modifierButton.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        panelCombobox.setVisible(true);
                        if (modifierAlreadyClicked == false ) {
                            RemplirCombobox(comboBox1, typeSociete);
                        }
                        else System.out.println("Vous avez déjà cliqué sur le bouton Modifier");
                        comboBox1.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Societe societe = RecupererSelectionCombobox(comboBox1, typeSociete);
                                comboBox1.setVisible(false);
                                Formulaire formulaire = new Formulaire(supprimerOuModifier, societe);
                            }
                        });
                    }

                    //TO DO ; METTRE EN TRY CATCH POUR NPE
//                try {Object societeToHandle = comboBox1.getSelectedItem();}
//                catch (NullPointerException mem) {
//                    throw new MonExceptionMaison("Merci de séléctionner une société à éditer dans la liste déroulante");

                });
                modifierAlreadyClicked = true ;
                break;

        }


    }

    }


