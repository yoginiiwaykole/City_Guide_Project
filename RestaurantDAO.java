import java.sql.*;
import java.util.*;
public class RestaurantDAO {
    public List<Restaurant> searchByRegion(String region) {
        List<Restaurant> restaurants = new ArrayList<>(); //store the Restaurant objects that will be retrieved from the database.

        try {
            Connection connection = DatabaseConnection.getConnection();
            //The query contains a placeholder (?) for the value that will be inserted later using parameter binding.
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM restaurants WHERE region = ?");
            statement.setString(1, region);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String restaurantName = resultSet.getString("restaurant_name");
                String location = resultSet.getString("location");
                String cuisine = resultSet.getString("cuisine");

                Restaurant restaurant = new RestaurantImpl(restaurantName, location, cuisine, region);
                restaurants.add(restaurant);
                //creates a new object of a class that implements the Restaurant interface, and initializes its properties
                // with the values retrieved from the ResultSet object.
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurants;
    }
}
