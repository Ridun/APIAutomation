package codes;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Payload;
import files.reusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	@Test(dataProvider="BooksData")
	
	public void addBook(String isbn,String aisle)
	{
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = given().log().all().header("Content-Type","application/json").
		
		body(Payload.addBook(isbn,aisle)).
		
		when().post("/Library/Addbook.php").
		
		then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js = reusableMethods.rawtojson(response);
		
		String id = js.get("ID");
		
		System.out.println(id);
		
		
	}
	
	@DataProvider(name="BooksData")
	
	public Object[][] getData() {
		
		//new object[][] {array1,array2,array3..};
		
		return new Object[][]   { {"ratyu,3456"},{"tayrf","4568"},{"jahgb","9876"} };
	}
		
}


