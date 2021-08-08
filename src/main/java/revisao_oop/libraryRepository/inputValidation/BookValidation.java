package revisao_oop.libraryRepository.inputValidation;

import org.jetbrains.annotations.NotNull;
import revisao_oop.models.Book;

public class BookValidation implements InputValidation<Book> {
    @Override
    public Book validate(@NotNull Book b) {
        if (b.author.length() == 0)
            throw new IncorrectInputException("Author field not exist !!", "author");

        if (b.title.length() == 0)
            throw new IncorrectInputException("Title field not exist !!", "title");

        if (b.year.length() == 0)
            throw new IncorrectInputException("Year field not exist !!", "year");

        return b;
    }
}
