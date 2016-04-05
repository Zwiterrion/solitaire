package Projet.Utils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Zwitterion on 22/03/16.
 */
public class CoupsRenderer extends JPanel implements ListCellRenderer<String> {

    private JTextArea coup;

    public CoupsRenderer() {
        super();
        coup = new JTextArea();
        coup.setEditable(false);
        add(coup);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
        coup.setText(value);
        return this;
    }
}
