package revisao_oop.formPage;

import revisao_oop.formPage.FormController.FormController;
import revisao_oop.formPage.swingForm.SwingForm;
import revisao_oop.libraryRepository.inputValidation.IncorrectInputException;

import javax.swing.*;
import java.awt.*;

public abstract class DefaultSwingForm<Model> extends SwingForm {

    private final FormController<Model> controller;

    protected DefaultSwingForm(FormController<Model> controller) {

        this.controller = controller;
    }

    public abstract Model getModel();

    @Override
    public void submit() {

        try {
            controller.sendModelToDataBase(getModel());
            labelsToBlack();

        } catch (IncorrectInputException error) {
            labelsToRed();
        }


    }

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


    protected JPanel buildTextField(JLabel label, JTextField jtextField) {
        JPanel field = new JPanel();
        field.setLayout(new FlowLayout());

        field.add(label);
        field.add(jtextField);
        jtextField.setEditable(true);

        return field;
    }

}
