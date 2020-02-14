package Matchr_App;

import org.junit.Test;


import java.util.List;

import static org.junit.Assert.*;

public class XMLSerializerTest {


    @Test
    public void saveToXML() {
        // prepare racquet data from excel file
        Inventory.updateInventory();
        List<Racquet> inventory = Inventory.getInventory();
        Racquets racquets = new Racquets(inventory);

        // saves racquets to XML file
        XMLSerializer racquetXMLSerializer= new XMLSerializer();
        racquetXMLSerializer.saveToXML(racquets);

    }

    @Test
    public void readFromXML() {
        XMLSerializer racquetDeserializer= new XMLSerializer();
        Racquets racquets = racquetDeserializer.readFromXML();
        System.out.println(racquets.getRacquets());
    }
}