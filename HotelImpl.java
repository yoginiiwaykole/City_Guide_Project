public class HotelImpl implements Hotel {
    private String hotelName;
    private String location;
    private int rating;
    private String region;

    public HotelImpl(String hotelName, String location, int rating, String region) {
        this.hotelName = hotelName;
        this.location = location;
        this.rating = rating;
        this.region = region;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getLocation() {
        return location;
    }

    public int getRating() {
        return rating;
    }

    public String getRegion(){
        return region;
    }
}
