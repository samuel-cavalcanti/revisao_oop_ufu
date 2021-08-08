package revisao_oop.formPage.FormController;

import org.jetbrains.annotations.NotNull;

public interface FormController<T> {
    void sendModelToDataBase(@NotNull T model);
}
