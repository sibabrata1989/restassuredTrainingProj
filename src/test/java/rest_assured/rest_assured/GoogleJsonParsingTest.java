package rest_assured.rest_assured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.*;

import Library_File.Resources;
import Library_File.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class GoogleJsonParsingTest {
	Properties prp;
@BeforeClass
public void commonService() throws IOException
{
	 prp = new Properties();
	 FileInputStream fs = new FileInputStream("D:/DemoRestAssured/rest-assured/src/test/java/Library_File/file.properties");
	 prp.load(fs);
}

@Test
public void Get() {
		//BaseURL or Host
		RestAssured.baseURI = prp.getProperty("HOST1");
		Response res = given().
				param("location","-33.8670522,151.1957362").
				param("radius","500").
				param("key",prp.getProperty("KEY1")).
		when().
				get(Resources.postData()).

		then().assertThat().
				statusCode(200).and().contentType(ContentType.JSON).and().
				body("results[0].name", equalTo("Sydney")).and().
				body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
				header("server","scaffolding on HTTPServer2").
				
	//Extracting the json response and parsing all values....			
		extract().response();
		JsonPath jp = ReusableMethods.Raw_to_Json(res);
		
		int count = jp.getInt("results.size()");
		
		for(int i=0; i<count;i++)
		{
			System.out.println(jp.get("results["+i+"].name"));
		}
		

}

}
