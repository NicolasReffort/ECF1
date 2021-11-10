package vues;

import Utilitaires.Outils;
import entites.Client;
import entites.ListeClients;
import entites.ListeProspects;
import entites.Societe;

import javax.swing.*;
import java.awt.*;
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


    //CONSTRUCTEUR -----------------------------------------------------------------------------------------------------
    public Accueil() {
        setContentPane(contentPane);
        setSize(1600,900);
        setMinimumSize(new Dimension(150,156));
        getRootPane().setDefaultButton(buttonOK);

        // CHOIX DE LA GESTION CLIENT
        gererUnClientButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                gererUnProspectButton.setVisible(false);
                jpanelChoixEdition.setVisible(true);
                affichageButton.setText("Afficher tous les clients");
                creerButton.setText("Créer un client");
                modifierButton.setText("Modifier un client");
                supprimerButton.setText("Supprimer un client");
            }
        });

        // CHOIX CREATION CLIENT :
        creerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                comboBox1.setVisible(false);
                Formulaire formulaireCreation = new Formulaire(Outils.TypeSociete.CLIENT);

            }
        });

        // CHOIX MODIFICATION CLIENT :
       ModificationSuppression("modifier", Outils.TypeSociete.CLIENT);

                //TO DO ; METTRE EN TRY CATCH POUR NPE
//                try {Object societeToHandle = comboBox1.getSelectedItem();}
//                catch (NullPointerException mem) {
//                    throw new MonExceptionMaison("Merci de séléctionner une société à éditer dans la liste déroulante");

        // CHOIX SUPPRESSION CLIENT :
       ModificationSuppression("supprimer", Outils.TypeSociete.CLIENT);


        // CHOIX AFFICHAGE CLIENT :
        affichageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                comboBox1.setVisible(false);
                Affichage affichage = new Affichage(Outils.TypeSociete.CLIENT);

            }
        });

        this.setVisible(true);
        jpanelChoixEdition.setVisible(false);
        panelCombobox.setVisible(false);
    }

    /***
     *
     * @param comboBox Une comboBox préexistante qu'on souhaite remplir.
     * @param typeSociete Détermine la base de données/collection à afficher
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
     * @param supprimerOuModifier créér les boutons supprimer ou modifier et leurs combobox
     * @param typeSociete Outils.TypeSocite.CLIENT ou PROSPECT
     */
    public void ModificationSuppression(String supprimerOuModifier, Outils.TypeSociete typeSociete){


        switch (supprimerOuModifier){
            case "supprimer":

                supprimerButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panelCombobox.setVisible(true);
                        RemplirCombobox(comboBox1, typeSociete);
                        comboBox1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Societe societe = RecupererSelectionCombobox(comboBox1, typeSociete);
                                Formulaire formulaire = new Formulaire(supprimerOuModifier, societe);
                            }
                        });
                    }
                });

            break;

            case "modifier":

                modifierButton.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        panelCombobox.setVisible(true);
                        RemplirCombobox(comboBox1, typeSociete);
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
            break;

        }


    }






    }


