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

    public static void main(String[] args) {
        readFromExcel(inventoryPath);
    }

    // reads inventory data from excel file of given file path
    // PRECONDITION: table headers in input file should match class Racquet attributes
    // returns an ArrayList of Racquet objects
    public static void readFromExcel(String inventoryPath) {

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

                    // validate cell value, must be either text or numeric
                    if (!getCellValue(cell)) throw new InputMismatchException();

                    // add cell value to list of attributes (as Strings)
                    if (cell.getCellType() == CellType.STRING)
                        attributes.add(cell.getStringCellValue());
                    else if (cell.getCellType() == CellType.NUMERIC)
                        attributes.add(String.valueOf((int) cell.getNumericCellValue()));
                }

                System.out.println(attributes.toString());
            }

        } catch (FileNotFoundException err) {  // subclass exception 1st
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


    // checks whether cell type of a given cell is valid
    private static Boolean getCellValue(Cell cell) throws InputMismatchException {
        // check for cell type and extract data
        CellType cellType = cell.getCellType();

        // validate cell value, must be either text or numeric
        return (cellType == CellType.NUMERIC) || (cellType == CellType.STRING);
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
