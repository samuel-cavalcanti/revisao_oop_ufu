package reviso_oop.libraryDataBase;

public class IncorrectInputError extends RuntimeException {
    public String field;

    public IncorrectInputError(String message, String field) {
        super(message);
        this.field = field;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
