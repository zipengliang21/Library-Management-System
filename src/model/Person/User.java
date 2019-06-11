package model.Person;

import Observer.LibraryLog;
import Utility.JOptionPane;
import exceptions.EmptyName;
import model.Book.Book;
import model.Book.Novel;
import model.Book.ScienceFiction;
import model.Book.StudyBook;
import model.Library;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import static Utility.GenerateSoundWithoutStop.generateMusic;
import static Utility.JOptionPane.displayWindow;
import static Utility.JOptionPane.getInputWindow;
import static Utility.JOptionPane.warningWindow;

public class User extends Person{

    private LibraryLog libraryLog;
    private Date date;
    private String password;
    public User(String name, String password, Library library, LibraryLog libraryLog) {
        this.name = name;
        this.library = library;
        this.personalBookList = new ArrayList<>();
        this.libraryLog = libraryLog;
        addObserver(libraryLog);
        date = new Date();
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void viewBookList() {
        StringBuilder bookListName = new StringBuilder("Book:\n");
        bookListName.append("*StudyBook:\n");
        for (Book book: this.library.getRecordBookList()) {
            if (book.getCategory().equals(StudyBook.CATEGORY)) {
                appendAvailability(bookListName, book);
            }
        }
        bookListName.append("\n*Novel:\n");
        for (Book book: this.library.getRecordBookList()) {
            if (book.getCategory().equals(Novel.CATEGORY)) {
                appendAvailability(bookListName, book);
            }
        }
        bookListName.append("\n*ScienceFiction:\n");
        for (Book book: this.library.getRecordBookList()) {
            if (book.getCategory().equals(ScienceFiction.CATEGORY)) {
                appendAvailability(bookListName, book);
            }
        }
        JOptionPane.displayWindow(bookListName.toString());
    }

    public void borrowBook() throws EmptyName {
        String bookName = getBookName("Please enter the name of the book which you want to borrow: ");
        if (this.library.loan(bookName,this)){
            generateMusic("1_person_cheering.wav");
            displayWindow("The book " + bookName + " is borrowed successfully");
            displayWindow("you have borrowed" + this.getPersonalBookList());
            this.libraryLog.record(date.toString());
            this.libraryLog.record(this.name + " has borrowed " + bookName);
            setChanged();
            notifyObservers(this);
        }
        else {
            generateMusic("beep-03.wav");
            warningWindow("The book " + bookName + " is not in the library right now");}
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return this.name.equals(user.name) && this.password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,password,library);
    }

    public void viewPersonalBookList() {
        if (this.getPersonalBookList().size() == 0) {
            displayWindow("You are not borrowing any books right now.");
        }
        else if(this.getPersonalBookList().size() > 0) {
            displayWindow("you have borrowed" + this.getPersonalBookList());
        }
    }

    public void returnBook() throws EmptyName {
        String bookName = getBookName("Please enter the name of the book which you want to return: ");
        if (this.library.returnBook(bookName,this)){
            generateMusic("1_person_cheering.wav");
            displayWindow("The book " + bookName + " is returned successfully");
            viewPersonalBookList();
            this.libraryLog.record(date.toString());
            this.libraryLog.record(this.name + " return " + bookName + " successfully");
            setChanged();
            notifyObservers(this);
        }
        else {
            generateMusic("beep-03.wav");
            warningWindow("You didn't borrow the book " + bookName);}
    }

    private String getBookName(String s) throws EmptyName {
        String bookName;
        bookName = getInputWindow(s);
        if (bookName == null) {
            throw new NullPointerException();
        }
        if (bookName.equals("")) {
            throw new EmptyName();
        }
        return bookName;
    }

    private void appendAvailability(StringBuilder bookListName, Book book) {
        if (book.checkAvailability()) {
            bookListName.append(book.getBookName()).append(" (Available)\n");
        }
        else bookListName.append(book.getBookName()).append(" (Unavailable)\n");
    }
}
