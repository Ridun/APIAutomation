package codes;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;


import files.Payload;
import files.reusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class StaticJson {
	
@Test
	
	public void addBook() throws IOException
	{
		RestAssured.baseURI = "http://216.10.245.166";
		
		
		String response = given().log().all().header("Content-Type","application/json").
		
		body(generateStringfromResource("D://after search docs//DMT//Documents//personal//API Testing-Rakesh Shetty//addbook.json")).
		
		when().post("/Library/Addbook.php").
		
		then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js = reusableMethods.rawtojson(response);
		
		String id = js.get("ID");
		
		System.out.println(id);
		
		
	}

public static String generateStringfromResource(String path) throws IOException{
	
	return new String(Files.readAllBytes(Paths.get(path)));
	
}

}
