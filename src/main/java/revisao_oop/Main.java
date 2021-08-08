/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package revisao_oop;

import revisao_oop.Navigator.SwingNavigator;
import revisao_oop.formPage.FormPageBuilder;

/**
 * @author samuel
 */


public class Main {

    public static final String APP_NAME = "Revisao OOP";
    public static final int MIN_SIZE_IN_PIXELS = 300;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {
            SwingNavigator navigator = SwingNavigator.getInstance();

            navigator.goToPage(FormPageBuilder.buildBookPage());

        });
    }


}
