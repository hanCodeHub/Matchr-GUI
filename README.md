# Inventory and Recommendation System

### Description

**Purpose**

This is a program developed as a term project for MET CS 622 at Boston University. The goal is to create the logic for a content-based recommendation system, which will match the preferences of a user to the specifications of a recommendable item. Data used is for badminton racquets.

**Method**

1. User preferences gathered via UI
2. User submits excel file to update inventory in database, receipt confirmation printed to txt
2. Program calculates the distance between user values and those on each racquet
3. Sort racquet data by lowest total distance, which indicates greatest similarity
4. Output top recommendation(s) to the UI

**Tools/Dependencies**

* Java JDK 11
* Apache POI for reading Excel files
    * download here: https://poi.apache.org/download.html
* JDBC API with SQL for storing data
* JavaFX for basic UI
    * download here: https://gluonhq.com/products/javafx/
    * For JDK 11 users, JavaFX has to be configured: https://www.jetbrains.com/help/idea/javafx.html
* Java look and feel graphics repo from oracle
    * https://www.oracle.com/technetwork/java/index-138612.html

### Running the program

This section will be updated as the project develops.

Copy the project into your directory, and run the JavaFX_App from package Matchr_UI within the src directory. Make sure all the library files and dependencies are configured for the project. This includes the VM options when running the app. The following has to be specified:

`--module-path %PATH_TO_FX% --add-modules javafx.base,javafx.controls,javafx.fxml`

Where %PATH_TO_FX% should be replaced by the full path to the JavaFX SDK lib directory, for example:

`--module-path C:\Users\username\Documents\lib\javafx-sdk-11.0.2\lib --add-modules javafx.base,javafx.controls,javafx.fxml`

For uploading inventory data via excel, fill in the 'racquet_inventory.xlsx' template provided in the static folder. Please do not add or delete columns.

Tests are located in the tests folder within the main project directory.