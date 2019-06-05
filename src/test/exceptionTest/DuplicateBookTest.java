package test.exceptionTest;

import exceptions.DuplicateBook;
import exceptions.EmptyName;
import model.Book.Book;
import model.Book.StudyBook;
import model.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DuplicateBookTest {
    public static final Book FIRST_TEST_BOOK = new StudyBook("CPSC210 Textbook", true);
    public static final Book SECOND_TEST_BOOK = new StudyBook("CPSC110 Textbook", true);
    private Library testLibrary;

    @BeforeEach
    public void runBefore() {
        testLibrary = new Library();
    }

    @Test
    public void testNotDuplicateBook(){
        try {
            testLibrary.add(FIRST_TEST_BOOK);
            assertEquals(1, testLibrary.getBookListSize());
        }
        catch (DuplicateBook duplicateBook) {
            fail("Shouldn't be caught");
        } catch (EmptyName emptyName) {
            emptyName.printStackTrace();
        }

        try {
            testLibrary.add(SECOND_TEST_BOOK);
            assertEquals(2, testLibrary.getBookListSize());
        }
        catch (DuplicateBook duplicateBook) {
            fail("Shouldn't be caught");
        } catch (EmptyName emptyName) {
            emptyName.printStackTrace();
        }
    }

    @Test
    public void testDuplicateBook(){
        try {
            testLibrary.add(FIRST_TEST_BOOK);
            assertEquals(1, testLibrary.getBookListSize());
        }
        catch (DuplicateBook duplicateBook) {
            fail("Shouldn't be caught");
        }
        catch (EmptyName emptyName) {
        }

        try {
            testLibrary.add(FIRST_TEST_BOOK);
            fail("This duplicate book should not be added");
        }
        catch (DuplicateBook duplicateBook) {
        } catch (EmptyName emptyName) {
            emptyName.printStackTrace();
        }

    }


}
