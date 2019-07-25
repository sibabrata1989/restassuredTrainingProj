package rest_assured.rest_assured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.*;

import Library_File.ConstantsVariable;
import Library_File.Resources;
import Library_File.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class GoogleGetTest implements ConstantsVariable {
Properties prp;

@BeforeClass
public void commonService() throws IOException
{
	prp = ReusableMethods.getPropertyFile();
}

@Test
public void Get() {
		//BaseURL or Host
		RestAssured.baseURI = prp.getProperty("HOST1");
		given().
				param(LOCATION,"-33.8670522,151.1957362").
				param(RADIUS,"500").
				param(KEY,prp.getProperty("KEY1")).
		when().
				get("/maps/api/place/nearbysearch/json").

		then().assertThat().
				statusCode(STATUS_OK).and().contentType(ContentType.JSON).and().
				body("results[0].name", equalTo("Sydney")).and().
				body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
				header(SERVER,"scaffolding on HTTPServer2").log().all();

}

}
