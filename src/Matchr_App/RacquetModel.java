package Matchr_App;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;

// For saving and retrieving Racquet data in a SQL database
public class RacquetModel {

    // CONSTANTS
    // database file in local static folder
    private static final Path JDBC_PATH = Paths.get("static/inventory.db");

    // racquets table and columns names
    private static final String RACQUETS_TABLE = "racquets";

    // schema definitions with and without type for use in SQL statements
    public static final String RACQUETS_SCHEMA_TYPES = "(_id INTEGER, brand TEXT, model TEXT, " +
            "weight INTEGER, balance INTEGER, stiffness INTEGER, " +
            "style INTEGER, skill INTEGER, strength INTEGER, " +
            "shaftDiameter REAL)";

    public static final String RACQUETS_SCHEMA_NOTYPES = "(_id, brand, model, " +
            "weight, balance, stiffness, style, skill, strength, shaftDiameter)";

    // template for prepared statement for inserting one Racquet object
    public static final String INSERT_RACQUET = "INSERT INTO " + RACQUETS_TABLE +
            RACQUETS_SCHEMA_NOTYPES + "VALUES (?,?,?,?,?,?,?,?,?,?)";


    // racquets table should be created upon new instance
    public RacquetModel() {
        createRacquetTable();
    }

    // creates a table with given name
    private void createRacquetTable() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + JDBC_PATH)) {
            Statement statement = conn.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS " + RACQUETS_TABLE +
                    RACQUETS_SCHEMA_TYPES
            );
            statement.close();

        } catch (SQLException e) {
            System.out.println("Issue creating the racquets table: " + e.getMessage());
        }
    }


    // inserts a list of racquets into the database
    public void insertRacquets (List<Racquet> racquets) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + JDBC_PATH)) {

            PreparedStatement prepStatement = null;

            for (Racquet racquet : racquets) {

                // set each racquet property in the prepared statement constant
                prepStatement = conn.prepareStatement(INSERT_RACQUET);
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

            if (prepStatement != null) prepStatement.close();
        } catch (SQLException e) {
            System.out.println("Issue inserting racquets into table: " + e.getMessage());
        }
    }

    public List<Racquet> getAllRacquets() {
        return null;
    }
}
