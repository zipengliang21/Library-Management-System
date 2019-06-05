package model;

import Observer.LibraryLog;
import Utility.LoadAndSave;
import exceptions.*;
import model.Book.Book;
import model.Book.Novel;
import model.Book.ScienceFiction;
import model.Book.StudyBook;
import model.Person.Librarian;
import model.Person.User;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

import static Utility.GenerateSoundWithoutStop.generateMusic;
import static Utility.JOptionPane.*;
import static Utility.PrintStatement.printLibrarianOrUser;
import static Utility.PrintStatement.printOption;
import static com.sun.javafx.scene.DirtyBits.TEXT_FONT;

public class Library {
    private ArrayList<Book> bookList;
    private ArrayList<Book> recordBookList;
    private LoadAndSave loadAndSave;
    private HashMap<String, ArrayList<Book>> categoryBookList;
    private ArrayList<String> categoryName;
    private Database database;
    private LibraryLog libraryLog;

    public Library() {
        bookList = new ArrayList<>();
        recordBookList = new ArrayList<>();
        categoryBookList = new HashMap<>();
        categoryBookList.put("studyBook", new ArrayList<>());
        categoryBookList.put("novel", new ArrayList<>());
        categoryBookList.put("scienceFiction", new ArrayList<>());
        categoryName = new ArrayList<>();
        categoryName.add("studyBook");
        categoryName.add("novel");
        categoryName.add("scienceFiction");
        loadAndSave = new LoadAndSave();
        database = new Database();
        libraryLog = new LibraryLog();
        loadAndSave.load(new File("dataFile.txt"), new File("UsersBookList.txt"), new File("LibaryLog.txt") ,this);
    }


    public Database getDatabase() {
        return this.database;
    }

    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public ArrayList<Book> getRecordBookList() {
        return recordBookList;
    }

    public HashMap<String, ArrayList<Book>> getCategoryBookList() {
        return categoryBookList;
    }

    public ArrayList<String> getCategoryName() {
        return categoryName;
    }

    //MODIFIES: this
    //EFFECTS: add the given book to the book list
    public void add(Book bookName) throws DuplicateBook, EmptyName {
        boolean containBook = false;
        for (Book book: bookList) {
            if(book.getBookName().equals(bookName.getBookName())) {
                containBook = true;
            }
        }
            if(!containBook){
                if(bookName.getBookName().equals("")) {
                    throw new EmptyName();
                }
                else {
                    bookName.setLibrary(this);
                    bookList.add(bookName);
                    recordBookList.add(bookName);
                    categoryBookList.get(bookName.getCategory()).add(bookName);
                    loadAndSave.save("dataFile.txt", this);
                    generateMusic("1_person_cheering.wav");
                    displayWindow("wow! There is one more book in the library");
                }
            }
            else if (containBook) {
                throw new DuplicateBook(bookName.getBookName());
            }
    }

    //MODIFIES: this
    //EFFECTS: remove the given book from the book list
    public Boolean remove(String bookName) throws EmptyName {
        if(bookName.equals("")) {
            throw new EmptyName();
        }
        else{
            for (Book book: bookList) {
                if (book.getBookName().equals(bookName)) {
                    book.setLibrary(null);
                    book.setHolder(null);
                    bookList.remove(book);
                    recordBookList.remove(book);
                    categoryBookList.get(book.getCategory()).remove(book);
                    loadAndSave.save("dataFile.txt", this);
                    return true;
                }
            }
        }

        return false;
    }

    public Boolean returnBook(String bookName, User user) {
        for (Book book: recordBookList) {
            if (book.getBookName().equals(bookName) && (book.getHolder().equals(user.getPersonName()))) {
                book.changeAvailability();
                book.setHolder(null);
                book.setLibrary(this);
                this.bookList.add(book);
                user.getPersonalBookList().remove(book);
                loadAndSave.save("dataFile.txt", this);
                return true;
            }
        }
        return false;
    }

    //MODIFIES：this
    //EFFECTS: loan the given book to the person
    public Boolean loan(String bookName, User user) {
        for (Book book: bookList) {
            if (book.getBookName().equals(bookName) && book.checkAvailability()) {
                book.changeAvailability();
                book.setHolder(user.getPersonName());
                book.setLibrary(null);
                this.bookList.remove(book);
                user.getPersonalBookList().add(book);
                loadAndSave.save("dataFile.txt", this);
                return true;
            }
        }
        return false;
    }

    //EFFECTS: return the size of the book list
    public int getBookListSize() {
        return bookList.size();
    }


    //EFFECTS: print the names of the books in the recorded list
    @Override
    public String toString() {
        StringBuilder bookListName = new StringBuilder("Book:\n");
        bookListName.append("*StudyBook:\n");
        for (Book book: recordBookList) {
            if (book.getCategory().equals(StudyBook.CATEGORY)) {
                if (book.checkAvailability()) {
                    bookListName.append(book.getBookName()).append(" (Available)\n");
                }
                else bookListName.append(book.getBookName()).append(" (Unavailable, ").append(book.getHolder()).append(" is using the book)\n");
            }
        }
        bookListName.append("\n*Novel:\n");
        for (Book book: recordBookList) {
            if (book.getCategory().equals(Novel.CATEGORY)) {
                if (book.checkAvailability()) {
                    bookListName.append(book.getBookName()).append(" (Available)\n");
                }
                else bookListName.append(book.getBookName()).append(" (Unavailable, ").append(book.getHolder()).append(" is using the book)\n");
            }
        }
        bookListName.append("\n*ScienceFiction:\n");
        for (Book book: recordBookList) {
            if (book.getCategory().equals(ScienceFiction.CATEGORY)) {
                if (book.checkAvailability()) {
                    bookListName.append(book.getBookName()).append(" (Available)\n");
                }
                else bookListName.append(book.getBookName()).append(" (Unavailable, ").append(book.getHolder()).append(" is using the book)\n");
            }
        }
        return bookListName.toString();
    }

    public LibraryLog getLibraryLog() {
        return libraryLog;
    }

    public void register(String registerUserNameString, String registerPasswordString) {
        User user = new User(registerUserNameString, registerPasswordString, this, this.getLibraryLog());
        this.getDatabase().getUserBookList().put(user, user.getPersonalBookList());
        loadAndSave.save("dataFile.txt", this);
    }
}


