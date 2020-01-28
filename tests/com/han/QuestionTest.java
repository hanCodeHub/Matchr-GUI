package com.han;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionTest {

    private Question testQ = new Question("test question", "pick a number",
            1, 5);

    @Test
    public void isValidAnswer() {
        assertEquals(true, testQ.isValidAnswer(3));
        assertEquals(false, testQ.isValidAnswer(6));
        assertEquals(false, testQ.isValidAnswer(0));
    }
}