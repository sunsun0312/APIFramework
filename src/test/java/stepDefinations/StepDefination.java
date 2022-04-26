package stepDefinations;



import org.junit.runner.RunWith;
import pojo.*;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import io.cucumber.junit.Cucumber;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.cucumber.java.PendingException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@RunWith(Cucumber.class)
public class StepDefination extends Utils {
	
	RequestSpecification req;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	JsonPath js;
	static String placeId;
	
    @Given("Add Place payload with {string} {string} {string}")
    public void addplace_payload(String name, String language, String address) throws Throwable {
    	
    	
    	req = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));     
        resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        
    }
    
    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        req = given().spec(requestSpecification()).body(data.deletePlacePayload(placeId));
    }


    @When("user calls {string} with {string} Http request")
    public void user_calls_with_post_http_request(String resource, String method) throws Throwable {
    	APIResources resourceAPI = APIResources.valueOf(resource);
        
    	if(method.equalsIgnoreCase("POST"))
    		response = req.when().post(resourceAPI.getResource());
    	else if(method.equalsIgnoreCase("GET"))
    		response = req.when().get(resourceAPI.getResource());
    }

    @Then("the API call is successful with status code is {int}")
    public void the_api_call_is_successful_with_status_code_is(Integer int1) throws Throwable {
        assertEquals(response.statusCode(),200);
    }
    



    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) throws Throwable {
        

//    	System.out.println(key + " "+ value +" "+response.asString());
        assertEquals(getJsonPath(response, key), value);
    }
    
    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        placeId = getJsonPath(response, "place_id");
        req = given().spec(requestSpecification()).queryParam("place_id", placeId);
        
        user_calls_with_post_http_request(resource, "GET");
        assertEquals(getJsonPath(response, "name"), expectedName);
        
    }
}