package model.Person;

import model.Book.Book;
import model.Database;
import model.Library;

import java.util.ArrayList;
import java.util.Observable;

public abstract class Person extends Observable{
    protected String name;
    protected Library library;
    protected ArrayList<Book> personalBookList;
    protected Database database;

    public abstract void viewBookList();

    public String getPersonName() {
        return this.name;
    }

    public ArrayList<Book> getPersonalBookList() {
        return this.personalBookList;
    }
}
