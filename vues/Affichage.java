package vues;

import Utilitaires.Outils;
import entites.Client;
import entites.Prospect;
import entites.Societe;

import javax.swing.*;
import java.awt.event.*;

public class Affichage extends JFrame {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea ListeTextArea;

    public Affichage(Societe societe) {
        //CASTING
        Outils outils = new Outils();
        outils.DirecteurDeCasting(societe);

        if ( outils.isItsClient() )

        {
            //CASTING
            Client client = ((Client)societe);

            ListeTextArea.setText(
                    client.toString()
            );


        }

        else
        {
            //CASTING
            Prospect prospect = ((Prospect)societe);

            ListeTextArea.setText(
                    prospect.toString()
            );
        }

        //PANE CHARGE
        setContentPane(contentPane);

        //PANE VISIBLE
        setVisible(true);

        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
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
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    Formulaire formulaire = new Formulaire(societe);
                }
            }
        );
    }



    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void onOK() { // REVENIR AU FORMULAIRE D AVANT
        // add your code here
        dispose();
    }
}
