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
    private JButton button1;
    private JButton buttonOK;
    private boolean gererunClientIsClicked = false; // flag == vrai si on clique sur GérerUnClient
    private boolean supprimerAlreadyClicked; // même principe pour supprimer et modifer. Evite de remplir deux fois la combobox.
    private boolean modifierAlreadyClicked ;

    //CONSTRUCTEUR -----------------------------------------------------------------------------------------------------
    public Accueil() {
        VuesUtilitaires.PreparerlaPage(this, contentPane );
        VuesUtilitaires.PreparerBoutonAccueil(button1, this);

        gererunClientIsClicked = false;
        supprimerAlreadyClicked = false;
        modifierAlreadyClicked = false;

        // ACTION SUR GESTION CLIENT
        gererUnClientButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                gererunClientIsClicked = true ;
                gererUnProspectButton.setVisible(false);
                jpanelChoixEdition.setVisible(true);// apparition des champs d'édition : factorisable, à la rigueur
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
                    CreerContenuBoutonModificationSuppression("modifier", Outils.TypeSociete.CLIENT);
                } else {
                    CreerContenuBoutonModificationSuppression("modifier", Outils.TypeSociete.PROSPECT);
                }
                modifierAlreadyClicked = true;

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
                supprimerAlreadyClicked = true ;
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
                    comboBox.addItem(ListeClients.getListeTousClients().get(i).getRaisonSociale());
                }
                break;

            case PROSPECT:
                for (int i = 0; i < ( ListeProspects.getListeTousProspects().size() ) ; i++) {
                    comboBox.addItem(ListeProspects.getListeTousProspects().get(i).getRaisonSociale());
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
     * Crée le contenu puis rend visible la CBB.
     * @param supprimerOuModifier créé le contenu des boutons supprimer ou modifier, et l'action sur combobox correspondante.
     * @param typeSociete Outils.TypeSocite.CLIENT ou PROSPECT
     */
    public void CreerContenuBoutonModificationSuppression(String supprimerOuModifier, Outils.TypeSociete typeSociete){

        // si rien n'a été cliqué
        if (!modifierAlreadyClicked && !supprimerAlreadyClicked) {

            RemplirCombobox(comboBox1, typeSociete);

            // action sur combobox
            comboBox1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Societe societe = RecupererSelectionCombobox(comboBox1, typeSociete);
                    Formulaire formulaire = new Formulaire(supprimerOuModifier, societe);
                    dispose();
                }
            }
            );
            panelCombobox.setVisible(true); // CBB visible.
            comboBox1.setVisible(true);
        }


    }

    }


