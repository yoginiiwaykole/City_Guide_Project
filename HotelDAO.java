import java.sql.*;
import java.util.*;
public class HotelDAO {
    public List<Hotel> searchByRegion(String region) {
        List<Hotel> hotels = new ArrayList<>();

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM hotels WHERE region = ?");
            statement.setString(1, region);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String hotelName = resultSet.getString("hotel_name");
                String location = resultSet.getString("location");
                int rating = resultSet.getInt("rating");

                Hotel hotel = new HotelImpl(hotelName, location, rating, region);
                hotels.add(hotel);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotels;
    }
}
