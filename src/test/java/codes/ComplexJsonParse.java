package codes;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {

		JsonPath js = new JsonPath(Payload.coursePrice()); // so we mock the response with out request that later we can
															// add after dev gave

		// Print No:of courses returned by API

		int count = js.getInt("courses.size"); // .size has to written manually.

		System.out.println(count);

		// print purchase amount

		int totalAmount = js.getInt("dashboard.purchaseAmount");

		System.out.println(totalAmount);

		// Title of first course

		String TiltleFirstCourse = js.getString("courses[0].title"); // can use get also instead of getString(default
																		// get is String)

		System.out.println(TiltleFirstCourse);

		// Print all course titles and its price

		for (int i = 0; i < count; i++) {
			
			System.out.println(js.get("courses[" + i + "].title"));

			System.out.println(js.getInt("courses[" + i + "].price"));
		}

		// print no:of copies sold by RPA

		System.out.println("no:of copies sold by RPA...");

		for (int i = 0; i < count; i++) {
			
			String CourseTitles = js.get("courses[" + i + "].title");

			if (CourseTitles.equalsIgnoreCase("RPA")) {
				
				int copiesCount = js.getInt("courses[" + i + "].copies");
				
				System.out.println(copiesCount);
				
				break;   //this will break the loop if the required result got,we can use this for optimization.
			}
		}
	}

}
