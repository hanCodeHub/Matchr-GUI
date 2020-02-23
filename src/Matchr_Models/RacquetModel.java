package Matchr_Models;

import Matchr_App.Racquet;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// For saving and retrieving Racquet data in a SQL database
public class RacquetModel {

    private int rowCount;

    // CONSTANTS
    // database file in local static folder
    private static final Path JDBC_PATH = Paths.get("static/inventory.db");

    // racquets table and columns names
    public static final String RACQUETS_TABLE = "racquets";

    // SQL string for table definition
    public static final String RACQUETS_SCHEMA = "(" +
            "_id INT PRIMARY KEY NOT NULL, brand TEXT NOT NULL, model TEXT NOT NULL, " +
            "weight INT, balance INT, stiffness INT, " +
            "style INT, skill INT, strength INT, " +
            "shaftDiameter REAL, UNIQUE(_id))";

    // SQL string for use in CRUD statements
    public static final String RACQUETS_COLUMNS = "(_id, brand, model, " +
            "weight, balance, stiffness, style, skill, strength, shaftDiameter)";

    // string template for inserting one Racquet object in a prepared statement
    public static final String INSERT_RACQUET = "INSERT OR IGNORE INTO " +
            RACQUETS_TABLE + RACQUETS_COLUMNS + "VALUES (?,?,?,?,?,?,?,?,?,?)";

    // SQL string for getting all racquet objects
    public static final String GET_RACQUETS =
            "SELECT * FROM " + RACQUETS_TABLE + " ORDER BY brand";

    // SQL string for getting a count of total rows
    public static final String GET_COUNT =
            "SELECT COUNT(1) AS count FROM " + RACQUETS_TABLE;


    // racquets table should be created upon new instance
    public RacquetModel() {
        createRacquetTable(); // only if it does not exist yet
    }

    // creates a racquets table in database
    public void createRacquetTable() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + JDBC_PATH);
             Statement statement = conn.createStatement()) {

            statement.execute("CREATE TABLE IF NOT EXISTS " + RACQUETS_TABLE +
                    RACQUETS_SCHEMA);

        } catch (SQLException e) {
            System.out.println("Issue creating the racquets table: " + e.getMessage());
        }
    }


    // inserts a list of racquets into the database
    public void insertRacquets (List<Racquet> racquets) {

        // autoclose connection and statement
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + JDBC_PATH);
            Statement statement = conn.createStatement();
            PreparedStatement prepStatement = conn.prepareStatement(INSERT_RACQUET)) {

            // for each racquet set each racquet property in the prepared INSERT statement
            for (Racquet racquet : racquets) {
                prepStatement.setInt(1, racquet.getId());
                prepStatement.setString(2, racquet.getBrand());
                prepStatement.setString(3, racquet.getModel());
                prepStatement.setInt(4, racquet.getWeight());
                prepStatement.setInt(5, racquet.getBalance());
                prepStatement.setInt(6, racquet.getStiffness());
                prepStatement.setInt(7, racquet.getStyle());
                prepStatement.setInt(8, racquet.getSkill());
                prepStatement.setInt(9, racquet.getStrength());
                prepStatement.setFloat(10, racquet.getShaftDiameter());

                prepStatement.executeUpdate();
            }
            System.out.println("Racquet inventory successfully updated in the database.\n");

            // total row count is updated
            int count = statement.executeQuery(GET_COUNT).getInt("count");
            setRowCount(count);

        } catch (SQLException e) {
            System.out.println("Issue inserting racquets into database: " + e.getMessage());
        }
    }


    // returns a list of all Racquets from the database, ordered alphabetically by brand
    public List<Racquet> getAllRacquets() {
        ArrayList<Racquet> racquetList = null;

        // autoclose connection, statement, results
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + JDBC_PATH);
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(GET_RACQUETS)
        ) {

            // sets container list size according to number of rows
            racquetList = new ArrayList<>(getRowCount());

            // creates racquet from each row and adds to list
            while (results.next()) {
                Racquet racquet = new Racquet(
                        results.getInt("_id"),
                        results.getString("brand"),
                        results.getString("model"),
                        results.getInt("weight"),
                        results.getInt("balance"),
                        results.getInt("stiffness"),
                        results.getInt("style"),
                        results.getInt("skill"),
                        results.getInt("strength"),
                        results.getFloat("shaftDiameter")
                );
                racquetList.add(racquet);
            }

        } catch (SQLException e) {
            System.out.println("Issue getting racquets from database: " + e.getMessage());
        }
        return racquetList;
    }

    // getter/setter

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
}
