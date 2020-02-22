package Matchr_App;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;
import static org.junit.Assert.*;


// tests the saving and retrieving of Racquet objects to and from XML
public class XMLSerializerTest {

    // ensures inventory is updated with racquets from excel
    @Before
    public void updateInventory() {
        Inventory.clearRacquets();
        Inventory.readFromExcel();
    }

    // tests both XML serialization and deserialization
    @Test
    public void xmlSerialization() {
        // retrieves racquet data from excel file
        Racquets inventory = new Racquets(Inventory.getInventory());

        // saves racquet objects to XML file
        XMLSerializer racquetXMLSerializer= new XMLSerializer();
        racquetXMLSerializer.saveToXML(inventory);

        // tests the output file has been created
        File outputFile = new File("static/racquet_objects.xml");
        assertTrue(outputFile.exists());

        // read objects from XML
        Racquets racquets = racquetXMLSerializer.readFromXML();

        // obtain lists from xml and inventory objects
        List<Racquet> srcRacquets = inventory.getRacquets();  // objects from inventory
        List<Racquet> xmlRacquets = racquets.getRacquets();  // objects from xml

        // same number of objects in both lists
        boolean sameNumberObjects = srcRacquets.size() == xmlRacquets.size();

        // same objects found in xml is found in source (inventory)
        boolean allObjectsMatch = xmlRacquets.stream()
                .allMatch(xmlE -> srcRacquets.stream()
                        .anyMatch(inventoryE -> xmlE.getId() == inventoryE.getId())
                );

        // tests that the objects in both xml and inventory are the same
        assertTrue(allObjectsMatch && sameNumberObjects);
    }


}