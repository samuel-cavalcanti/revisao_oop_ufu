package revisao_oop.listItens;

import revisao_oop.libraryRepository.LibraryRepository;
import revisao_oop.libraryRepository.sqliteRepository.LibrarySqliteRepository;
import revisao_oop.models.Book;
import revisao_oop.models.Review;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static revisao_oop.Main.APP_NAME;
import static revisao_oop.Main.MIN_SIZE_IN_PIXELS;

public class ListPageSwing extends javax.swing.JFrame {

    final String pageName = "Listagem";

    final LibraryRepository database;

    final List<Book> books;
    final List<Review> reviews;

    public ListPageSwing() {
        setMinimumSize(new Dimension(MIN_SIZE_IN_PIXELS * 2, MIN_SIZE_IN_PIXELS));
        setTitle(APP_NAME);
        setVisible(true);
        setInitLocalization();

        database = LibrarySqliteRepository.getInstance();
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
        StringBuilder output = new StringBuilder();

        for (Book b : books)
            output.append(b.toPrettyString()).append("\n");


        return output.toString();
    }

    String reviewsToString() {
        StringBuilder output = new StringBuilder();

        for (Review r : reviews)
            output.append(r.toPrettyString()).append("\n");


        return output.toString();
    }


    private void setInitLocalization() {

        GraphicsConfiguration config = getGraphicsConfiguration();
        Rectangle bounds = config.getBounds();

        int x = bounds.x + 50;
        int y = 200;

        setLocation(x, y);

    }

}
