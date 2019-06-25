package rest_assured.rest_assured;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Library_File.ReusableMethods;
import Library_File.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AddBookTest {
	
	Properties prp;
	@BeforeClass
	public void commonService() throws IOException
	{
		prp = ReusableMethods.getPropertyFile();
	}
	@Test
	public void AddBook()
	{
		HashMap<String,Object> testData = (HashMap<String, Object>) ReusableMethods.getInputValuesMap("D:\\DemoRestAssured\\rest-assured\\Resource\\test.json", "TestData");
		System.out.println(testData.get("name"));
		RestAssured.baseURI=prp.getProperty("HOST2");

		Response resp=given().

		header("Content-Type","application/json").

		body(testData).

		when().

		
		post("/Library/Addbook.php").

		then().assertThat().statusCode(200).

		extract().response();

		JsonPath js= ReusableMethods.Raw_to_Json(resp);

		   String id=js.get("ID");

		   System.out.println(id);
	}

}
