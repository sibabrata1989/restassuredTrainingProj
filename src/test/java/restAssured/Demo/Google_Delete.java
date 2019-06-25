package restAssured.Demo;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Google_Delete {
	
	@Test
	public void delete_Call()
	{
		RestAssured.baseURI = "http://216.10.245.166";
		
		given()
		.queryParam("key","qaclick123").
		body("{"+
			    "\"place_id\":\"78f2141fc63d8ae0d83ba4e0a32bf633\""+
			"}").
		
		when().
		post("/maps/api/place/delete/json").
		
		then().assertThat().
		statusCode(200).and().contentType(ContentType.JSON).and().
		body("status",equalTo("OK")).log().all();
		

	}

}
