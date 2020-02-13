package Matchr_App;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ItemFinderTest {

    // both test users are closest aligned with racquet id 3
    // test user 1 with no preference for brand
    User testUser1 = new User("",
            3, 6, 7,
            4, 5, 3, 7.5F);

    // test user 2 has preference for brand (racquet id 1)
    User testUser2 = new User("Yonex",
            3, 6, 7,
            4, 5, 3, 7.5F);

    // test racquets


    // test finders
    ItemFinder<Racquet> testFinder1 = new ItemFinder<>(testUser1);
    ItemFinder<Racquet> testFinder2 = new ItemFinder<>(testUser2);


    @Test // also tests getTopResult() and filterBrand()
    public void rankItems() {

        // prepares the test data of racquets
        Inventory.updateInventory();
        List<Racquet> racquets = Inventory.getInventory();
        testFinder1.rankItems(racquets);
        testFinder2.rankItems(racquets);

        // testUser1 should be recommended racquet id 3
        assertEquals(3, testFinder1.getTopResult().getId());
        // testUser2 should be recommended racquet id 1 even though attributes are the same
        assertEquals(1, testFinder2.getTopResult().getId());
    }


    @Test
    public void getResults() {

        // prepares the test data of racquets
        Inventory.updateInventory();
        List<Racquet> racquets = Inventory.getInventory();
        testFinder1.rankItems(racquets);

        // tests that the size of recommended results match given quantity
        assertEquals(2, testFinder1.getResults(2).size());

    }
}