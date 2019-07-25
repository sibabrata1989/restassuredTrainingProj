package rest_assured.rest_assured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import Library_File.payload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GoogleDeleteTest {

	@Test
	public void DeletePlace()
	{
		RestAssured.baseURI= "http://216.10.245.166";
		
		Response res = given().
			queryParam("key","qaclick123").
			body(payload.postData()).
		when().
			post("/maps/api/place/add/json").
		
		then().assertThat().
			statusCode(200).and().contentType(ContentType.JSON).and().
			body("status",equalTo("OK")).
		
		extract().response();
			String responseString = res.asString();
			System.out.println(responseString);
			
			JsonPath js = new JsonPath(responseString);
			String placeID = js.get("place_id");
			System.out.println("The scope value is "+js.get("scope"));
			System.out.println("Place ID is: "+placeID);
		
		
		//Delete Method continues....
		
		given().
			queryParam("key","qaclick123").
			body("{"+
					"\"place_id\":\""+placeID+"\""+
					"}").
		when().
			post("/maps/api/place/delete/json").
		
		then().assertThat().
			statusCode(200).and().contentType(ContentType.JSON).and().
			body("status",equalTo("OK"));
			
				
		
	}
}
