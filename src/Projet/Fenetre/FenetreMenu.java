package Projet.Fenetre;

import javax.swing.*;

/**
 * Created by Zwitterion on 21/03/16.
 */
public class FenetreMenu extends FenetreQuitter {

    private JButton changerDimension;
    private JButton rejouer;

    public FenetreMenu() {
        super();

        System.out.println("|\t |-> Construction Fenêtre Menu");
        changerDimension = new JButton("Changer de taille");
        rejouer = new JButton("Rejouer");

        Box menuBas = this.getMenuBas();
        Box menuTotal = Box.createHorizontalBox();

        menuTotal.add(changerDimension);
        menuTotal.add(rejouer);
        menuTotal.add(Box.createGlue());
        menuTotal.add(menuBas);

        this.setMenuBas(menuTotal);

    }

    public JButton getChangerDimension() {
        return changerDimension;
    }

    public JButton getRejouer() { return rejouer; }

    public static void runProgram() {
        FenetreMenu m = new FenetreMenu();
        initFrame(m, "Fenêtre avec un menu (rejouer & redimensionner)");
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(FenetreMenu::runProgram);
    }


}
