import java.sql.*;
import java.util.*;
public class TouristPlaceDAO {
    public List<TouristPlace> searchByRegion(String region) {
        List<TouristPlace> places = new ArrayList<>();

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM tourist_places WHERE region = ?");
            statement.setString(1, region);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String placeName = resultSet.getString("place_name");
                String location = resultSet.getString("location");
                String description = resultSet.getString("description");

                TouristPlace touristPlace = new TouristPlaceImpl(placeName, location, description, region);
                places.add(touristPlace);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return places;
    }
}
