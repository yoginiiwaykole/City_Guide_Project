import java.sql.*;

public class DatabaseHandler {
    private Connection conn;

    public DatabaseHandler(String url, String username, String password) {
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.out.println("Connection failed");
        }
    }

    public ResultSet executeQuery(String query) {
        ResultSet rs = null;
        try {
            // create a statement
            Statement stmt = conn.createStatement();

            // execute the query
            rs = stmt.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void closeConnection() {
        try {
            // close the connection
            conn.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

