package test.exceptionTest;

import exceptions.InvalidNumberInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class InvalidNumberInputExceptionTest {
//    Library testLibrary;
//    Librarian testLibrarian;
//    String testPersonName = "test";
    int validNumebr1;
    int invalidNumber2;

    @BeforeEach
    public void runBefore() {
        validNumebr1 = 1;
        invalidNumber2 = 100;
    }

    @Test
    public void testValidNumber(){

        try {
            if (!(validNumebr1 < 5)) {
                throw new InvalidNumberInputException();
            }
        }
        catch(InvalidNumberInputException e) {
            fail("");
        }
    }

    @Test
    public void testInvalidNumber(){
        try {
            if (!(invalidNumber2 < 5)) {
                throw new InvalidNumberInputException();
            }
            fail("");
        }
        catch(InvalidNumberInputException e) {
        }
    }
}
