package reviso_oop.menu;

import reviso_oop.SwingNavigator;
import reviso_oop.addModel.AddBookPageSwing;
import reviso_oop.addModel.AddReviewPageSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuButtonsSwing extends JPanel {

    public final JButton addButton;

    private final JButton goToAddBookPageButton;
    private final JButton goToReviewPageButton;
    private final JButton showListPage;


    public MenuButtonsSwing(String currentPage) {

        setLayout(new FlowLayout());

        goToAddBookPageButton = new JButton(AddBookPageSwing.pageName);

        goToReviewPageButton = new JButton(AddReviewPageSwing.pageName);


        addButton = new JButton("Incluir");

        showListPage = new JButton("Listagem");
        add(addButton);

        if (!currentPage.equals(AddBookPageSwing.pageName))
            add(goToAddBookPageButton);

        if (!currentPage.equals(AddReviewPageSwing.pageName))
            add(goToReviewPageButton);


        add(showListPage);

        setListeners();
    }


    private void setListeners() {
        SwingNavigator navigator = SwingNavigator.getInstance();

        showListPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {


                navigator.goToListPage();
            }
        });

        goToAddBookPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                navigator.goToAddBook();
            }
        });

        goToReviewPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                navigator.goToAddReview();
            }
        });
    }

}
