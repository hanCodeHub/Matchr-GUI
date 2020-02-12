package Matchr_App;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionTest {
    Question<Integer> testQ = new Question<>("testName", "testQuestion"
    , 5, 10);

    @Test
    public void isWithinRange() {
        // test that only values between low and high return true
        assertTrue(testQ.isWithinRange(6));
        assertTrue(testQ.isWithinRange(5));
        assertTrue(testQ.isWithinRange(10));
        assertFalse(testQ.isWithinRange(11));
        assertFalse(testQ.isWithinRange(4));
    }

    @Test
    public void validateText() {
        // test that only one single word with alphabetic chars return true
        assertTrue(testQ.validateText("testWord"));
        assertFalse(testQ.validateText("test word"));
        assertFalse(testQ.validateText("test123"));
    }
}