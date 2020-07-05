package codes;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.path.json.JsonPath;

//to verify sum of all cources
public class SumValidation {

	@Test

	public void sumOfCource() {
		
		int sum=0;

		JsonPath js = new JsonPath(Payload.coursePrice()); // to get the datas from response

		int count = js.getInt("courses.size");

		for (int i = 0; i < count; i++) {

			int Prices = js.getInt("courses[" + i + "].price");

			int Copies = js.getInt("courses[" + i + "].copies");

			int amount = Prices * Copies;

			System.out.println(amount);
			
			sum=sum+amount;

		}

		System.out.println(sum);
		
		//validate the sum got is equal to purchased price(assert)
		
		int purchasedAmount = js.getInt("dashboard.purchaseAmount");
		
		Assert.assertEquals(purchasedAmount, sum);
	}

}
