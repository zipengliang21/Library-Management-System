package model.Book;

public class Novel extends Book{
    public static final String CATEGORY = "novel";

    public Novel(String bookName, boolean isAvailable) {
        super(bookName, isAvailable);
        this.category = CATEGORY;
    }

    @Override
    public String getCategory() {
        return this.category;
    }
}
