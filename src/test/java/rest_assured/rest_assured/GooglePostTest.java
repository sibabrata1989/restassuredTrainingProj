package rest_assured.rest_assured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.*;

import Library_File.ReusableMethods;
import Library_File.payload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
public class GooglePostTest {
	Properties prp;
	@BeforeClass
	public void commonService() throws IOException
	{
		prp = ReusableMethods.getPropertyFile();
	}
	@Test
	public void Post()
	{
		RestAssured.baseURI= "http://216.10.245.166";
		
		given().
			queryParam("key","qaclick123").
			body("{    \"location\":{        \"lat\" : -38.383494,        \"lng\" : 33.427362    },    \"accuracy\":50,    \"name\":\"Frontline house\",    \"phone_number\":\"(+91) 983 893 3937\",    \"address\" : \"29, side layout, cohen 09\",    \"types\": [\"shoe park\",\"shop\"],    \"website\" : \"http://google.com\",    \"language\" : \"French-IN\"}").
			
		when().
			post("/maps/api/place/add/json").
		
		then().assertThat().
			statusCode(200).and().contentType(ContentType.JSON).and().
			body("status",equalTo("OK")).log().all();
	}

}
