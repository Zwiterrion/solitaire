package Projet.Fenetre;

import Projet.Pion.PionBicolore;
import Projet.Utils.CoupsRenderer;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.BorderLayout;


public class FenetreSuiteCoups extends FenetreGrille {

    private JList<String> liste;
    private DefaultListModel<String> modele;

    private PionBicolore[][] precedenteGrille;

    public FenetreSuiteCoups() {
        super();

        System.out.println("|\t\t\t\t |-> Construction Fenêtre Suite Coups");

        modele = new DefaultListModel<>();
        liste = new JList<>(modele);
        liste.setCellRenderer(new CoupsRenderer());

        JScrollPane pane = new JScrollPane(liste);

        add(pane, BorderLayout.EAST);

        precedenteGrille = getPions();
        modele.addElement("-- Coups joués --");
    }

    @Override
    public void tournerVoisins(PionBicolore pion, boolean ajout) {
        super.tournerVoisins(pion,ajout);

        if(ajout) {
            ajouterCoups();
            precedenteGrille = getPions();
        }
    }


    public void ajouterCoups() {
        StringBuilder coups = new StringBuilder("Coups n°" + getScore()+ "\n");
        Pair<Integer,Integer> taille = getTaille();
        for(int i=0; i<taille.getKey(); i++) {
            coups.append("\n| ");
            for (int j = 0; j < taille.getValue(); j++) {
                if (precedenteGrille[i][j].estCache())
                    coups.append("X");
                else
                    coups.append("O");

                coups.append(" | ");
            }
        }

        modele.addElement(coups.toString());

        Runnable deplaceListe = () -> liste.ensureIndexIsVisible(getScore() - 1);

        SwingUtilities.invokeLater(deplaceListe);
    }

    @Override
    public void rejouer() {
        super.rejouer();
        modele.removeAllElements();
        modele.addElement("-- Coups joués --");
    }

    @Override
    public void changerTailleGrille() {
        super.changerTailleGrille();
        modele.removeAllElements();
        precedenteGrille = getPions();
    }

    public static void runProgram() {
        FenetreSuiteCoups m = new FenetreSuiteCoups();
        initFrame(m, "Fenêtre avec affichage finale des coups");
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(FenetreSuiteCoups::runProgram);
    }
}
