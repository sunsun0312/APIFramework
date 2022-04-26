package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name, String language, String address) {
		AddPlace p = new AddPlace();
    	p.setAccuracy(50);
    	p.setName(name);
    	p.setPhone_number("(+91) 983 893 3937");
    	p.setAddress(address);
    	p.setWebsite("http://google.com");
    	p.setLanguage(language);
    	Location l = new Location();
    	l.setLat(-38.383494);
    	l.setLng(33.427362);
    	p.setLocation(l);
    	
    	List<String> t = new ArrayList<String>();
    	t.add("shoe park");
    	t.add("shop");
    	p.setTypes(t);
    	return p;
	}
	
	public String deletePlacePayload(String placeId) {
		return "{\n    \"place_id\":\""+placeId+"\"\n}";
	}
	
}
