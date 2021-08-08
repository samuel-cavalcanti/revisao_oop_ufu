package revisao_oop.libraryRepository;

import org.jetbrains.annotations.NotNull;
import revisao_oop.models.Book;
import revisao_oop.models.Review;

import java.util.List;

public interface LibraryRepository {
    void add(@NotNull Book b);

    void add(@NotNull Review r);

    @NotNull List<Book> getBooks();

    @NotNull List<Review> getReviews();
}
