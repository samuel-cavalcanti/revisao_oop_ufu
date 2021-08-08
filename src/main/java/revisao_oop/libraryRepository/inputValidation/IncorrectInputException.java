package revisao_oop.libraryRepository.inputValidation;

public class IncorrectInputException extends RuntimeException {
    public String field;

    public IncorrectInputException(String message, String field) {
        super(message);
        this.field = field;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
