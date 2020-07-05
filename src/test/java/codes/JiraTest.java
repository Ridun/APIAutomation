package codes;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;
//JIRA API automation
public class JiraTest {

	public static void main(String[] args) {

		RestAssured.baseURI = "http://localhost:8080";

		// Login Scenario
		SessionFilter session = new SessionFilter();

		String resp = given().header("Content-Type", "application/json")
				.body("{ \"username\": \"ridun\", \"password\": \"Anoopanoop1!\" }").log().all().filter(session).when()
				.post("/rest/auth/1/session").then().log().all().extract().response().asString();

		// we can direcyly give ID or pass it as path parameter
		// Add comment -POST /rest/api/3/issue/{issueIdOrKey}/comment
		String expMsg = "Hi how are you";
		String addcommentResponse = given().pathParam("key", "10103").log().all()
				.header("Content-Type", "application/json")
				.body("{\r\n" + "  \"body\": \""+expMsg+"\",\r\n" + "  \"visibility\": {\r\n" //exp msg added here after line 66
						+ "    \"type\": \"role\",\r\n" + "    \"value\": \"Administrators\"\r\n" + "  }\r\n" + "}")
				.filter(session).
				when().post("/rest/api/2/issue/{key}/comment").
				then().log().all().assertThat().statusCode(201).extract().response().asString();

		JsonPath js = new JsonPath(addcommentResponse);
		// to get the comment id and to compare when validating with get api
		String commentId = js.getString("id");

		// Add attachment
		// curl -D- -u admin:admin -X POST -H "X-Atlassian-Token: no-check" -F
		// "file=@myfile.txt"
		// https://your-domain.atlassian.net/rest/api/3/issue/TEST-123/attachments
		given().header("X-Atlassian-Token", "no-check").filter(session).pathParam("key", "10103")

				.log().all().header("Content-Type", "multipart/form-data").multiPart("file", new File("jira.txt"))
				.when().post("/rest/api/2/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);

		// Get issue -- as GET ,no need to give header value

		String issueDetails = given().filter(session).pathParam("key", "10103").queryParam("fields", "comment").log()
				.all().when().get("/rest/api/2/issue/{key}").then().log().all().extract().response().asString();
		System.out.println(issueDetails);

		JsonPath js1 = new JsonPath(issueDetails);

		int commentsCount = js1.getInt("fields.comment.comments.size()");

		for (int i = 0; i < commentsCount; i++) {
			System.out.println(js1.getInt("fields.comment.comments[" + i + "].id"));// to match with this also take id
																					// from add comment

			String commentidIssue = js1.get("fields.comment.comments[" + i + "].id").toString();

			if (commentidIssue.equalsIgnoreCase(commentId)) {
				
				String message = js1.get("fields.comment.comments[" + i + "].body").toString();
				
				System.out.println(message);
				
				//now compare expected msg with this msg

				Assert.assertEquals(expMsg, message);
				
				System.out.println("passed....");
			}

		}

	}

}
