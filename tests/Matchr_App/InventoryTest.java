package Matchr_App;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

// tests that the Inventory methods for reading and writing racquet data
public class InventoryTest {

    // tests the reading of excel file
    // Precondition: data for exactly 5 racquets are provided in file "racquet_inventory.xlsx"
    @Test
    public void readFromExcel() {

        // prepare the inventory and output .txt file
        Inventory.clearRacquets();
        Inventory.readFromExcel();
        Inventory.writeToText();
        ArrayList<Racquet> inventory = Inventory.getInventory();

        // test the new inventory size matches the number of racquets in input file
        assertEquals(5, inventory.size());
    }

    // tests the writing to txt file
    @Test
    public void writeToText() {
        // test the output file has been created
        File outputFile = new File("static/racquet_output.txt");
        assertTrue(outputFile.exists());
    }

    // tests the creation of a Racquet object
    @Test
    public void createRacquet() {
        String[] attributes = {"1", "testBrand", "testModel",
                "5", "6", "7", "2", "3", "4", "7.1"};
        Racquet testRacquet = Inventory.createRacquet(Arrays.asList(attributes));

        // test that a single Racquet object is created successfully with correct fields
        assertEquals(10, testRacquet.getClass().getDeclaredFields().length - 1);
        assertEquals(4, testRacquet.getStrength());
    }
}