import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    //static means it belongs to the class level, and private means it can only be accessed within the class.
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) { // Check if connection is null or closed
            String url = "jdbc:mysql://localhost:3306/city_guide";
            String user = "root";
            String password = "yoginii@1008";
            connection = DriverManager.getConnection(url, user, password);
            // returns a Connection object representing the connection to the database
        }
        return connection;
    }
}