package model;

import model.Book.Book;
import model.Person.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    private HashMap<User, ArrayList<Book>> userBookList;

    public Database() {
        userBookList = new HashMap<>();
    }

    public HashMap<User, ArrayList<Book>> getUserBookList() {
        return userBookList;
    }
}
