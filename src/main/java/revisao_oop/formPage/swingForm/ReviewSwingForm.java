package revisao_oop.formPage.swingForm;

import revisao_oop.formPage.DefaultSwingForm;
import revisao_oop.formPage.FormController.FormController;
import revisao_oop.models.Review;

import javax.swing.*;
import java.awt.*;

public class ReviewSwingForm extends DefaultSwingForm<Review> {
    private final JTextField titleTextField;
    private final JTextField orgTextField;

    private final JTextField volumeTextField;
    private final JTextField numberTextField;
    private final JTextField yearTextField;


    private final JLabel titleLabel;
    private final JLabel orgLabel;

    private final JLabel volumeLabel;
    private final JLabel numberLabel;
    private final JLabel yearLabel;

    public ReviewSwingForm(FormController<Review> reviewFormController) {
        super(reviewFormController);

        titleTextField = new JTextField();
        orgTextField = new JTextField();
        volumeTextField = new JTextField();
        numberTextField = new JTextField();
        yearTextField = new JTextField();

        titleLabel = new JLabel("Titulo:");
        orgLabel = new JLabel("Org:");

        volumeLabel = new JLabel("Vol:");
        numberLabel = new JLabel("Nro:");
        yearLabel = new JLabel("Ano:");

        setSizeFields();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        buildPanel();


    }

    private void buildPanel() {
        add(buildTextField(titleLabel, titleTextField));
        add(buildTextField(orgLabel, orgTextField));


        JLabel[] metadataLabel = {volumeLabel, numberLabel, yearLabel};
        JTextField[] metadataTextField = {volumeTextField, numberTextField, yearTextField};

        add(buildTextFields(metadataLabel, metadataTextField));
    }


    JPanel buildTextFields(JLabel[] metadataReviewLabels, JTextField[] fields) {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());


        for (int i = 0; i < fields.length; i++) {
            jPanel.add(metadataReviewLabels[i]);
            jPanel.add(fields[i]);
            fields[i].setEditable(true);
        }

        return jPanel;
    }

    private void setSizeFields() {
        int biggerWidthFieldInPixels = 30;
        int smallWidthFieldInPixels = 4;

        titleTextField.setColumns(biggerWidthFieldInPixels);
        orgTextField.setColumns(biggerWidthFieldInPixels);

        yearTextField.setColumns(smallWidthFieldInPixels);
        numberTextField.setColumns(smallWidthFieldInPixels);
        volumeTextField.setColumns(smallWidthFieldInPixels);
    }

    @Override
    public Review getModel() {

        String title = titleTextField.getText();
        String org = orgTextField.getText();
        String volume = volumeTextField.getText();
        String number = numberTextField.getText();
        String year = yearTextField.getText();

        return new Review(title, org, volume, number, year);

    }

    @Override
    protected JLabel[] getLabels() {

        return new JLabel[]{
                titleLabel,
                orgLabel,
                volumeLabel,
                numberLabel,
                yearLabel};
    }


}
