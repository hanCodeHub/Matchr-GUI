# Inventory and Recommendation System

### Description

**Purpose**

This is a program developed as a term project for MET CS 526 at Boston University. The goal is to create the logic for a content-based recommendation system, which will match the preferences of a user to the specifications of a badminton racquet.

**Method**

1. User preferences gathered via UI
2. Calculate the distance between user values and those on each racquet
3. Sort racquet data by lowest total distance, which indicates greatest similarity
4. Output top recommendations

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

For now copy the project into your directory, and run the main() method from package com.han. This section will be updated once data persistence and UI are developed.

For uploading inventory data via excel, follow the template provided in the static folder. Do not change the contents/order of the headers.

Tests are located in the tests folder within the main project directory, and will be continuously updated as the project develops.