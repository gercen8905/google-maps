package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import resources.APIResources;
import resources.TestDataBuilder;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinitions extends Utils {
    RequestSpecification request;
    ResponseSpecification res;
    Response response;
    TestDataBuilder data = new TestDataBuilder();
    JsonPath jsonPath;
    static String placeID;

    @Given("add Place payload with {string} {string} {string}")
    public void add_Place_payload_with(String name, String language, String address) throws IOException {
        request = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {
        res = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
        APIResources resourceAPI = APIResources.valueOf(resource);
        if (method.equalsIgnoreCase("post")) {
            response = request.when().post(resourceAPI.getResource());
        } else if (method.equalsIgnoreCase("get")) {
            response = request.when().get(resourceAPI.getResource());
        }

    }


    @Then("the API call is success with status code {int}")
    public void the_API_call_is_success_with_status_code(Integer int1) {
        assertEquals(200, response.getStatusCode());
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String string, String string2) {
        String stringResponse = response.asString();
        jsonPath = new JsonPath(stringResponse);
        assertEquals(string2, jsonPath.getString(string));
    }

    @Then("verify place_ID created maps to {string} using {string}")
    public void verify_place_ID_created_maps_to_using(String expectedName, String resource) throws IOException {
        placeID = getJsonPath(response, "place_id");
        request = given().spec(requestSpecification()).queryParam("place_id", placeID);
        user_calls_with_http_request(resource, "get");
        String name = getJsonPath(response, "name");
        Assert.assertEquals(expectedName, name);

    }
    @Given("DeletePlace Payload")
    public void deleteplace_Payload() throws IOException {
        request = given().spec(requestSpecification()).body(data.deletePlacePayload(placeID));

    }
}
