//package test.exceptionTest;
//
//import exceptions.DuplicateBook;
//import exceptions.EmptyName;
//import model.Book.Book;
//import model.Book.StudyBook;
//import model.Library;
//import model.Person.Librarian;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;
//
//public class EmptyNameTest {
//    public static final Book FIRST_TEST_BOOK = new StudyBook("CPSC210 Textbook", true);
//    public static final Book SECOND_TEST_BOOK = new StudyBook("", true);
//    private Library testLibrary;
//    public static final String personName = "Zipeng";
//    private Librarian testLibrarian;
//
//    @BeforeEach
//    public void runBefore() {
//        testLibrary = new Library();
//        testLibrarian = new Librarian(personName, testLibrary);
//    }
//
//    @Test
//    public void testAddNonEmptyBookName(){
//        try {
//            testLibrary.add(FIRST_TEST_BOOK);
//            assertEquals(1, testLibrary.getBookListSize());
//        }
//        catch (EmptyName e) {
//            fail("Shouldn't be caught");
//        }
//        catch (DuplicateBook duplicateBook) {
//            fail("Shouldn't be caught in here");
//        }
//
//    }
//
//    @Test
//    public void testAddEmptyBookName(){
//        try {
//            testLibrary.add(SECOND_TEST_BOOK);
//            assertEquals(0, testLibrary.getBookListSize());
//            fail("Should be caught in the exception");
//        }
//        catch (EmptyName e) {
//        }
//        catch (DuplicateBook duplicateBook) {
//            fail("Wrong exception");
//        }
//
//    }
//}
