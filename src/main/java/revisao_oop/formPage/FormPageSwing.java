package revisao_oop.formPage;

import revisao_oop.formPage.swingForm.SwingForm;
import revisao_oop.menu.MenuButtonsSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static revisao_oop.Main.APP_NAME;
import static revisao_oop.Main.MIN_SIZE_IN_PIXELS;

public class FormPageSwing extends javax.swing.JFrame {

    private final SwingForm form;
    private final MenuButtonsSwing menuButtons;
    private final String pageName;

    public FormPageSwing(SwingForm form, String pageName) {
        this.pageName = pageName;
        this.form = form;

        setMinimumSize(new Dimension(MIN_SIZE_IN_PIXELS * 2, MIN_SIZE_IN_PIXELS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(APP_NAME);
        setVisible(true);


        menuButtons = new MenuButtonsSwing(pageName);


        buildContainer();

        menuButtons.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                form.submit();
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
