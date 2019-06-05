package model.Book;

import model.Library;
import model.Person.User;

import java.io.Serializable;

public abstract class Book implements Serializable{
    protected String category;
    private final String bookName;
    private boolean isAvailable;
    private String holder;
    private Library library;

    public Book(String bookName, boolean isAvailable) {
        this.bookName = bookName;
        this.isAvailable = isAvailable;
        this.holder = null;
        this.library = null;
    }

    //EFFECTS: return the name of the book
    public String getBookName() {
        return bookName;
    }

    //EFFECTS: return the name of the holder of the book
    public String getHolder() {
        return holder;
    }

    //MODIFIES: this
    //EFFECTS: set the name of the holder of the book
    public void setHolder(String holder) {
        this.holder = holder;
    }

    public Library getLibrary() {
        return this.library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    //EFFECTS: return true if the book is available in the library;
    //         of false if the book is not available in the library
    public boolean checkAvailability() {
        return this.isAvailable;
    }

    //MODIFIES: this
    //EFFECTS: change the available status of the book
    public void changeAvailability() {
        this.isAvailable = !this.isAvailable;
    }

    @Override
    public String toString() {
        return this.bookName;
    }

    public abstract String getCategory() ;
}
