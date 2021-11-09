package vues;

import entites.Client;
import entites.ListeClients;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;

public class Accueil extends JFrame {
    private JPanel contentPane;
    private JButton gérerUnClientButton;
    private JButton gérerUnProspectButton;
    private JButton affichageButton;
    private JButton créerButton;
    private JButton modifierButton;
    private JButton supprimerButton;
    private JPanel jpanelChoixEdition;
    private JButton buttonOK;

    public Accueil() {
        setContentPane(contentPane);
        setSize(1600,900);
        setMinimumSize(new Dimension(150,156));
        getRootPane().setDefaultButton(buttonOK);
        setVisible(true);
        jpanelChoixEdition.setVisible(false); // DE BASE ON CACHE LES CHOIX D EDITION

        // CHOIX DE LA GESTION CLIENT CLIENT --------------------------------------------------------------------------
        gérerUnClientButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                gérerUnProspectButton.setVisible(false);
                jpanelChoixEdition.setVisible(true);
                affichageButton.setText("Afficher tous les clients");
                créerButton.setText("Créer un client");
                modifierButton.setText("Modifier un client");
                supprimerButton.setText("Supprimer un client");

            }
        });

        // CHOIX CREATION CLIENT :
        créerButton.addActionListener(new ActionListener() {
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
                Formulaire formulaireModification = new Formulaire("modification", ListeClients.getListeTousClients().get(0));
            }
        });

        // CHOIX AFFICHAGE CLIENT :
        affichageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                // ON PASSE A LA MODIFICAITON DE LA PERSONNE DESIREE
                //TO DO : LE FAIRE CHOISIR AVEC UNE COMBO BOX
                Affichage affichage = new Affichage( "client");
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
}
