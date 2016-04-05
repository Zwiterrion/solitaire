package Projet.Fenetre;

import Projet.Pion.PionBicolore;
import Projet.Utils.TypePion;
import Projet.Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Zwitterion on 26/03/16.
 */
public class FenetreChoixPion extends FenetreJeuInverse {

    public FenetreChoixPion() {
        super();
        System.out.println("|\t\t\t\t\t\t |-> Construction Fenêtre Choix du Pion");


        JButton pionChoix = new JButton("Changer pion");
        pionChoix.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                changerPionForme();
            }
        });

        JButton couleurChoix = new JButton("Couleur Pion");
        couleurChoix.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                changeCouleurPion();
            }
        });

        Box menuHaut = this.getMenuHaut();
        menuHaut.add(pionChoix);
        menuHaut.add(couleurChoix);

    }

    public void changeCouleurPion() {

        final Color[] faceCachee = new Color[1];
        final Color[] faceVisible = new Color[1];
        final Color[] bordure = new Color[1];

        JButton couleurFaceCache = new JButton("Change");
        JButton couleurFaceVisible = new JButton("Change");
        JButton couleurBordure = new JButton("Change");

        couleurFaceCache.setOpaque(true);
        couleurFaceVisible.setOpaque(true);
        couleurBordure.setOpaque(true);

        couleurBordure.setBackground(Utils.defautBordure);
        couleurFaceCache.setBackground(Utils.defautCache);
        couleurFaceVisible.setBackground(Utils.defautVisible);

        construitBoutonCouleur(couleurBordure, bordure, "Couleur des bordures");
        construitBoutonCouleur(couleurFaceCache, faceCachee, "Couleur face cachée");
        construitBoutonCouleur(couleurFaceVisible, faceVisible, "Couleur Face Visible");

        Object[] inputFields = {
                "Couleur face cachée", couleurFaceCache,
                "Couleur face visible", couleurFaceVisible,
                "Couleur des bordures", couleurBordure};

        int option = JOptionPane.showConfirmDialog(this, inputFields, "Menu Couleurs",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            if(faceCachee[0] != null)
                Utils.defautCache = faceCachee[0];
            if(faceVisible[0] != null)
                Utils.defautVisible = faceVisible[0];
            if(bordure[0] != null)
                Utils.defautBordure = bordure[0];

            appliqueCouleur();
            repaint();
        }
    }

    public void construitBoutonCouleur(JButton bouton, Color couleur[], String texte) {
        bouton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                couleur[0] = choixCouleur(texte);
                bouton.setBackground(couleur[0]);
            }
        });
    }

    public void appliqueCouleur() {
        PionBicolore grille[][] = this.getPions();

        int n = this.getTaille().getKey();
        int m = this.getTaille().getValue();

        for(int i=0; i<n; i++)
            for(int j=0; j<m; j++) {
                grille[i][j].checkCouleur();
            }
    }

    public Color choixCouleur(String t) {
        return JColorChooser.showDialog(this, t, Color.BLACK);
    }

    public void changerPionForme() {

        String list[] = new String[TypePion.values().length];

        for(int i=0; i<TypePion.values().length; i++)
            list[i] = TypePion.values()[i].toString();

        Object choix = JOptionPane.showInputDialog(this,"Choix des pions","Choix des pions",
                JOptionPane.PLAIN_MESSAGE, null, list, null);

        if(choix instanceof String) {
            if (choix.equals(TypePion.CERCLE.toString()))
                this.setChoix(TypePion.CERCLE);
            else if (choix.equals(TypePion.RECTANGULAIRE.toString()))
                this.setChoix(TypePion.RECTANGULAIRE);
            changeDePions();
        }
    }

    public void changeDePions() {
        PionBicolore grille[][] = this.getPions();

        int n = this.getTaille().getKey();
        int m = this.getTaille().getValue();

        for(int i=0; i<n; i++)
            for(int j=0; j<m; j++) {
                grille[i][j] = constructionPion(i,j,grille[i][j].estCache());
            }
        this.setPions(grille);
        initialisationGrille(grille);

    }

    public PionBicolore constructionPion(int i, int j, boolean cache) {
        PionBicolore b = super.constructionPion(i, j);
        if(b.estCache() != cache)
            b.tournerPion();
        return b;
    }

    public static void runProgram() {
        FenetreChoixPion m = new FenetreChoixPion();
        initFrame(m, "Jeu avec choix des pions");
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(FenetreChoixPion::runProgram);
    }
}
