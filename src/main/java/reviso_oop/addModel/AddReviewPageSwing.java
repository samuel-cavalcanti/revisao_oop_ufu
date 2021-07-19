package reviso_oop.addModel;

import reviso_oop.libraryDataBase.IncorrectInputError;
import reviso_oop.libraryDataBase.LibraryDataBase;
import reviso_oop.menu.MenuButtonsSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static reviso_oop.Main.APP_NAME;
import static reviso_oop.Main.MIN_SIZE_IN_PIXELS;

public class AddReviewPageSwing extends javax.swing.JFrame {
    public static final String pageName = "Revistas";


    private final ReviewSwingForm form;
    final LibraryDataBase dataBase;
    private final MenuButtonsSwing menuButtons;

    public AddReviewPageSwing() {

        setMinimumSize(new Dimension(MIN_SIZE_IN_PIXELS * 2, MIN_SIZE_IN_PIXELS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(APP_NAME);
        setVisible(true);

        dataBase = LibraryDataBase.getInstance();
        form = new ReviewSwingForm();

        menuButtons = new MenuButtonsSwing(pageName);


        buildContainer();

        menuButtons.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    dataBase.add(form.getModel());
                    form.labelsToBlack();

                } catch (IncorrectInputError error) {
                    form.labelsToRed();
                }

            }
        });

    }


    private void buildContainer() {
        Container container = getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(new Label(pageName, Label.CENTER));
        container.add(form);
        container.add(menuButtons);
    }


}
