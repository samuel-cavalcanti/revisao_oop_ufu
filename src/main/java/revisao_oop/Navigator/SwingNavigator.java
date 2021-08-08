package revisao_oop.Navigator;

import org.jetbrains.annotations.NotNull;

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


    public void goToPage(@NotNull JFrame page) {
        disposeCurrentPage();
        currentPage = page;
    }

    private void disposeCurrentPage() {
        if (currentPage != null)
            currentPage.dispose();
    }


}
