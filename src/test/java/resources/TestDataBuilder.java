package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.List;

public class TestDataBuilder {
    public AddPlace addPlacePayload(String name, String language, String address) {
        AddPlace body = new AddPlace();
        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        body.setLocation(location);
        body.setAccuracy(50);
        body.setName(name);
        body.setPhone_number("(+91) 983 893 3937");
        body.setAddress(address);
        body.setTypes(List.of("shoe park", "shop"));
        body.setWebsite("http://google.com");
        body.setLanguage(language);
        return body;
    }

    public String deletePlacePayload(String placeID) {
        return "{\r\n    \"place_id\":\"" + placeID + "\"\r\n}";
    }
}
