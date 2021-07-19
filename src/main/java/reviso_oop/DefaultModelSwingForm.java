package reviso_oop;

import javax.swing.*;
import java.awt.*;

public abstract class DefaultModelSwingForm<Model> extends JPanel {


    protected JPanel buildTextField(JLabel label, JTextField jtextField) {
        JPanel field = new JPanel();
        field.setLayout(new FlowLayout());

        field.add(label);
        field.add(jtextField);
        jtextField.setEditable(true);

        return field;
    }

    public abstract Model getModel();

    protected abstract JLabel[] getLabels();

    public void labelsToRed() {

        JLabel[] labels = getLabels();

        for (JLabel l : labels) {
            l.setForeground(Color.RED);
        }

    }


    public void labelsToBlack() {

        JLabel[] labels = getLabels();
        for (JLabel l : labels) {
            l.setForeground(Color.black);
        }

    }
}
