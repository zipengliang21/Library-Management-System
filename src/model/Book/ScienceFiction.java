package model.Book;

public class ScienceFiction extends Book{
    public static final String CATEGORY = "scienceFiction";

    public ScienceFiction(String bookName, boolean isAvailable) {
        super(bookName, isAvailable);
        this.category = CATEGORY;
    }

    @Override
    public String getCategory() {
        return this.category;
    }
}
