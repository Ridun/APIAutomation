package codes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;

import io.restassured.RestAssured;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.Getcourse;
import pojo.WebAutomation;

import static io.restassured.RestAssured.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
public class OAuthTest {
	public WebDriver driver;
	


	public static  void browserInitialization() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "D:\\API Testing-Rakesh Shetty\\BrowserDrivers\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		options.addArguments("disable-infobars");
		//options.addArguments("incognito");
		options.addArguments("--allow-running-insecure-content");
		WebDriver driver = new ChromeDriver();			
		driver.manage().window().maximize(); 
		
		driver.get("https://accounts.google.com/signin/oauth/oauthchooseaccount?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&o2v=2&as=1WDzZ4cGeKDttwKgNfvyrQ&flowName=GeneralOAuthFlow");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("jiraridun");
		
		Thread.sleep(2000); 
	
		driver.findElement(By.xpath("(//span[@class='CwaK9'])[1]")).click();
		
		Thread.sleep(4000);
		
		//driver.navigate().refresh();
		
		//driver.quit();
	}
	@Test
	@JsonIgnoreProperties(ignoreUnknown = true)
	public void oauthAutomation() throws InterruptedException {
		
	//browserInitialization();
		String courseTilte [] = {"selenium webdriver java","cypress","protractor"};
		
	String accessTokenResponse = given().urlEncodingEnabled(false).
	queryParams("code", "4%2F1gHEf7jIeiTar2x2zADCSlS5hWhqQTHRlyO6QsUto_f66jSzJbaSUsBY92g6CYRx-pz0XOSzpi8iKyN5SBdXzww").
	queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
	queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W").
	queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php").
	queryParams("grant_type", "authorization_code").
	
	when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
	
	JsonPath js = new JsonPath(accessTokenResponse);
	String access_Token = js.get("access_token");
	
	//write this first step with out having access token value
	/*
	String response = given().queryParam("access_token", access_Token).
	
	when().get("https://rahulshettyacademy.com/getCourse.php").asString();//instead of assertthat.status
	
	//System.out.println(response);
	
	*/
	 
	//for pojo class
	// we have to tell explicitly which is the format(json/xml) while using pojo class
	// we can avoid this if response header "content-type" ="application.json"
			Getcourse gc = given().queryParam("access_token", access_Token).expect().defaultParser(Parser.JSON).
			
			when().get("https://rahulshettyacademy.com/getCourse.php").as(Getcourse.class);
			//System.out.println(response);
			
			//System.out.println(gc.getLinkedin());
			
			//System.out.println(gc.getInstructor());
			
			System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
			
			//Get course name of webAutomation
			ArrayList<String>a = new ArrayList<String>();
			List<WebAutomation> cn = gc.getCourses().getWebAutomation();
			
			for(int j=0;j<cn.size();j++) {
				a.add(cn.get(j).getCourseTitle()); //so adding course title in this array list
			}
			
			//convert created array beginning to arraylist for cmparing
			
			List<String> expectedList = Arrays.asList(courseTilte);
			
			Assert.assertTrue(a.equals(expectedList));
	} 
}


	
	