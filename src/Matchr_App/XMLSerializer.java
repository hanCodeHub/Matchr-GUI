package Matchr_App;

import javax.xml.bind.JAXB;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import java.util.NoSuchElementException;

// Serializes Racquet objects: saves to or retrieves from XML
public class XMLSerializer {

    private static final Path xmlPath = Paths.get("static/racquet_objects.xml");

    // saves racquet objects as XML in file located at xmlPath
    // Precondition: objects in listObjects must be serializable as XML
    public void saveToXML(Racquets racquets) {
        try (BufferedWriter output = Files.newBufferedWriter(xmlPath)) {

            // write objects' XML to output
            JAXB.marshal(racquets, output);

        } catch (NoSuchElementException | IOException e){
            System.out.println("Cannot serialize objects into XML");
            e.printStackTrace();
        }
    }

    // reads racquet objects from XML in file located at xmlPath
    // Precondition: file "racquet_objects.xml" must exist in static directory
    public Racquets readFromXML () {
        Racquets racquets = new Racquets();

        try (BufferedReader input = Files.newBufferedReader(xmlPath)) {

            // read XML from file into the Racquets class
            racquets = JAXB.unmarshal(input, racquets.getClass());

        } catch (NoSuchElementException | IOException e){
            System.out.println("Cannot deserialize objects from XML");
            e.printStackTrace();
        }
        return racquets;
    }

}
