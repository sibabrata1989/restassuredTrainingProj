package rest_assured.rest_assured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Library_File.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;



public class GooglePost_XmlTest {
	Properties prp;
	@BeforeClass
	public void commonService() throws IOException
	{
		prp = ReusableMethods.getPropertyFile();
	}
			
	@Test
	public void PostCallUsingXML() throws IOException
	{
	//Xml file input
	String postData = ReusableMethods.generateStringFromResources("D:\\DemoRestAssured\\Resources\\payload.xml");		
	
	RestAssured.baseURI= prp.getProperty("HOST2");
	
	Response res = given().
		queryParam("key",prp.getProperty("KEY2")).
		body(postData).
	when().
		post("/maps/api/place/add/xml").
	
	then().assertThat().
		statusCode(200).and().contentType(ContentType.XML).and().log().all().
	
	extract().response();
		/*String responseString = res.asString();
		System.out.println(responseString);
		XmlPath xp = new XmlPath(responseString);*/
	
		//Covert Raw response to XML format...
		XmlPath xp = ReusableMethods.Raw_to_Xml(res);
		
		String placeID = xp.get("response.place_id");
		System.out.println("Place ID is: "+placeID);
	}
}
