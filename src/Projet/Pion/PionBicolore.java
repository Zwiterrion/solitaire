package Projet.Pion;

import Projet.Fenetre.FenetreGrille;
import Projet.Utils.TypePion;
import Projet.Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public abstract class PionBicolore extends JComponent implements MouseListener {

    private boolean cache;

    private FenetreGrille fenetreGrille;

    private int posX;
    private int posY;

    private Color couleurCourante;

    protected Shape pion;

    public PionBicolore(FenetreGrille fenetreGrille, int posX, int posY) {
        super();

        this.fenetreGrille = fenetreGrille;
        this.posX = posX;
        this.posY = posY;

        cache = true;
        couleurCourante = Utils.defautCache;

        this.setBorder(BorderFactory.createLineBorder(Utils.defautBordure));
        this.addMouseListener(this);

        construirePion();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(couleurCourante);
        definirPosition(graphics2D);
        graphics2D.fill(pion);
    }

    public void checkCouleur() {
        if(cache)
            couleurCourante = Utils.defautCache;
        else
            couleurCourante = Utils.defautVisible;

        this.setBorder(BorderFactory.createLineBorder(Utils.defautBordure));
    }

    public void tournerPion() {
        cache = !cache;
        checkCouleur();
        repaint();
    }

    public abstract void definirPosition(Graphics2D g);
    public abstract void construirePion();

    @Override
    public void mousePressed(MouseEvent e) {
        if(pion.contains(e.getX(), e.getY())) {
            if (SwingUtilities.isRightMouseButton(e)) {
                fenetreGrille.tournerVoisins(this, false);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(pion.contains(e.getX(), e.getY())) {
            if (SwingUtilities.isRightMouseButton(e))
                fenetreGrille.tournerVoisins(this, false);
            else
                fenetreGrille.tournerVoisins(this, true);
        }
    }

    public boolean estCache() {
        return cache;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }


    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

}
