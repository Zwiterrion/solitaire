package Projet.Pion;

import Projet.Fenetre.FenetreGrille;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by Zwitterion on 21/03/16.
 */
public class PionRectangulaire extends PionBicolore {

    public PionRectangulaire(FenetreGrille fenetreGrille, int posX, int posY) {
        super(fenetreGrille, posX, posY);
    }


    @Override
    public void definirPosition(Graphics2D g) {

        int x = (int)(g.getClipBounds().getWidth() * 0.05);
        int y = (int)(g.getClipBounds().getHeight() * 0.05);

        ((Rectangle2D.Float)pion).setRect(x, y,
                (int) (g.getClipBounds().getWidth()) - x*2,
                (int) (g.getClipBounds().getHeight()) - y*2);
    }

    @Override
    public void construirePion() {
        pion = new Rectangle2D.Float(0,0,0,0);
    }
}
