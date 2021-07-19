package reviso_oop.addModel;

import reviso_oop.DefaultModelSwingForm;
import reviso_oop.models.Book;

import javax.swing.*;

public class BookSwingForm extends DefaultModelSwingForm<Book> {
    private final JTextField titleTextField;
    private final JTextField authorTextField;
    private final JTextField yearTextField;

    private final JLabel titleLabel;
    private final JLabel authorLabel;
    private final JLabel yearLabel;

    public BookSwingForm() {
        titleTextField = new JTextField();
        authorTextField = new JTextField();
        yearTextField = new JTextField();
        titleLabel = new JLabel("Titulo: ");
        authorLabel = new JLabel("Autor: ");
        yearLabel = new JLabel("Ano: ");

        setSizeFields();


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        buildPanel();

    }

    private void setSizeFields() {
        int biggerWidthFieldInPixels = 30;
        int smallWidthFieldInPixels = 4;

        titleTextField.setColumns(biggerWidthFieldInPixels);
        authorTextField.setColumns(biggerWidthFieldInPixels);
        yearTextField.setColumns(smallWidthFieldInPixels);
    }

    private void buildPanel() {
        add(buildTextField(titleLabel, titleTextField));
        add(buildTextField(authorLabel, authorTextField));
        add(buildTextField(yearLabel, yearTextField));
    }


    @Override
    public Book getModel() {
        String title = titleTextField.getText();
        String author = authorTextField.getText();
        String year = yearTextField.getText();

        return new Book(title, author, year);
    }

    @Override
    protected JLabel[] getLabels() {

        return new JLabel[]{titleLabel,
                authorLabel,
                yearLabel};
    }
}
