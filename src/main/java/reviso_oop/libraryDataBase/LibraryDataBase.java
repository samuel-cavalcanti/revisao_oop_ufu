package reviso_oop.libraryDataBase;

import reviso_oop.models.Book;
import reviso_oop.models.Review;

import java.util.LinkedList;
import java.util.List;

public class LibraryDataBase {
    private final List<Book> books;
    private final List<Review> reviews;


    private static LibraryDataBase singleton;

    public static LibraryDataBase getInstance() {
        if (singleton == null)
            singleton = new LibraryDataBase();

        return singleton;
    }

    private LibraryDataBase() {
        books = new LinkedList<>();

        reviews = new LinkedList<>();
    }

    public void add(Book b) {

        if (b.author.length() == 0)
            throw new IncorrectInputError("Author field not exist !!", "author");

        if (b.title.length() == 0)
            throw new IncorrectInputError("Title field not exist !!", "title");

        if (b.year.length() == 0)
            throw new IncorrectInputError("Year field not exist !!", "year");

        books.add(b);


    }


    public void add(Review r) {
        if (r.title.length() == 0)
            throw new IncorrectInputError("title field not exist !!", "title");

        if (r.organization.length() == 0)
            throw new IncorrectInputError("organization field not exist !!", "organization");

        if (r.volume.length() == 0)
            throw new IncorrectInputError("volume field not exist !!", "volume");

        if (r.number.length() == 0)
            throw new IncorrectInputError("number field not exist !!", "number");

        if (r.year.length() == 0)
            throw new IncorrectInputError("year field not exist !!", "year");

        reviews.add(r);
    }

    public List<Book> getBooks() {
        return this.books;
    }

    public List<Review> getReviews() {
        return this.reviews;
    }
}
