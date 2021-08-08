package revisao_oop.formPage;

import revisao_oop.Navigator.Pages;
import revisao_oop.formPage.FormController.BookFormController;
import revisao_oop.formPage.FormController.FormController;
import revisao_oop.formPage.FormController.ReviewFormController;
import revisao_oop.formPage.swingForm.BookSwingForm;
import revisao_oop.formPage.swingForm.ReviewSwingForm;
import revisao_oop.libraryRepository.LibraryRepository;
import revisao_oop.libraryRepository.sqliteRepository.LibrarySqliteRepository;
import revisao_oop.models.Book;
import revisao_oop.models.Review;

public class FormPageBuilder {

    public static FormPageSwing buildBookPage() {
        LibraryRepository repo = currentRepository();
        FormController<Book> controller = new BookFormController(repo);
        BookSwingForm form = new BookSwingForm(controller);
        return new FormPageSwing(form, Pages.books);
    }


    public static FormPageSwing buildReviewPage() {
        LibraryRepository repo = currentRepository();
        FormController<Review> controller = new ReviewFormController(repo);
        ReviewSwingForm form = new ReviewSwingForm(controller);
        return new FormPageSwing(form, Pages.reviews);
    }

    private static LibraryRepository currentRepository() {
        return LibrarySqliteRepository.getInstance();
    }
}
