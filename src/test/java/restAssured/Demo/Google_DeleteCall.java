package restAssured.Demo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Library_File.ReusableMethods;
import Library_File.payload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Google_DeleteCall {
	Properties prp;
	@BeforeClass
	public void commonServices() throws IOException
	{
		prp = ReusableMethods.getPropertyFile();
	}
	
	
	@Test
	public void DeleteCall() throws IOException
	{
		String payloadData = ReusableMethods.generateStringFromResources("D:\\DemoRestAssured\\Resources\\payload.json");
		
		RestAssured.baseURI = prp.getProperty("HOST2");
		
		Response res = given().
			queryParam("key",prp.getProperty("KEY2")).
			body(payloadData).
		
		when().
			post("/maps/api/place/add/json").
			
		then().assertThat().
			statusCode(200).and().contentType(ContentType.JSON).and().
			body("status",equalTo("OK")).log().all().
		
		extract().response();
			
			JsonPath jp = ReusableMethods.Raw_to_Json(res);
			
			String place_id = jp.getString("place_id");
			System.out.println("Place id is : "+place_id);
			
			
		given().
			queryParam("key","qaclick123").
			body("{"+
			    "\"place_id\":\""+place_id+"\""+
			"}").
		
		when().
			post("/maps/api/place/delete/json").
			
		then().assertThat().
			statusCode(200).and().contentType(ContentType.JSON).and().
			body("status",equalTo("OK"));
		

			
		
		
			
			
	}

}
