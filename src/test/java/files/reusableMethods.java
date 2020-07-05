package files;

import io.restassured.path.json.JsonPath;


public class reusableMethods {

	public static   JsonPath rawtojson(String resp) {     //make static to call by class.method name in other class
		
		JsonPath js1 = new JsonPath(resp);
		
	    return js1;
		
	    
	}
}
