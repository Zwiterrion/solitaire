package Projet.Fenetre;

import Projet.Pion.PionBicolore;
import Projet.Pion.PionCercle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Zwitterion on 26/03/16.
 */
public class FenetreJeuInverse extends FenetreSuiteCoups {

    private boolean jeuInverse;
    private JButton changerJeu;

    public FenetreJeuInverse() {
        super();
        System.out.println("|\t\t\t\t\t |-> Construction Fenêtre Jeu Inverse");

        jeuInverse = false;

        changerJeu = new JButton("Jeu complémentaire");
        changerJeu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jeuInverse = !jeuInverse;
                affichageMessage();
                rejouer();
            }
        });

        Box menuHaut = this.getMenuHaut();
        menuHaut.add(Box.createGlue());
        menuHaut.add(changerJeu);

        this.add(menuHaut, BorderLayout.NORTH);

    }

    public void affichageMessage() {
        if(jeuInverse) {
            JOptionPane.showMessageDialog(this, "Remettez la grille en blanc en un nombre impair de coups !");
            changerJeu.setText("Jeu classique");
        }
        else {
            JOptionPane.showMessageDialog(this, "Mettez la grille en noir !");
            changerJeu.setText("Jeu complémentaire");
        }
    }

    @Override
    public boolean testEstTermine() {

        if(jeuInverse) {
            PionBicolore pions[][] = getPions();

            int n = getTaille().getKey();
            int m = getTaille().getValue();

            boolean termine = true;

            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++) {
                    PionBicolore p = pions[i][j];
                    if (!p.estCache())
                        termine = false;
                }

            return (termine && (getScore() % 2 == 1));
        }
        else
            return super.testEstTermine();
    }

    public static void runProgram() {
        FenetreJeuInverse m = new FenetreJeuInverse();
        initFrame(m, "Jeu final");
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(FenetreJeuInverse::runProgram);
    }
}
