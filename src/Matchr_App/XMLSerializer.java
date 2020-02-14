package Matchr_App;


import org.apache.poi.ss.formula.functions.T;

import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlElement;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


// Serializes Racquet objects: saves to or retrieves from XML
public class XMLSerializer {

    private static final Path xmlPath = Paths.get("static/racquet_objects.xml");

    // saves racquet objects as XML in xmlPath
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

    // reads racquet objects from XML in xmlPath
    // Precondition: file "racquet_objects.xml" must exist in static directory
    public Racquets readFromXML () {
        Racquets racquets = new Racquets();

        try (BufferedReader input = Files.newBufferedReader(xmlPath)) {

            // read XML from file into the Racquets class
            racquets = JAXB.unmarshal(input, Racquets.class);

        } catch (NoSuchElementException | IOException e){
            System.out.println("Cannot deserialize objects from XML");
            e.printStackTrace();
        }
        return racquets;
    }

}
