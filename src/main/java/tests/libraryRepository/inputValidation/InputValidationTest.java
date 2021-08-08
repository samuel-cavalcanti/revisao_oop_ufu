package tests.libraryRepository.inputValidation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import revisao_oop.libraryRepository.inputValidation.BookValidation;
import revisao_oop.libraryRepository.inputValidation.IncorrectInputException;
import revisao_oop.libraryRepository.inputValidation.ReviewValidation;
import revisao_oop.models.Book;
import revisao_oop.models.Review;

import static org.junit.jupiter.api.Assertions.*;

class InputValidationTest {


    @Test
    void testBook() {
        Book b = new Book("title", "author", "1996");
        Book b1 = new Book("title", "author", "1996");
        Book validatedBook = new BookValidation().validate(b);
        assertTrue(validatedBook.mEquals(b1));
        assertThrows(IncorrectInputException.class, new Executable() {
            @Override
            public void execute() {
                Book b2 = new Book("", b.author, b.year);
                new BookValidation().validate(b2);
            }
        });

        String expected = "Livro: " + validatedBook.title + " " + validatedBook.author + " " + validatedBook.year;

        assertEquals(expected, validatedBook.toPrettyString());

    }

    @Test
    void testReview() {
        Review r = new Review("title", "org", "vol", "666", "1999");
        Review r1 = new Review("title", "org", "vol", "666", "1999");
        assertTrue(r.mEquals(r1));

        Review validatedReview = new ReviewValidation().validate(r);
        assertTrue(validatedReview.mEquals(r));

        assertThrows(IncorrectInputException.class, new Executable() {
            @Override
            public void execute() {
                Review r = new Review("", "org", "vol", "666", "1999");
                new ReviewValidation().validate(r);
            }
        });

        String expected = "Revista: " + r.title + " " + r.organization + " " + r.volume + " " + r.number + " " + r.year;

        assertEquals(expected, r.toPrettyString());
    }
}