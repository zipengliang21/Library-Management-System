//package test;
//
//import model.Book.Book;
//import model.Book.StudyBook;
//import model.Library;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class LibraryTest {
//    public static final Book FIRST_TEST_BOOK = new StudyBook("CPSC210 Textbook", true);
//    public static final Book SECOND_TEST_BOOK = new StudyBook("CPSC110 Textbook", true);
//    public static final Book THIRD_TEST_BOOK = new StudyBook("CPSC121 Textbook", true);
//    public static final String personName = "Zipeng";
//    private Library testLibrary;
//
//    @BeforeEach
//    public void runBefore() {
//        testLibrary = new Library();
//    }
//
//    @Test
//    public void testConstructor(){
//        assertEquals(0, testLibrary.getBookListSize());
//    }
//
//    @Test
//    public void testAdd(){
//        testLibrary.add(FIRST_TEST_BOOK);
//        assertEquals(1, testLibrary.getBookListSize());
//        testLibrary.add(SECOND_TEST_BOOK);
//        assertEquals(2, testLibrary.getBookListSize());
//    }
//
//    @Test
//    public void testRemoveSuccessfully(){
//        testLibrary.add(FIRST_TEST_BOOK);
//        testLibrary.add(SECOND_TEST_BOOK);
//        testLibrary.add(THIRD_TEST_BOOK);
//        assertTrue(testLibrary.remove(FIRST_TEST_BOOK.getBookName()));
//        assertEquals(2, testLibrary.getBookListSize());
//        assertTrue(testLibrary.remove(SECOND_TEST_BOOK.getBookName()));
//        assertEquals(1, testLibrary.getBookListSize());
//        assertTrue(testLibrary.remove(THIRD_TEST_BOOK.getBookName()));
//        assertEquals(0, testLibrary.getBookListSize());
//    }
//
//    @Test
//    public void testRemoveUnsuccessfully(){
//        testLibrary.add(FIRST_TEST_BOOK);
//        testLibrary.add(SECOND_TEST_BOOK);
//        testLibrary.add(THIRD_TEST_BOOK);
//        assertFalse(testLibrary.remove("CPSC213 Textbook"));
//        assertEquals(3, testLibrary.getBookListSize());
//        assertFalse(testLibrary.remove("CPSC221 Textbook"));
//        assertEquals(3, testLibrary.getBookListSize());
//    }
//
//    @Test
//    public void testBookListSize(){
//        testLibrary.add(FIRST_TEST_BOOK);
//        assertEquals(1, testLibrary.getBookListSize());
//        testLibrary.add(SECOND_TEST_BOOK);
//        assertEquals(2, testLibrary.getBookListSize());
//    }
//
//    @Test
//    public void testRecordBookListSize(){
//        testLibrary.add(THIRD_TEST_BOOK);
//        assertEquals(1, testLibrary.getRecordBookListSize());
//        assertTrue(testLibrary.loan(THIRD_TEST_BOOK.getBookName(),personName));
//    }
//
//    @Test
//    public void testLoanSuccessfully(){
//        testLibrary.add(FIRST_TEST_BOOK);
//        testLibrary.add(SECOND_TEST_BOOK);
//        assertTrue(testLibrary.loan(FIRST_TEST_BOOK.getBookName(),personName));
//        assertEquals(personName, FIRST_TEST_BOOK.getHolder());
//        assertEquals(1, testLibrary.getBookListSize());
//        assertEquals(personName, FIRST_TEST_BOOK.getHolder());
//        assertEquals(2, testLibrary.getRecordBookListSize());
//
//        assertTrue(testLibrary.loan(SECOND_TEST_BOOK.getBookName(),personName));
//        assertEquals(0, testLibrary.getBookListSize());
//        assertEquals(2, testLibrary.getRecordBookListSize());
//    }
//
//    @Test
//    public void testLoanUnsuccessfully(){
//        testLibrary.add(FIRST_TEST_BOOK);
//        testLibrary.add(SECOND_TEST_BOOK);
//        assertFalse(testLibrary.loan(" ",personName));
//        assertEquals(2, testLibrary.getBookListSize());
//        assertEquals(2, testLibrary.getRecordBookListSize());
//
//    }
//}
