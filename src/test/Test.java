package test;

import model.Book.Book;

public class Test extends Book {
    private static final String CATEGORY = "test";

    public Test(String bookName, boolean isAvailable) {
        super(bookName, isAvailable);
        this.category = CATEGORY;
    }

    @Override
    public String getCategory() {
        return this.category;
    }
}
