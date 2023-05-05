import java.util.*;

public class Implementation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;

        // Create DAO objects for TouristPlace, Restaurant, and Hotel
        // which is a Data Access Object (DAO) used to interact with the data source (such as a database) for TouristPlace objects.
        TouristPlaceDAO touristPlaceDAO = new TouristPlaceDAO();
        RestaurantDAO restaurantDAO = new RestaurantDAO();
        HotelDAO hotelDAO = new HotelDAO();

        // Define the valid regions for searching data in the database
        List<String> validRegions = Arrays.asList("New Market", "MP Nagar", "Lalghati");

        while (continueLoop) {
            try {
                System.out.print("Enter region: ");
                String region = null;
                do {
                    region = scanner.nextLine();
                    if (region.isEmpty() || !validRegions.contains(region)) {
                        throw new RegionNotFoundException("Invalid region entered. Please enter a valid region.");
                    }
                } while (region.isEmpty());

                int category = 0;
                do {
                    System.out.println("Enter category (1 for Tourist Place, 2 for Restaurant, 3 for Hotel, 4 to stop): ");
                    try {
                        category = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character
                        if (category < 1 || category > 4) {
                            System.out.println("Invalid input. Please try again.");
                            category = 0;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please try again.");
                        scanner.nextLine(); // Consume invalid input
                    }
                } while (category == 0);

                switch (category) {
                    case 1:
                        // Retrieve tourist places data from the database based on region
                        List<TouristPlace> touristPlaces = touristPlaceDAO.searchByRegion(region);
                        // This line calls the searchByRegion() method of the touristPlaceDAO object to retrieve a list of TouristPlace
                        // objects from the data source based on the entered region, and stores it in a List of TouristPlace objects.
                        if (touristPlaces.isEmpty()) {
                            throw new RegionNotFoundException("No tourist places found in " + region);
                        }
                        System.out.println("Tourist Places in " + region + ":");
                        for (TouristPlace touristPlace : touristPlaces) {
                            System.out.println(touristPlace.getPlaceName() + "  ,Location: " + touristPlace.getLocation() + "  ,Description: "
                                    + touristPlace.getDescription());
                        }
                        break;
                    case 2:
                        // Retrieve restaurants data from the database based on region
                        List<Restaurant> restaurants = restaurantDAO.searchByRegion(region);
                        if (restaurants.isEmpty()) {
                            throw new RegionNotFoundException("No restaurants found in " + region);
                        }
                        System.out.println("Restaurants in " + region + ":");
                        for (Restaurant restaurant : restaurants) {
                            System.out.println(restaurant.getRestaurantName() + " ,Location: " + restaurant.getLocation() + "  ,Cuisine: "
                                    + restaurant.getCuisine());
                        }
                        break;
                    case 3:
                        // Retrieve hotels data from the database based on region
                        List<Hotel> hotels = hotelDAO.searchByRegion(region);
                        if (hotels.isEmpty()) {
                            throw new RegionNotFoundException("No hotels found in " + region);
                        }
                        System.out.println("Hotels in " + region + ":");
                        for (Hotel hotel : hotels) {
                            System.out.println(hotel.getHotelName() + " ,Location: " + hotel.getLocation() + " ,Rating: " + hotel.getRating());
                        }
                        break;
                    case 4:
                        continueLoop = false;
                        break;
                    default:
                        throw new RegionNotFoundException("Region not found for " + region + ". Available regions are: New Market, MP Nagar, Lalghati");
                }
            } catch (RegionNotFoundException e) {
                System.out.println(e.getMessage());
                System.out.println("Please try again.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }

        System.out.println("Thank you for using the travel guide. Goodbye!");
    }
}
