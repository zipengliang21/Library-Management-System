package model.Person;

import Utility.JOptionPane;
import exceptions.DuplicateBook;
import exceptions.EmptyName;
import exceptions.InvalidBookCategory;
import model.Book.Novel;
import model.Book.ScienceFiction;
import model.Book.StudyBook;
import model.Database;
import model.Library;

import java.util.ArrayList;

import static Utility.GenerateSoundWithoutStop.generateMusic;
import static Utility.JOptionPane.displayWindow;
import static Utility.JOptionPane.getInputWindow;
import static Utility.JOptionPane.warningWindow;

public class Librarian extends Person{

    public static final String librarianName = "Zipeng";
    public static final String password = "zipeng123";

    public Librarian(String name, Library library, Database database) {
        this.name = name;
        this.library = library;
        this.personalBookList = new ArrayList<>();
        this.database = database;
    }

    @Override
    public void viewBookList() {
        JOptionPane.displayWindow(this.library.toString());
    }

    public void addBook(String bookName, String bookCategory) throws InvalidBookCategory, DuplicateBook, EmptyName {
        switch (bookCategory) {
            case "study book":
                this.library.add(new StudyBook(bookName, true));
                break;
            case "novel":
                this.library.add(new Novel(bookName, true));
                break;
            case "science fiction":
                this.library.add(new ScienceFiction(bookName, true));
                break;
            default:
                throw new InvalidBookCategory(bookCategory);
        }
    }
    /*
    public void addBook() throws InvalidBookCategory, DuplicateBook, EmptyName {
        String bookName;
        String bookCategory;
        bookName = getInputWindow("Please enter the name of the book which you want to add: ");
        if (bookName.equals("")) {
            throw new EmptyName();
        }
        bookCategory = getInputWindow("Please enter the category of the book: ");
        switch (bookCategory) {
            case "study book":
                this.library.add(new StudyBook(bookName, true));
                break;
            case "novel":
                this.library.add(new Novel(bookName, true));
                break;
            case "science fiction":
                this.library.add(new ScienceFiction(bookName, true));
                break;
            default:
                throw new InvalidBookCategory(bookCategory);
        }
        displayWindow("Thank you for adding " + bookName);
    }
    */

    public void removeBook() throws EmptyName {
        String bookName;
        bookName = getInputWindow("Please enter the name of the book which you want to remove: ").trim();
        if (bookName == null) {
            throw new NullPointerException();
        }
        if (bookName.equals("")) {
            throw new EmptyName();
        }
        if (this.library.remove(bookName)) {
            generateMusic("1_person_cheering.wav");
            displayWindow("Thank you for removing " + bookName);
        }
        else  {
            generateMusic("beep-03.wav");
            displayWindow("There is no book called" + bookName + " in this library");
        }
    }

    public void loanBook() throws EmptyName {
        String bookName;
        String personName;
        bookName = getInputWindow("Please enter the name of the book which you want to loan: ");
        throwNullOrEmptyException(bookName);
        personName = getInputWindow("Please enter the user name");
        throwNullOrEmptyException(personName);
        User user = null;

        for (User listUser: library.getDatabase().getUserBookList().keySet()) {
            if (listUser.getPersonName().equals(personName)) {
                user = listUser;
            }
        }

        if (user == null) {
            generateMusic("beep-03.wav");
            warningWindow("There is no person called " + personName + " in this library");
            return;
        }
        if (this.library.loan(bookName,user)) {
            generateMusic("1_person_cheering.wav");
            displayWindow("The book " + bookName + " is loan successfully");
        }
        else {
            generateMusic("beep-03.wav");
            warningWindow("The book " + bookName + " is not in the library right now");}
    }

    public void viewOneUserBookList() throws EmptyName {
        String userName = getInputWindow("You want to check one user's book list, please enter this user's name");
        throwNullOrEmptyException(userName);
        User user = null;

        for (User listUser: this.database.getUserBookList().keySet()) {
            if (listUser.getPersonName().equals(userName)) {
                user = listUser;
            }
        }
        if(! (user == null)) {
            generateMusic("1_person_cheering.wav");
            displayWindow(this.database.getUserBookList().get(user).toString());
        }
        else {
            generateMusic("beep-03.wav");
            displayWindow("Our library does not have this user");
        }
    }

    private void throwNullOrEmptyException(String Name) throws EmptyName {
        if (Name == null) {
            throw new NullPointerException();
        }
        else if (Name.equals("")) {
            throw new EmptyName();
        }
    }
}
