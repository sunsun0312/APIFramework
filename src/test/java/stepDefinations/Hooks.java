package stepDefinations;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void beforeScenario() throws Throwable {
		StepDefination m = new StepDefination();
		if(StepDefination.placeId==null) {
			m.addplace_payload("Shetty", "French", "Asia");
			m.user_calls_with_post_http_request("AddPlaceAPI", "POST");
			m.verify_place_id_created_maps_to_using("Shetty", "GetPlaceAPI");
		}
		
	}
}
