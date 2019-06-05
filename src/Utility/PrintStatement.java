package Utility;

import model.Person.Librarian;
import model.Person.Person;

public class PrintStatement {
    //EFFECTS: show the print options
    public static String printOption(Person person) {
        if (person instanceof Librarian)
            return "Hello, " + person.getPersonName()
                    + "\nPlease enter what you would like to do: "
                    + "\n[1] add a book "
                    + "\n[2] remove a book"
                    + "\n[3] loan a book "
                    + "\n[4] see all the books"
                    + "\n[5] view one particular user's book list"
                    + "\n[6] quit";

        else  return "Hello, " + person.getPersonName()
                + "\nPlease enter what you would like to do: "
                + "\n[1] borrow a book "
                + "\n[2] see all the books"
                + "\n[3] see you borrowed book list"
                + "\n[4] return a book"
                + "\n[5] quit";
    }

    public static String printLibrarianOrUser() {
        return "Are you the librarian or the user："
                +"\n[1] library administrator "
                +"\n[2] user";
    }
}
