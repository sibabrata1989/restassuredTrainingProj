package restAssured.Demo;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import Library_File.ConstantsVariable;
import org.testng.annotations.Test;

import Library_File.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class demo {
	String tokenId;
	
	@Test
	public void GetToken()
	{
		RestAssured.baseURI = "http://ojas-pc:7800/";
		
		Response rsp = given().
		
			headers("Content-Type","application/x-www-form-urlencoded").
			queryParam("Username", "BF_WEBAPI").
			queryParam("password", "1234").
			queryParam("grant_type", "password").
		
		when().
			post("lgtoken").
		
		then().assertThat().
			statusCode(200).and().contentType(ContentType.JSON).and().
			body("token_type",equalTo("bearer")).log().all().
		extract().response();
		//coverting the Raw type response to Json type
		JsonPath js= Raw_to_Json(rsp);
		System.out.println("Below is the login token>>");
		System.out.println(js.get("access_token"));
		//Token is fetched from the response and stored to the variable tokenId.
		tokenId=js.get("access_token").toString();
					
	}
	
	@Test
	public void createApplication() throws IOException
	{
		RestAssured.baseURI = "http://ojas-pc:7800/";
		//Json file input(Add the complete body to a json file. eg: payload.json)
		String postData = generateStringFromResources("D:\\DemoRestAssured\\Resources\\payload.json");
		
		Response rsp = given().
		//Passing the token here in the below header.........
			headers("Authorization","Bearer "+tokenId+"").
			body(postData).
					
		when().
			post("/api/applications").
		
		then().assertThat().
			statusCode(200).and().contentType(ContentType.JSON).
			
		extract().response();
		
		JsonPath js= Raw_to_Json(rsp);
		//Validation to check the correct response.
		Assert.assertEquals(js.get("ProductName"), "Loanguard Bank WEBAPI");
		Assert.assertEquals(js.get("ApplicationNo"), "Test1");
		
		
	}
	//Method used to convert Xml/Json to String
	public String generateStringFromResources(String path) throws IOException
	{
		return new String (Files.readAllBytes(Paths.get(path)));
	}
	//Method to convert Raw response to a JSON format
	public static JsonPath Raw_to_Json(Response res)
	{
		String responseString = res.asString();
		JsonPath json = new JsonPath(responseString);
		return json;
	}
	

}
