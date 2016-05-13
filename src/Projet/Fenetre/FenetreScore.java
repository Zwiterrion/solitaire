package Projet.Fenetre;

import Projet.Utils.Utils;

import javax.swing.*;
import java.awt.*;

public class FenetreScore extends FenetreMenu {

    private int score;
    private JLabel scoreAffichage;
    private Box menuHaut;

    public FenetreScore() {
        super();

        System.out.println("|\t\t |-> Construction Fenêtre Score");

        initialisationScore();
        constructionMenuHaut();
    }

    public void constructionMenuHaut() {
        menuHaut = Box.createHorizontalBox();

        menuHaut.add(Box.createRigidArea(new Dimension(10, 0)));
        menuHaut.add(scoreAffichage);
        menuHaut.setBackground(Utils.couleurText);
        menuHaut.setOpaque(true);

        this.add(menuHaut, BorderLayout.NORTH);
    }
    public void initialisationScore() {
        score = 0;
        scoreAffichage = new JLabel("Coups joués : " + score);
        scoreAffichage.setFont(Utils.policeText);
        scoreAffichage.setForeground(Color.WHITE);
        scoreAffichage.setBackground(Utils.couleurText);
        scoreAffichage.setOpaque(true);
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        scoreAffichage.setText("Coups joués : " + score);
    }

    public Box getMenuHaut() {
        return menuHaut;
    }

    public static void runProgram() {
        FenetreScore m = new FenetreScore();
        initFrame(m, "Fenêtre avec le score");
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(FenetreScore::runProgram);
    }
}
