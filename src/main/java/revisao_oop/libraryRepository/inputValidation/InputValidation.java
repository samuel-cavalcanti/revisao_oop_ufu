package revisao_oop.libraryRepository.inputValidation;

import org.jetbrains.annotations.NotNull;

public interface InputValidation<Model> {

    Model validate(@NotNull Model m);
}
