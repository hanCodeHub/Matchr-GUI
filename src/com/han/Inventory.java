package com.han;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class Inventory {

    // relative path to project + the inventory excel file
    private static String inventoryPath = new File("").getAbsolutePath()
            .concat("/static/racquet_inventory.xlsx");

    // inventory of Racquet objects
    private static ArrayList<Racquet> racquetList = new ArrayList<>(20);

    // returns the list of racquets to client program by reading input file
    public static ArrayList<Racquet> getRacquetList() {
        readFromExcel(inventoryPath);
        System.out.println("Racquet inventory successfully updated.\n");
        return racquetList;
    }

    // writes inventory to text file - include only {id, brand, model}

    // reads inventory data from excel file of given file path
    // then populates the racquetList with Racquet objects
    // PRECONDITION: table headers in input file should match class Racquet attributes
    private static void readFromExcel(String inventoryPath) {

        try (FileInputStream file = new FileInputStream(new File(inventoryPath))) {
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
                    extractRowVals(attributes, cell);
                }
                // create a new Racquet object from attributes
                racquetList.add(createRacquet(attributes));
            }

        } catch (FileNotFoundException err) {
            System.out.println("racquet_inventory file does not exist!");
            err.printStackTrace();
        } catch (IOException err) {
            System.out.println("could not read the file!");
            err.printStackTrace();
        } catch (InputMismatchException err) {
            System.out.println("inventory file may only contain text or numbers!");
            err.printStackTrace();
        }
        // no need for finally: resource auto-closed by try-with-resources

    }

    // returns a Racquet object from a list of attributes
    private static Racquet createRacquet(List<String> attributes) {
        return new Racquet(
                Integer.parseInt(attributes.get(0)),  // id
                attributes.get(1),  // brand
                attributes.get(2),  // model
                Integer.parseInt(attributes.get(3)),  // weight
                Integer.parseInt(attributes.get(4)),  // balance
                Integer.parseInt(attributes.get(5)),  // stiffness
                Integer.parseInt(attributes.get(6)),  // style
                Integer.parseInt(attributes.get(7)),  // skill
                Integer.parseInt(attributes.get(8))  // strength
        );
    }

    // populates a list of String cell values from the row of excel data
    private static void extractRowVals(List<String> attributes, Cell cell)
            throws InputMismatchException {

        // check for cell type and extract data
        CellType cellType = cell.getCellType();

        // add cell value to list of attributes (as Strings)
        if (cellType == CellType.STRING)
            attributes.add(cell.getStringCellValue());
        else if (cellType == CellType.NUMERIC)
            attributes.add(String.valueOf((int) cell.getNumericCellValue()));
        else throw new InputMismatchException();
    }

    // FOR DEBUGGING ONLY
    // returns the column header value of a given cell within a given worksheet
    private static String getColumnHeader(XSSFSheet sheet, Cell cell) {
        // get the correct cell within header row
        Cell headerCell = sheet.getRow(0).getCell(cell.getColumnIndex());

        // return value of the headerCell
        return headerCell.getRichStringCellValue().toString();
    }

}
