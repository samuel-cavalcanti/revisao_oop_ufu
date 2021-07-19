package reviso_oop;

import reviso_oop.addModel.AddBookPageSwing;
import reviso_oop.addModel.AddReviewPageSwing;
import reviso_oop.listItens.ListPageSwing;

import javax.swing.*;

public class SwingNavigator {

    private static SwingNavigator singleton;

    private JFrame currentPage;

    public static SwingNavigator getInstance() {
        if (singleton == null)
            singleton = new SwingNavigator();

        return singleton;
    }

    private SwingNavigator() {
        currentPage = null;
    }


    public void goToAddBook() {
        if (currentPage != null)
            currentPage.dispose();
        currentPage = new AddBookPageSwing();
    }

    public void goToAddReview() {
        if (currentPage != null)
            currentPage.dispose();
        currentPage = new AddReviewPageSwing();
    }

    public void goToListPage() {
        new ListPageSwing();
    }


}
