/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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


public class AddBookPageSwing extends javax.swing.JFrame {

    public static final String pageName = "Livros";

    private final BookSwingForm bookForm;
    final MenuButtonsSwing menuButtons;

    final LibraryDataBase database;

    public AddBookPageSwing() {
        setTitle(APP_NAME);
        setMinimumSize(new Dimension(MIN_SIZE_IN_PIXELS * 2, MIN_SIZE_IN_PIXELS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        database = LibraryDataBase.getInstance();
        bookForm = new BookSwingForm();
        menuButtons = new MenuButtonsSwing(pageName);

        buildContainer();

        menuButtons.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    database.add(bookForm.getModel());
                    bookForm.labelsToBlack();

                } catch (IncorrectInputError error) {
                    bookForm.labelsToRed();
                }


            }
        });


        pack();
    }

    private void buildContainer() {
        Container container = getContentPane();

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.add(new Label(pageName, Label.CENTER));

        container.add(bookForm);

        container.add(menuButtons);
    }
}
