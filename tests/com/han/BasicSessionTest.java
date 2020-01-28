package com.han;

import org.junit.Test;

import static org.junit.Assert.*;

public class BasicSessionTest {

    private Session testSession = new BasicSession();

    @Test
    public void getQuestions() {
        assertEquals(3, testSession.getQuestions().size());
    }
}