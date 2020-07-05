
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;



public class Search {
	@Test
	public void searchTest()
	{
	/*	
	System.setProperty("webdriver.chrome.driver", "D:\\Eclipsedirectory\\AutomationPedia\\RESTAssuredRidunAutomationPedia\\src\\test\\java\\browserDrivers\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	driver.get("https://www.google.com/");
	driver.findElement(By.xpath("//input[@class='gLFyf gsfi']")).sendKeys("images");
	
	ArrayList<String>a = new ArrayList();
	a.add("pan"); //adding array with out index 
	a.add(1, "pan4"); //a.add(index, element);with index
	a.remove(1);      //a.remove(index)
	System.out.println(a);
	*/
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.alamy.com");
		driver.close();
	
	

}
}
