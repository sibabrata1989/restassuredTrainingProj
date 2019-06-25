package rest_assured.rest_assured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Library_File.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class GooglePost_JsonTest implements ConstantsVariable {
	Properties prp;
	@BeforeClass
	public void commonService() throws IOException
	{
		prp = ReusableMethods.getPropertyFile();
	}
			
	@Test
	public void googlePostWithJsonFile() throws IOException
	{
	//Json file input
	String postData = ReusableMethods.generateStringFromResources("D:\\DemoRestAssured\\Resources\\payload.json");

	RestAssured.baseURI= prp.getProperty("HOST2");
	
	Response res = given().
		queryParam("key",prp.getProperty("KEY2")).
		body(postData).
		
	when().
		post("/maps/api/place/add/json").
	
	then().assertThat().
		statusCode(STATUS_OK).and().contentType(ContentType.JSON).and().
		body("status",equalTo("OK")).log().all().
	
	extract().response();
	

	//Covert Raw response to JSON format...
	JsonPath jp = ReusableMethods.Raw_to_Json(res);
	
	String placeID = jp.get("place_id");
	System.out.println("Place ID is: "+placeID);
	}
}
