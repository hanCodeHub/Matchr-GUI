package Matchr_Models;

import Matchr_App.Inventory;
import Matchr_App.Racquet;
import Matchr_App.User;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class UserModelTest {

    // id number of Racquet being tested
    public static final int RACQUET_TEST_ID = 2;

    // creates user, racquet, initialize new users table in db
    private User user = new User();
    private Racquet racquet = new Racquet();
    UserModel userModel = new UserModel();

    // sets up sample user and racquet data
    // Precondition: created racquet must have id currently in the database
    @Before
    public void setUp() throws Exception {
        racquet.setId(RACQUET_TEST_ID);
        user.setName("Han");
        user.setRacquet(racquet);

        // update current inventory
        Inventory.clearRacquets();
        Inventory.readFromExcel();
    }


    // tests inserting a user into users table
    // also tests getting a user's saved racquet from database
    @Test
    public void getUserSavedRacquet() {
        // get racquet name saved in database
        userModel.insertUser(user);
        String userRacquetName = userModel.getUserSavedRacquet(user);

        // get racquet model and brand from source data (excel)
        Racquet racquetSrc = Inventory.getInventory().stream()
                .filter(e -> e.getId() == RACQUET_TEST_ID)
                .collect(Collectors.toList())
                .get(0);
        // format into name
        String srcRacquetName = racquetSrc.getBrand() + " " + racquetSrc.getModel();

        assertEquals(srcRacquetName, userRacquetName);

    }
}