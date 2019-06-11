package test;

import model.Book.Book;
import model.Book.StudyBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookTest {
    public static final String TEST_BOOK_NAME = "CPSC210 Textbook";
    public static final String TEST_HOLDER = "Zipeng";
    private Book testBook;

    @BeforeEach
    public void runBefore() {
        testBook = new StudyBook(TEST_BOOK_NAME, true);
    }

    @Test
    public void testConstructor(){
        assertEquals(TEST_BOOK_NAME, testBook.getBookName());
        assertEquals("Library", testBook.getHolder());
    }

    @Test
    public void testGetName(){
        assertEquals(TEST_BOOK_NAME, testBook.getBookName());
    }

    @Test
    public void testGetHolder(){
        assertEquals("Library", testBook.getHolder());
    }

//    @Test
//    public void testSetHolder(){
//        testBook.setHolder(TEST_HOLDER);
//        assertEquals(TEST_HOLDER, testBook.getHolder());
//    }

    @Test
    public void testCheckAvailability(){
        assertTrue(testBook.checkAvailability());
    }

    @Test
    public void testChangeAvailability(){
        testBook.changeAvailability();
        assertFalse(testBook.checkAvailability());
    }
}
