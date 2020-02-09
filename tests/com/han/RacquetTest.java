package com.han;

import org.junit.Test;

import static org.junit.Assert.*;

public class RacquetTest {

    Racquet testRac = new Racquet(1, "testBrand", "testModel", "speed",
            3, 4, 5, 6.8F, 3, 5, 4
            );

    User testUser = new User("testBrand", "speed",
            3, 5, 8,
            4, 2, 5, 7.6F);

    User testUserEmpty = new User();

    @Test
    public void calcMatchIndex() {
        // for testing a user with all values selected
        assertEquals(9.8, testRac.calcMatchIndex(testUser), 0.05);

        // for testing a user with some values selected - diffs should be ignored
        testUserEmpty.setStrengthPref(2);
        testUserEmpty.setStylePref(4);
        testUserEmpty.setSkillPref(3);
        testUserEmpty.setShaftPref(8.1F);
        assertEquals(6.3, testRac.calcMatchIndex(testUserEmpty), 0.05);
    }
}