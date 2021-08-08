package revisao_oop.libraryRepository.sqliteRepository;

import org.jetbrains.annotations.NotNull;
import revisao_oop.libraryRepository.LibraryRepository;
import revisao_oop.libraryRepository.inputValidation.BookValidation;
import revisao_oop.libraryRepository.inputValidation.ReviewValidation;
import revisao_oop.models.Book;
import revisao_oop.models.Review;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class LibrarySqliteRepository implements LibraryRepository {

    private static LibrarySqliteRepository singleton;

    private Connection connection;
    private Integer lastBookId;
    private Integer lastReviewId;

    public static LibrarySqliteRepository getInstance() {

        if (singleton == null)
            singleton = new LibrarySqliteRepository();

        return singleton;
    }


    private LibrarySqliteRepository() {

        tryToConnect();
        lastBookId = getLastPrimaryKeyFromDB("Books", "book_id");
        lastReviewId = getLastPrimaryKeyFromDB("Reviews", "review_id");

    }


    private Integer getLastPrimaryKeyFromDB(String tableName, String colName) {

        try {
            String sql = "SELECT " + colName + " FROM " + tableName + " ORDER BY " + colName + " DESC LIMIT 1;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.getInt(colName);

        } catch (SQLException throwables) {
            System.out.println("Não foi possível achar a ultima Chave Primária: " + colName + " Na tabela: " + tableName);
            System.out.println(throwables.getMessage());
            System.exit(-1);
        }

        return null;
    }

    private void tryToConnect() {
        String databaseName = "jdbc:sqlite:sqlite.db";
        try {
            connection = DriverManager.getConnection(databaseName);
            DatabaseMetaData meta = connection.getMetaData();
            System.out.println("The driver name is " + meta.getDriverName());
            System.out.println("Connected to database");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Não foi possível abrir coneção com " + databaseName);
            System.exit(-1);
        }
    }

    @Override
    public void add(@NotNull Book b) {

        Book validatedBook = new BookValidation().validate(b);

        tryToInsertBookOnDB(validatedBook);


    }

    private void tryToInsertBookOnDB(Book validatedBook) {

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Books (book_id, title, author, year)\n" +
                    "VALUES (?, ?, ?, ?);");

            statement.setString(1, String.valueOf(lastBookId + 1));
            statement.setString(2, validatedBook.title);
            statement.setString(3, validatedBook.author);
            statement.setString(4, validatedBook.year);
            statement.executeUpdate();
            lastBookId++;
        } catch (SQLException e) {
            System.out.println("Não foi possível inserir o" + validatedBook.toPrettyString());
            System.out.println(e.getMessage());
        }


    }

    @Override
    public void add(@NotNull Review r) {

        Review validatedReview = new ReviewValidation().validate(r);

        tryToInsertReviewOnDB(validatedReview);
    }

    private void tryToInsertReviewOnDB(Review validatedReview) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Reviews (Review_id, title, organization, volume, number, year)\n" +
                            "VALUES (?,?, ?, ?, ?, ?);");

            statement.setString(1, String.valueOf(lastReviewId + 1));
            statement.setString(2, validatedReview.title);
            statement.setString(3, validatedReview.organization);
            statement.setString(4, validatedReview.volume);
            statement.setString(5, validatedReview.number);
            statement.setString(6, validatedReview.year);
            statement.executeUpdate();
            lastReviewId++;
        } catch (SQLException e) {
            System.out.println("Não foi possível inserir o" + validatedReview.toPrettyString());
            System.out.println(e.getMessage());
        }

    }

    @Override
    public @NotNull List<Book> getBooks() {
        String sql = "SELECT title, author, year FROM Books;";
        ResultSet resultSet = tryToSelectDataFromDb(sql);
        List<Book> books = bookResultToLinkedList(resultSet);

        return books;
    }


    private List<Book> bookResultToLinkedList(ResultSet resultSet) {
        List<Book> books = new LinkedList<>();

        if (resultSet == null)
            return books;

        try {

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String year = resultSet.getString("year");

                books.add(new Book(title, author, year));
            }


        } catch (SQLException throwables) {
            System.out.println("Não foi possível Transformar o ResultSet em uma lista de livros");
            System.out.println(throwables.getMessage());
        }

        return books;
    }

    @Override
    public @NotNull List<Review> getReviews() {
        String sql = "SELECT title, organization,  volume, number, year FROM Reviews;";
        ResultSet resultSet = tryToSelectDataFromDb(sql);
        List<Review> reviews = reviewResultToLinkedList(resultSet);

        return reviews;
    }

    private List<Review> reviewResultToLinkedList(ResultSet resultSet) {
        List<Review> reviews = new LinkedList<>();

        if (resultSet == null)
            return reviews;

        try {
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String organization = resultSet.getString("organization");
                String volume = resultSet.getString("volume");
                String number = resultSet.getString("number");
                String year = resultSet.getString("year");

                reviews.add(new Review(title, organization, volume, number, year));
            }

        } catch (SQLException throwables) {
            System.out.println("Não foi possível Transformar o ResultSet em uma lista de Revistas");
            System.out.println(throwables.getMessage());
        }

        return reviews;
    }

    private ResultSet tryToSelectDataFromDb(String sql) {


        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            return statement.executeQuery();

        } catch (SQLException throwables) {
            System.out.println("Não foi possível recuperar os livros");
            System.out.println(throwables.getMessage());
        }

        return null;
    }
}
