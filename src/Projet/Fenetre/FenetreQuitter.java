package Projet.Fenetre;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Zwitterion on 21/03/16.
 */
public class FenetreQuitter extends JFrame {

    private Box menuBas;

    public FenetreQuitter() {
        super();

        System.out.println("Construction Fenêtre Quitter");

        JButton quitter = new JButton("Quitter");

        quitter.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this,
                    "Voulez-vous vraiment quitter ?",
                    "Attention Fermeture !!",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null) == 0)
                System.exit(0);

        });

        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        menuBas = Box.createHorizontalBox();
        menuBas.add(Box.createGlue());
        menuBas.add(quitter);

        this.add(menuBas, BorderLayout.SOUTH);
    }

    public static void initFrame(JFrame m, String title) {
        m.setPreferredSize(new Dimension(800,600));
        m.setTitle(title);
        m.pack();
        m.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        m.setVisible(true);
        System.out.println("|__ -> Initialisation Frame");
    }

    public Box getMenuBas() {
        return menuBas;
    }

    public void setMenuBas(Box menuBas) {
        this.menuBas = menuBas;
        this.add(menuBas, BorderLayout.SOUTH);
    }


    public static void runProgram() {
        FenetreQuitter m = new FenetreQuitter();
        initFrame(m, "Simple fenêtre");
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(FenetreQuitter::runProgram);
    }
}
