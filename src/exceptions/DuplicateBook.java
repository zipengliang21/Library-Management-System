package exceptions;

public class DuplicateBook extends Exception {
    public DuplicateBook(String duplicateBook) {
        super(duplicateBook);
    }
}
