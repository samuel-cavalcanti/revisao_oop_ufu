package revisao_oop.formPage.FormController;

import org.jetbrains.annotations.NotNull;
import revisao_oop.libraryRepository.LibraryRepository;
import revisao_oop.models.Book;

public class BookFormController implements FormController<Book> {
    private final LibraryRepository database;

    public BookFormController(LibraryRepository database) {
        this.database = database;
    }

    @Override
    public void sendModelToDataBase(@NotNull Book b) {
        database.add(b);
    }
}
