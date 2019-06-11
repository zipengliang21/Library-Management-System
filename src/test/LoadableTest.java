//package test;
//
//import model.Book.Book;
//import model.Book.StudyBook;
//import model.Library;
//import model.Loadable;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.File;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class LoadableTest {
//    public static final String TEST_BOOK1_NAME = "210Textbook";
//    public static final String TEST_BOOK2_NAME = "121Textbook";
//    public static final String TEST_BOOK1_HOLDER = "Zipeng";
//    public static final String TEST_BOOK2_HOLDER = "Library";
//
//    private Book testBook1;
//    private Book testBook2;
//    private Loadable testLibrary;
//
//    @BeforeEach
//    public void runBefore() {
//        testBook1 = new StudyBook(TEST_BOOK1_NAME, true);
//        testBook2 = new StudyBook(TEST_BOOK2_NAME, true);
//        testLibrary = new Library();
//
//    }
//
//    @Test
//    public void testLoad() {
//        testLibrary.load(new File("testFile.txt"));
//        testBookName((Library) testLibrary);
//        testBookAvailability((Library) testLibrary);
//        testBookHolder((Library) testLibrary);
//
//    }
//
//    public void testBookName(Library testLibrary) {
//        assertEquals(TEST_BOOK1_NAME, testLibrary.getRecordBookList().get(0).getBookName());
//        assertEquals(TEST_BOOK2_NAME, testLibrary.getRecordBookList().get(1).getBookName());
//    }
//
//    public void testBookAvailability(Library testLibrary) {
//        assertFalse(testLibrary.getRecordBookList().get(0).checkAvailability());
//        assertTrue(testLibrary.getRecordBookList().get(1).checkAvailability());
//    }
//
//
//    public void testBookHolder(Library testLibrary) {
//        testLibrary.getRecordBookList().get(0).setHolder(TEST_BOOK1_HOLDER);
//        assertEquals(TEST_BOOK1_HOLDER, testLibrary.getRecordBookList().get(0).getHolder());
//        assertEquals(TEST_BOOK2_HOLDER, testLibrary.getRecordBookList().get(1).getHolder());
//    }
//}
//
//
//
//
