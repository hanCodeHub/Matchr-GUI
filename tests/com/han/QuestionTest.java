package com.han;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionTest {
    Question<Integer> testQ = new Question<>("testName", "testQuestion"
    , 5, 10);

    @Test
    public void isWithinRange() {
        assertEquals(true, testQ.isWithinRange(6));
        assertEquals(true, testQ.isWithinRange(5));
        assertEquals(true, testQ.isWithinRange(10));
        assertEquals(false, testQ.isWithinRange(11));
        assertEquals(false, testQ.isWithinRange(4));
    }
}