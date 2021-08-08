package revisao_oop.menu;

import revisao_oop.Navigator.Pages;
import revisao_oop.Navigator.SwingNavigator;
import revisao_oop.formPage.FormPageBuilder;
import revisao_oop.listItens.ListPageSwing;

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

        goToAddBookPageButton = new JButton(Pages.books);

        goToReviewPageButton = new JButton(Pages.reviews);

        addButton = new JButton("Incluir");

        showListPage = new JButton(Pages.list);


        add(addButton);

        if (!currentPage.equals(Pages.books))
            add(goToAddBookPageButton);

        if (!currentPage.equals(Pages.reviews))
            add(goToReviewPageButton);


        add(showListPage);

        setListeners();
    }


    private void setListeners() {
        SwingNavigator navigator = SwingNavigator.getInstance();

        showListPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ListPageSwing();
            }
        });

        goToAddBookPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                navigator.goToPage(FormPageBuilder.buildBookPage());
            }
        });

        goToReviewPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                navigator.goToPage(FormPageBuilder.buildReviewPage());
            }
        });
    }

}
