package test.exceptionTest;

import exceptions.DuplicateBook;
import exceptions.EmptyName;
import exceptions.InvalidBookCategory;
import model.Book.Book;
import model.Book.StudyBook;
import model.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class InvalidBookCategoryTest {
    public static final Book FIRST_TEST_BOOK = new StudyBook("CPSC210 Textbook", true);
    public static final Book SECOND_TEST_BOOK = new test.Test("CPSC110 Textbook", true);
    private Library testLibrary;

    @BeforeEach
    public void runBefore() {
        testLibrary = new Library();
    }

    @Test
    public void testValidCategoryBook(){
        try {
            testLibrary.add(FIRST_TEST_BOOK);
            assertEquals(1, testLibrary.getBookListSize());
            if (!FIRST_TEST_BOOK.getCategory().equals("studyBook") ) {
                throw new InvalidBookCategory(FIRST_TEST_BOOK.getCategory());
            }
        }
        catch (DuplicateBook duplicateBook) {
            fail("Shouldn't be caught");
        } catch (EmptyName emptyName) {
            fail("Shouldn't be caught in here");
        } catch (InvalidBookCategory invalidBookCategory) {
            fail("Shouldn't be caught in here !");
        }

    }

    @Test
    public void testInvalidCategoryBook(){
        try {
            testLibrary.add(SECOND_TEST_BOOK);
            assertEquals(1, testLibrary.getBookListSize());
            if (!FIRST_TEST_BOOK.getCategory().equals("studyBook") ||!FIRST_TEST_BOOK.getCategory().equals("novel")||!FIRST_TEST_BOOK.getCategory().equals("scienceFiction")) {
                throw new InvalidBookCategory(FIRST_TEST_BOOK.getCategory());
            }
        }
        catch (DuplicateBook duplicateBook) {
            fail("Shouldn't be caught");
        } catch (EmptyName emptyName) {
            fail("Shouldn't be caught in here");
        } catch (InvalidBookCategory invalidBookCategory) {
        }

    }
}
