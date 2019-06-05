package model.Book;

public class StudyBook extends Book{
    public static final String CATEGORY = "studyBook";

    public StudyBook(String bookName, boolean isAvailable) {
        super(bookName, isAvailable);
        this.category = CATEGORY;
    }

    @Override
    public String getCategory() {
        return this.category;
    }
}
