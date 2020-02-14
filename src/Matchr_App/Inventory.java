package Matchr_App;

import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Inventory {

    // relative path to project's static assets
    private static final Path inputPath = Paths.get("static/racquet_inventory.xlsx");
    private static final Path outputPath = Paths.get("static/racquet_output.txt");

    // inventory of Racquet objects
    private static ArrayList<Racquet> racquetList = new ArrayList<>(20);

    // returns a copy of inventory of racquets to be used for recommendations
    public static ArrayList<Racquet> getInventory() {
        return new ArrayList<>(racquetList);
    }

    // reads inventory data from excel file of given file path
    // then populates the racquetList with Racquet objects
    // PRECONDITION: table headers in input file should match class Racquet attributes
    public static void readFromExcel() {

        try (FileInputStream file = new FileInputStream(Inventory.inputPath.toFile())) {
            // create a new workbook
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            // obtain the first sheet in workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            // iterate through each row in sheet
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();  // skip the header row
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                // each cell in row is stored as an attribute in temp list
                Iterator<Cell> cellIter = row.cellIterator();
                List<String> attributes = new ArrayList<>(10);

                // iterate through each cell
                while (cellIter.hasNext()) {
                    Cell cell = cellIter.next();
                    // add cell value to list of attributes (as Strings)
                    getCellValue(attributes, cell);
                }
                // create a new Racquet object from attributes
                Racquet newRacquet = createRacquet(attributes);
                if (newRacquet != null)
                    racquetList.add(createRacquet(attributes));
            }
            System.out.println("Racquet inventory successfully updated in the database.\n");
        // if file does not exist
        } catch (FileNotFoundException err) {
            System.out.println(
                    "racquet_inventory.xlsx file does not exist in /static directory.\n" +
                    "Please create the file and restart the program.");
        // if file cannot be opened
        } catch (IOException err) {
            System.out.println("An error occurred when reading the file. " +
                    "You may not have permissions.\n " +
                    "Please check the file and restart the program");
        // if values in the cells cannot be read
        } catch (InputMismatchException err) {
            System.out.println("The format of values in the cells are not correct.");
            System.out.println(err.getMessage());
        }
    }


    // writes inventory to text file. include only {id, brand, model} for each Racquet
    public static void writeToText() throws NotImplementedException {

        Formatter outfile = null;

        try {
            // abort if inventory is empty
            if (racquetList.size() == 0)
                throw new NotImplementedException("The inventory is empty!");

            // open file and write each racquet to it
            outfile = new Formatter(outputPath.toFile());
            outfile.flush();  // flush if used within same session
            outfile.format("INVENTORY OF RACQUETS\n");
            outfile.format("---------------------\n");

            for (Racquet racquet : racquetList) {
                // output format specified in Racquet.toString() method
                if (racquet != null) outfile.format(racquet.toString());
            }
            System.out.println(
                    "See racquet_output.txt for a basic summary of available racquets.\n");

        } catch (SecurityException | FileNotFoundException | FormatterClosedException err ) {
            System.err.println("Cannot open file. Please ensure that racquet_output.txt exists," +
                    "and that you have access to it. Then restart the program.");

        } catch (NotImplementedException err) {
            System.out.println("Could not write data to racquet_output.txt");
            System.out.println(err.getMessage());

        } finally {
            // close out the file if used
            if (outfile != null) outfile.close();
        }
    }


    // returns a Racquet object from a list of attributes
    public static Racquet createRacquet(List<String> attributes) throws NumberFormatException {
        Racquet racquet;
        ListIterator<String> iter = attributes.listIterator();

        try { // String values are converted to ints where appropriate.
            racquet = new Racquet(
                    Integer.parseInt(iter.next()),  // id
                    iter.next(),  // brand
                    iter.next()  // model
            );
            // set remaining properties with validation setters
            racquet.setWeight(Integer.parseInt(iter.next()));  // weight
            racquet.setBalance(Integer.parseInt(iter.next()));  // balance
            racquet.setStiffness(Integer.parseInt(iter.next()));  // stiffness
            racquet.setStyle(Integer.parseInt(iter.next()));  // style
            racquet.setSkill(Integer.parseInt(iter.next()));  // skill
            racquet.setStrength(Integer.parseInt(iter.next()));  // strength
            racquet.setShaftDiameter(Float.parseFloat(iter.next())); // shaftDiameter

        // if stored value cannot be cast into int
        } catch (NumberFormatException err) {
            System.out.println("Racquet data in Excel is invalid.\n" +
                    "Please make sure there is no text where numbers are supposed to be.\n");
            racquet = null;

        // if tried to set a field out of range
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() +
                    "\nracquet will not be stored in inventory.");
            racquet = null;
        }
        // return null racquet if any validation fails to ensure database integrity
        return racquet;
    }


    // extracts the value from a cell and adds it to attributes list
    private static void getCellValue(List<String> attributes, Cell cell)
            throws InputMismatchException {

        // check for cell type and extract data
        CellType cellType = cell.getCellType();

        // add cell value to list of attributes (as Strings)
        if (cellType == CellType.STRING)
            attributes.add(cell.getStringCellValue());
        else if (cellType == CellType.NUMERIC)
            attributes.add(String.valueOf((int) cell.getNumericCellValue()));
        else
            throw new InputMismatchException("cells can only have numeric or text values!");
    }

}
