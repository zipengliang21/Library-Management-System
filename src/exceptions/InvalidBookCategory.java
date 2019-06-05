package exceptions;

public class InvalidBookCategory extends Exception {
    public InvalidBookCategory(String invalidCategory) {
        super(invalidCategory);
    }
}
