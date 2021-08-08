package revisao_oop.formPage.FormController;

import org.jetbrains.annotations.NotNull;
import revisao_oop.libraryRepository.LibraryRepository;
import revisao_oop.models.Review;

public class ReviewFormController implements FormController<Review> {

    private final LibraryRepository dataBase;

    public ReviewFormController(LibraryRepository db) {
        dataBase = db;
    }

    @Override
    public void sendModelToDataBase(@NotNull Review r) {
        dataBase.add(r);
    }
}
