package restAssured.Demo;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Google_GetCall {
	
	@Test
	public void GetCall()
	{
		RestAssured.baseURI = "https://maps.googleapis.com";
		
		given().
			param("location","-33.8670522,151.1957362").
			param("radius","500").
			param("key","AIzaSyC_j86ZwLTq_Er1DURDZkyayZkfCVz3lVY").
		
		when().
			get("maps/api/place/nearbysearch/json").
		
		then().assertThat().
			statusCode(200).and().contentType(ContentType.JSON).and().
			body("results[0].name",equalTo("Sydney")).and().
			body("results[0].scope",equalTo("GOOGLE")).log().all();
					
	}
	

}
