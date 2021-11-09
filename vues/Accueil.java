package vues;

import Utilitaires.Outils;
import entites.Client;
import entites.ListeClients;

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
    public JComboBox<String> comboBox1;
    private JButton buttonOK;


    //CONSTRUCTEUR -----------------------------------------------------------------------------------------------------
    public Accueil() {
        setContentPane(contentPane);
        setSize(1600,900);
        setMinimumSize(new Dimension(150,156));
        getRootPane().setDefaultButton(buttonOK);

        this.setVisible(true);

        jpanelChoixEdition.setVisible(false); // DE BASE ON CACHE LES CHOIX D EDITION


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
                dispose();
                Formulaire formulaireCreation = new Formulaire("client");

            }
        });

        // CHOIX MODIFICATION CLIENT :
        modifierButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                // ON PASSE A LA MODIFICAITON DE LA PERSONNE DESIREE
                //TO DO : LE FAIRE CHOISIR AVEC UNE COMBO BOX
                //Formulaire formulaireModification = new Formulaire("modification", );
            }
        });

        // CHOIX AFFICHAGE CLIENT :
        affichageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


            }
        });

        // CHOIX SUPPRESSION CLIENT :
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {




                //FAIRE LA COMBO LISTE POUR ENVOYER LA BONNE SOCIETE A SUPP
                // Formulaire formulaire = new Formulaire("suppression", );

            }
        });




    }

    public void RemplirCombobox(JComboBox comboBox, Outils.TypeSociete typeSociete){


        switch (Outils.TypeSociete typeSociete){

            case CLIENT:

            case
        }


        for (int i = 0 ; i < ( ListeClients.getListeTousClients().size() ) ; i++) {
            comboBox.addItem(ListeClients.getListeTousClients().get(i).toString());
        }

    }
}
