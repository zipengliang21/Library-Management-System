//package test;
//
//import model.Book.Book;
//import model.Book.StudyBook;
//import model.Library;
//import model.Saveable;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.File;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class SaveableTest {
//    public static final String TEST_BOOK1_NAME = "210Textbook";
//    public static final String TEST_BOOK2_NAME = "121Textbook";
//    public static final String TEST_BOOK1_HOLDER = "Zipeng";
//    public static final String TEST_BOOK2_HOLDER = "Library";
//
//    private Book testBook1;
//    private Book testBook2;
//    private Saveable testSaveable;
//    private Library testLibrary;
//    private Library useTestLibrary;
//
//    @BeforeEach
//    public void runBefore() {
//        testBook1 = new StudyBook(TEST_BOOK1_NAME, true);
//        testBook2 = new StudyBook(TEST_BOOK2_NAME, true);
//        testSaveable = new Library();
//        testLibrary = (Library) testSaveable;
//        useTestLibrary = new Library();
//
//    }
//
//    @Test
//    public void testSave() {
//        testLibrary.add(testBook1);
//        testLibrary.add(testBook2);
//        testLibrary.getRecordBookList().get(0).changeAvailability();
//        testLibrary.getRecordBookList().get(0).setHolder(TEST_BOOK1_HOLDER);
//        testLibrary.save("outputFile.txt");
//        useTestLibrary.load(new File("outputFile.txt"));
//        testBookName();
//        testBookAvailability();
//        testBookHolder();
//
//    }
//
//    public void testBookName() {
//        assertEquals(TEST_BOOK1_NAME, useTestLibrary.getRecordBookList().get(0).getBookName());
//        assertEquals(TEST_BOOK2_NAME, useTestLibrary.getRecordBookList().get(1).getBookName());
//    }
//
//    public void testBookAvailability() {
//        assertFalse(useTestLibrary.getRecordBookList().get(0).checkAvailability());
//        assertTrue(useTestLibrary.getRecordBookList().get(1).checkAvailability());
//    }
//
//
//    public void testBookHolder() {
//        testLibrary.getRecordBookList().get(0).setHolder(TEST_BOOK1_HOLDER);
//        assertEquals(TEST_BOOK1_HOLDER, useTestLibrary.getRecordBookList().get(0).getHolder());
//        assertEquals(TEST_BOOK2_HOLDER, useTestLibrary.getRecordBookList().get(1).getHolder());
//    }
//}
