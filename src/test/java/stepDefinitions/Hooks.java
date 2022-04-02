package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeDeleteScenario() throws IOException {
        if (StepDefinitions.placeID == null) {

        StepDefinitions stepDefinitions = new StepDefinitions();
            stepDefinitions.add_Place_payload_with("Rahul", "French", "India");
            stepDefinitions.user_calls_with_http_request("ADD_PLACE_API", "post");
            stepDefinitions.verify_place_ID_created_maps_to_using("Rahul", "GET_PLACE_API");
        }
    }
}
