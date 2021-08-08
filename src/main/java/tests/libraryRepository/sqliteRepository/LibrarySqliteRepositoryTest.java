package tests.libraryRepository.sqliteRepository;

import org.junit.jupiter.api.Test;
import revisao_oop.libraryRepository.LibraryRepository;
import revisao_oop.libraryRepository.sqliteRepository.LibrarySqliteRepository;
import revisao_oop.models.Book;
import revisao_oop.models.Review;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class LibrarySqliteRepositoryTest {

    @Test
    void testSqlRepository() {
        LibraryRepository sqlRepo = LibrarySqliteRepository.getInstance();

        List<Book> books = sqlRepo.getBooks();
        List<Review> reviews = sqlRepo.getReviews();

        assertFalse(books.isEmpty());
        assertFalse(reviews.isEmpty());

        int booksSize = books.size();
        int reviewsSize = reviews.size();

        Book test = new Book("test", "junit", "2020");
        Review testReview = new Review("test", "JUNIT USER GUILDE", "5", "8.7.2", "2020");
        sqlRepo.add(test);
        sqlRepo.add(testReview);

        List<Book> newBooks = sqlRepo.getBooks();
        List<Review> newReviews = sqlRepo.getReviews();
        assertEquals(booksSize + 1, newBooks.size());
        assertEquals(reviewsSize + 1, newReviews.size());


    }

}