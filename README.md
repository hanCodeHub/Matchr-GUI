# Inventory and Recommendation System

### Description

**Purpose**

This is a program developed as a term project for MET CS 622 at Boston University. The goal is to create the logic for a content-based recommendation system, which will match the preferences of a user to the specifications of a recommendable item. Badminton racquet specifications are used as the data for demonstration.

**Method**

1. User preferences gathered via UI
2. User submits excel file to update inventory in database
3. Program calculates the similarity between user values and those on each racquet
4. Sort racquet data by greatest similarity
5. Output top recommendation(s) to the UI

**Tools/Dependencies**

* Java JDK 11
* Apache POI for reading Excel files
    * download jar files here: https://poi.apache.org/download.html
* JDBC API with SQLite3 for data persistence
    * SQLite3 included in Mac OS, for other OS users download here: https://www.sqlite.org/download.html
    * download JDBC driver here: https://bitbucket.org/xerial/sqlite-jdbc/downloads/
* JavaFX for basic UI (for JDK 11+)
    * download jar files here: https://gluonhq.com/products/javafx/
    * For JDK 11 users, JavaFX has to be configured: https://www.jetbrains.com/help/idea/javafx.html
* Java look and feel graphics library from oracle
    * https://www.oracle.com/technetwork/java/index-138612.html

### Running the program

This section will be updated as the project develops.

Copy the project into your directory, and run JavaFX_App from package Matchr_UI within the src directory. Make sure all the library files and dependencies are configured for the project. This includes the VM options when running the app. The following has to be specified:

`--module-path %PATH_TO_FX% --add-modules javafx.base,javafx.controls,javafx.fxml`

Where %PATH_TO_FX% should be replaced by the full path to the JavaFX SDK lib directory, for example:

`--module-path C:\Users\username\Documents\lib\javafx-sdk-11.0.2\lib --add-modules javafx.base,javafx.controls,javafx.fxml`

Tests are located in the tests folder within the main project directory.

### Using the program

Within the Matchr window, enter your preferred values into fields. All fields are optional, and the program will use the default values unless specified otherwise. If a brand preference is entered (and if it exists), the program will only match you with racquets of that brand. Otherwise, it will recommend the best match out of all brands. Click 'Recommend Racquet' once you're ready.

For uploading inventory data via excel, fill in the 'racquet_inventory.xlsx' template provided in the static folder. Do not add or delete columns. Then click 'Update Inventory' in the Matchr window.

If you want to start over, click Reset Preferences to clear all the fields.