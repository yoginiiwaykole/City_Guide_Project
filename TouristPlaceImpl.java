public class TouristPlaceImpl implements TouristPlace {
    private String placeName;
    private String location;
    private String description;
    private String region;

    public TouristPlaceImpl(String placeName, String location, String description, String region) {
        this.placeName = placeName;
        this.location = location;
        this.description = description;
        this.region = region;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }
    public String getRegion(){
        return region;
    }
}
