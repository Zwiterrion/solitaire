package Projet.Fenetre;

import Projet.Pion.PionBicolore;
import Projet.Pion.PionRectangulaire;
import Projet.Pion.PionCercle;
import Projet.Utils.TypePion;
import Projet.Utils.Utils;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by Zwitterion on 21/03/16.
 */
public class FenetreGrille extends FenetreScore {

    private int n;
    private int m;
    private JPanel grille;
    private PionBicolore[][] pions;

    private TypePion choix;

    public FenetreGrille() {
        super();

        System.out.println("|\t\t\t |-> Construction Fenêtre Grille");

        choix = Utils.defautTypePion;
        initialisationDimension();
        constructionGrille();

        this.getChangerDimension().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                changerTailleGrille();
            }
        });

        this.getRejouer().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                rejouer();
            }
        });
    }

    public void changerTailleGrille() {

        SpinnerNumberModel curseurN = new SpinnerNumberModel(2, 1, 15, 1);
        JSpinner spinnerN = new JSpinner(curseurN);

        SpinnerNumberModel curseurM = new SpinnerNumberModel(2, 1, 15, 1);
        JSpinner spinnerM = new JSpinner(curseurM);

        Object[] message = {"Nombre de lignes: ", spinnerN,
                "Nombre de colonnes: ", spinnerM};

        if(JOptionPane.showConfirmDialog(this,message,"Choix des dimensions",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null) == 0) {
            this.n = curseurN.getNumber().intValue();
            this.m = curseurM.getNumber().intValue();
            initialisationGrille();
        }

        setScore(0);
    }

    public void initialisationDimension() {
        this.n = Utils.defautNbLignes;
        this.m = Utils.defautNbColonnes;
    }

    public void initialisationGrille() {
        grille.removeAll();
        grille.setLayout(new GridLayout(n, m));
        ajoutPions();
        repaint();
        revalidate();
    }

    public void initialisationGrille(PionBicolore[][] g) {
        grille.removeAll();
        grille.setLayout(new GridLayout(n,m));
        for(int i=0; i<n; i++)
            for(int j=0; j<m; j++) {
                grille.add(g[i][j]);
                pions[i][j] = g[i][j];
            }
        repaint();
        revalidate();
    }

    public void constructionGrille() {

        grille = new JPanel();
        initialisationGrille();
        this.add(grille, BorderLayout.CENTER);
    }

    public void ajoutPions() {
        pions = new PionBicolore[n][m];
        for(int i=0; i<n; i++)
            for(int j=0; j<m; j++) {
                PionBicolore p = constructionPion(i,j);
                grille.add(p);
                pions[i][j] = p;
            }
    }

    public PionBicolore constructionPion(int i, int j) {
        PionBicolore pionBicolore;
        switch (choix) {
            case CERCLE:
                pionBicolore = new PionCercle(this, i,j);
            break;
            case RECTANGULAIRE:
                pionBicolore = new PionRectangulaire(this, i,j);
            break;
            default:
                pionBicolore = new PionRectangulaire(this,i,j);
        }
        return pionBicolore;
    }

    public void tournerVoisins(PionBicolore pion, boolean ajout) {

        int x = pion.getPosX();
        int y = pion.getPosY();

        int potentielVoisins[] = {x+1, y, x-1, y, x, y+1, x,y-1, x-1, y-1, x+1, y+1, x+1, y-1, x-1, y+1};

        ArrayList<Pair<Integer,Integer>> voisins = new ArrayList<>();

        for(int i=0; i<potentielVoisins.length; i+=2){
            int a = potentielVoisins[i];
            int b = potentielVoisins[i+1];
            if(positionEstValide(a,b)){
                voisins.add(new Pair<>(a,b));
            }
        }

        for(Pair<Integer, Integer> v : voisins ) {
            PionBicolore pionV = pions[v.getKey()][v.getValue()];
            pionV.tournerPion();
        }

        if(ajout)
            setScore(getScore() + 1);

        if(testEstTermine()) {
            JOptionPane.showMessageDialog(this, "Vous avez gagné en " + getScore() + " coups ! Félicitations", "Partie terminée", JOptionPane.INFORMATION_MESSAGE, null);
        }
        else
            repaint();
    }

    public boolean testEstTermine() {
        boolean termine = true;

        for(int i=0; i<n; i++)
            for(int j=0; j<m; j++) {
                PionBicolore p = pions[i][j];
                if (p.estCache())
                    termine = false;
            }

        return termine;
    }

    public boolean positionEstValide(int x, int y) {
        return ((x >=0 && x < n) && (y >= 0 && y < m));
    }

    public void rejouer() {
        setScore(0);
        initialisationGrille();
    }

    public void setPions(PionBicolore[][] pions) {
        this.pions = pions;
    }

    public PionBicolore[][] getPions() {
        return pions;
    }

    public Pair<Integer, Integer> getTaille() {
        return new Pair<>(n,m);
    }

    public void setChoix(TypePion choix) {
        this.choix = choix;
    }

    public static void runProgram() {
        FenetreGrille m = new FenetreGrille();
        initFrame(m, "Fenêtre avec la grille");
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(FenetreGrille::runProgram);
    }
}
