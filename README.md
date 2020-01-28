# Racquet Recommendation

### Program entirely written in Java (JDK 11)
_No frameworks or libraries were used_

### Description

**Purpose**

This is a program developed as a term project for MET CS 526 at Boston University. The goal is to create the logic for a content-based recommendation system, which will match the preferences of a user to the specifications of a badminton racquet.

**Method**

1. User preferences gathered via UI
2. Calculate the distance between user values and those on each racquet
3. Sort racquet data by lowest total distance, which indicates greatest similarity
4. Output top recommendations

**Tools**

* Java JDK 11
* txt/xls I/O to import racquet data
* JDBC API with SQL for storing data
* JavaFX for basic UI

### Running the program

For now copy the project into your directory, and run the main() method. This section will be updated once data persistence and UI are developed.