package Projet.Pion;

import Projet.Fenetre.FenetreGrille;
import Projet.Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class PionCercle extends PionBicolore {

    public PionCercle(FenetreGrille fenetreGrille, int posX, int posY) {
        super(fenetreGrille, posX, posY);
    }

    @Override
    public void definirPosition(Graphics2D g) {

        if(g.getClipBounds().getWidth() < g.getClipBounds().getHeight()) {

            int marge = (int) ((g.getClipBounds().getHeight() -  (g.getClipBounds().getWidth())) * 0.05);
            int y = (int) (g.getClipBounds().getHeight() -  (g.getClipBounds().getWidth()));

            ((Ellipse2D.Float)pion).setFrame(marge, y/2 + marge,
                    (int) (g.getClipBounds().getWidth()) - marge*2,
                    (int) (g.getClipBounds().getWidth()) - marge*2);
        }
        else {

            int marge = (int) ((g.getClipBounds().getWidth() - (g.getClipBounds().getHeight())) * 0.05);
            int x = (int) (g.getClipBounds().getWidth() - (g.getClipBounds().getHeight()));

            ((Ellipse2D.Float)pion).setFrame(x/2 + marge, marge,
                    (int) (g.getClipBounds().getHeight()) - marge*2,
                    (int) (g.getClipBounds().getHeight()) - marge*2);
        }
    }

    @Override
    public void construirePion() {
        pion = new Ellipse2D.Float(0,0,0,0);
    }
}
