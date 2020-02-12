package Matchr_App;

import org.junit.Test;

import static org.junit.Assert.*;

public class RacquetTest {

    Racquet testRac = new Racquet(1, "testBrand", "testModel",
            3, 4, 5, 6.8F, 3, 5, 4
            );

    User testUser = new User("testBrand",
            3, 5, 8,
            4, 2, 5, 7.6F);

    User testUserEmpty = new User();

    @Test
    public void calcMatchIndex() {
        // for testing a user with all values selected
        assertEquals(9.8, testRac.calcMatchIndex(testUser), 0.05);

        // for testing a user with some values selected
        testUserEmpty.setStrengthPref(2);
        testUserEmpty.setStylePref(4);
        testUserEmpty.setSkillPref(3);
        testUserEmpty.setShaftPref(8.1F);
        assertEquals(6.3, testRac.calcMatchIndex(testUserEmpty), 0.05);
    }

    @Test
    public void validateAttr() {
        // test for values that are allowed as a racquet attribute
        assertTrue(testRac.validateAttr(3, 1, 5));
        assertTrue(testRac.validateAttr(1, 1, 5));
        assertTrue(testRac.validateAttr(5, 1, 5));
        assertTrue(testRac.validateAttr(3.5F, 1.0F, 8.8F));

        // not allowed
        assertFalse(testRac.validateAttr(11, 1, 10));
        assertFalse(testRac.validateAttr(3.3F, 6.6F, 10.0F));
    }
}