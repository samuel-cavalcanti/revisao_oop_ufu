package reviso_oop.listItens;

import reviso_oop.libraryDataBase.LibraryDataBase;
import reviso_oop.models.Book;
import reviso_oop.models.Review;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static reviso_oop.Main.APP_NAME;
import static reviso_oop.Main.MIN_SIZE_IN_PIXELS;

public class ListPageSwing extends javax.swing.JFrame {

    final String pageName = "Listagem";

    final LibraryDataBase database;

    final List<Book> books;
    final List<Review> reviews;

    public ListPageSwing() {
        setMinimumSize(new Dimension(MIN_SIZE_IN_PIXELS * 2, MIN_SIZE_IN_PIXELS));
        setTitle(APP_NAME);
        setVisible(true);
        setInitLocalization();

        database = LibraryDataBase.getInstance();
        books = database.getBooks();
        reviews = database.getReviews();


        JTextArea textArea = new JTextArea();
        String text = booksToString() + reviewsToString();
        textArea.setText(text);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(textArea);


        Container container = getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(new Label(pageName, Label.CENTER));
        container.add(scrollPane);

    }


    String booksToString() {
        String output = "";

        for (Book b : books)
            output += "Livro: " + b.title + " " + b.author + " " + b.year + "\n";


        return output;
    }

    String reviewsToString() {
        String output = "";

        for (Review r : reviews)
            output += "Revista: " + r.title + " " + r.organization + " " + r.volume + " " + r.number + " " + r.year + "\n";


        return output;
    }


    private void setInitLocalization() {

        GraphicsConfiguration config = getGraphicsConfiguration();
        Rectangle bounds = config.getBounds();

        int x = bounds.x + 50;
        int y = 200;

        setLocation(x, y);

    }

}
