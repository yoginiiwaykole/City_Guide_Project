public class RestaurantImpl implements Restaurant {
    private String restaurantName;
    private String location;
    private String cuisine;
    private String region;

    public RestaurantImpl(String restaurantName, String location, String cuisine, String region) {
        this.restaurantName = restaurantName;
        this.location = location;
        this.cuisine = cuisine;
        this.region = region;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getLocation() {
        return location;
    }

    public String getCuisine() {
        return cuisine;
    }

    public String getRegion(){
        return region;
    }
}
