Feature: Validating Place API's
@AddPlace
Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
	Given Add Place payload with "<name>" "<language>" "<address>" 
	When user calls "<resource>" with "<method>" Http request
	Then the API call is successful with status code is 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_Id created maps to "<name>" using "GetPlaceAPI"

Examples: 
	|name       | language | address           | resource    | method |
	|AAhouse    | English  | Worldcross center | AddPlaceAPI | POST   |
#	|BBhouse    | Spanish  | Seacross center   | GetPlaceAPI | Get    |
@DeletePlace
Scenario: Verify if Delete Place functionality is working
	Given DeletePlace Payload
	When user calls "DeletePlaceAPI" with "POST" Http request
	Then the API call is successful with status code is 200
	And "status" in response body is "OK"