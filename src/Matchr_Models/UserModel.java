package Matchr_Models;

import Matchr_App.User;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class UserModel {

    // CONSTANTS
    // database file in local static folder
    private static final Path JDBC_PATH = Paths.get("static/inventory.db");

    // racquets table and columns names
    public static final String USERS_TABLE = "users";

    // SQL string for table definition
    public static final String USERS_SCHEMA = "(" +
            "name TEXT NOT NULL, racquet_id INT, " + "UNIQUE(name)" +
            ")";

    // SQL string for use in SQL CRUD statements
    public static final String USERS_COLUMNS = "(" +
            "name, racquet_id" +
            ")";

    // string template for inserting one User object in a prepared statement
    public static final String INSERT_USER = "INSERT OR IGNORE INTO " + USERS_TABLE +
            USERS_COLUMNS + " VALUES (?,?)";

    // SQL string for getting a User's saved racquet brand|model from database
    public static final String GET_USER_RACQUET =
            "SELECT racquets.brand, racquets.model " +
            "FROM " + USERS_TABLE + " INNER JOIN " + RacquetModel.RACQUETS_TABLE +
            " ON users.racquet_id = racquets._id " +
            "WHERE users.name IS ?";


    // users table should be created upon new instance
    public UserModel() {
        createUserTable(); // only if it does not exist yet
    }

    // creates a users table in database
    private void createUserTable() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + JDBC_PATH);
        Statement statement = conn.createStatement()) {

            // creates the table if not exist yet
            statement.execute("CREATE TABLE IF NOT EXISTS " + USERS_TABLE +
                    USERS_SCHEMA);

        } catch (SQLException e) {
            System.out.println("Issue creating the users table: " + e.getMessage());
        }
    }


    // inserts a user into the users table
    public void insertUser(User user) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + JDBC_PATH);
             PreparedStatement prepStatement = conn.prepareStatement(INSERT_USER)) {

            // set user columns and update table
            prepStatement.setString(1, user.getName());
            prepStatement.setInt(2, user.getRacquet().getId());
            prepStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Issue inserting a user into users: " + e.getMessage());
        }
    }


    // returns a user's saved racquet from database - just the name
    public String getUserSavedRacquet(User user) {
        // racquet name value returned as text
        StringBuilder racquetName = new StringBuilder();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + JDBC_PATH);
            PreparedStatement prepStatement = conn.prepareStatement(GET_USER_RACQUET)) {

            // get racquet columns: brand | model based on user name
            prepStatement.setString(1, user.getName());
            ResultSet results = prepStatement.executeQuery();

            // only 1 result since user name is unique
            results.next();
            racquetName.append(results.getString("brand"));
            racquetName.append(" ");
            racquetName.append(results.getString("model"));

        } catch (SQLException e) {
            System.out.println("Issue retrieving user's saved racquet: " + e.getMessage());
        }
        return racquetName.toString();
    }

}
