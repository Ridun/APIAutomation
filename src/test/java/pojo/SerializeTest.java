package pojo;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
public class SerializeTest {

	public static void main(String[] args) {
		
		AddPlace p =new AddPlace(); //create obj of add place
		
		p.setAccuracy(50);
		p.setAddress("29,kuttippuram,Kerala");
		p.setLanguage("english");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		p.setName("Frontline house");
		//now create list of types-- p.setTypes(types);
		ArrayList<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		//now create for nested json Location 
		//p.setLocation(location) -->return type is location so create obj of location class and give value in its setter method.
		Location l =new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		//now call main json location in serialize test
		p.setLocation(l);
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").body(p).
		
		when().post("/maps/api/place/add/json").
		
		then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(response );

	}

}
