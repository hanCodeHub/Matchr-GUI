package com.han;

import org.junit.Test;

import static org.junit.Assert.*;

public class AdvancedSessionTest {

    private AdvancedSession testSession = new AdvancedSession();

    @Test
    public void getQuestions() {
        assertEquals(3, testSession.getQuestions().size());
    }

}