package Matchr_App;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class RacquetModelTest {

    // ensures that raw data is read from excel
    @Before
    public void setUp() {
        Inventory.readFromExcel();
    }

    // tests reading from and writing to the racquets table in sqlite database
    @Test // also tests getAllRacquets()
    public void insertRacquets() {

        // read the excel source data into the database
        ArrayList<Racquet> racquetsSrc = Inventory.getInventory();
        RacquetModel racquetModel = new RacquetModel();
        racquetModel.insertRacquets(racquetsSrc);

        // retrieve racquet data from database (ordered alphabetically)
        List<Racquet> racquetsDB = racquetModel.getAllRacquets();

        // both racquet lists are sorted by id using compareTo() defined in Racquet class
        Collections.sort(racquetsDB);
        Collections.sort(racquetsSrc);

        // compares equality using equals() defined in Racquet class
        assertEquals(racquetsDB, racquetsSrc);

    }

}