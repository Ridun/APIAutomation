package codes;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*; //
import static org.hamcrest.Matchers.*;//equalTo verification in body is coming from this package
import static org.testng.Assert.assertEquals;

import org.testng.Assert;

import files.Payload;
import files.reusableMethods;

public class Basics {

	public static void main(String[] args) {
		//given - all input details
		//when - submit the API --HTTP method,Resource
		//Then - Validate the response
		
		
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				
		.body(Payload.addPlace())
		
		.when().post("/maps/api/place/add/json")
		
		.then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		System.out.println("Response is"+response);
		
		JsonPath js = new JsonPath(response); //for parsing json
		
		String placeId = js.get("place_id");
		
		System.out.println(placeId);
		
		
		//update place
		
		String newAddress = "70 winter walk, Africa";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}")
		.when().put("/maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo ("Address successfully updated"));
		
		//Get place
		
		String getplaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
		
		.when().get("/maps/api/place/get/json")
		
		//.then().assertThat().log().all().statusCode(200).body("address", equalTo ("70 winter walk, USA")); or below using jsonpath as rahul shetty used
		
		
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		//JsonPath js1 = new JsonPath(getplaceResponse); --instead of this using reusable method we changed
		
		JsonPath js1 = reusableMethods.rawtojson(getplaceResponse);
		
		String actualAddress = js1.get("address");
		
		System.out.println(actualAddress);
		
		Assert.assertEquals(actualAddress, newAddress);
		
		System.out.println("Passed..");
		
		
		
		
		
		
		
		
	}
	

}
