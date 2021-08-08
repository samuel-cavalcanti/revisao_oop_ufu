package revisao_oop.libraryRepository.SimpleRepository;

import org.jetbrains.annotations.NotNull;
import revisao_oop.libraryRepository.LibraryRepository;
import revisao_oop.libraryRepository.inputValidation.BookValidation;
import revisao_oop.libraryRepository.inputValidation.ReviewValidation;
import revisao_oop.models.Book;
import revisao_oop.models.Review;

import java.util.LinkedList;
import java.util.List;

public class SimpleRepository implements LibraryRepository {
    private final List<Book> books;
    private final List<Review> reviews;


    private static SimpleRepository singleton;

    public static SimpleRepository getInstance() {
        if (singleton == null)
            singleton = new SimpleRepository();

        return singleton;
    }

    private SimpleRepository() {
        books = new LinkedList<>();

        reviews = new LinkedList<>();
    }

    @Override
    public void add(@NotNull Book b) {

        Book validatedBook = new BookValidation().validate(b);
        books.add(validatedBook);

    }


    @Override
    public void add(@NotNull Review r) {

        Review validatedReview = new ReviewValidation().validate(r);

        reviews.add(validatedReview);
    }

    public @NotNull List<Book> getBooks() {
        return this.books;
    }

    public @NotNull List<Review> getReviews() {
        return this.reviews;
    }
}
