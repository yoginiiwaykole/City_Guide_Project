# City_Guide_Project


City Guide is a Java application that helps users to explore different tourist places, restaurants, and hotels in various regions. This application uses MySQL database to store and retrieve the data. 

### Requirements

* Java 
* MySQL 
* MySQL JDBC driver 

### Getting Started

1. Clone this repository to your local machine.
2. Import the project into your Java IDE.
3. Open the `DatabaseConnection` class and change the following lines to match your MySQL database configuration:
   ```
   String url = "jdbc:mysql://localhost:3306/city_guide";
   String user = "root";
   String password = "yoginii@1008";
   ```
4. Run the `Implementation` class to start the application.

### Usage

When the application is started, the user will be prompted to enter the region and category of the place they want to explore. 

The application supports three categories:
* Tourist Place
* Restaurant
* Hotel

The user can also choose to stop the application by entering 4. 

Once the user enters the region and category, the application retrieves the data from the MySQL database and displays it to the user. If no data is found for the given region and category, the application throws a `RegionNotFoundException`. 
