package revisao_oop.libraryRepository.inputValidation;

import org.jetbrains.annotations.NotNull;
import revisao_oop.models.Review;

public class ReviewValidation implements InputValidation<Review> {
    @Override
    public Review validate(@NotNull Review r) {

        if (r.title.length() == 0)
            throw new IncorrectInputException("title field not exist !!", "title");

        if (r.organization.length() == 0)
            throw new IncorrectInputException("organization field not exist !!", "organization");

        if (r.volume.length() == 0)
            throw new IncorrectInputException("volume field not exist !!", "volume");

        if (r.number.length() == 0)
            throw new IncorrectInputException("number field not exist !!", "number");

        if (r.year.length() == 0)
            throw new IncorrectInputException("year field not exist !!", "year");

        return r;
    }
}
