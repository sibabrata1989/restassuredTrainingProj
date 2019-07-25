package rest_assured.rest_assured;
import static org.hamcrest.Matchers.equalTo;
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
import java.util.Properties;

public class Twitter_APIs_Test implements ConstantsVariable
{
	
	String ConsumerKey="ok91GGBYji9qywCSKlhtfdLmG";
	String ConsumerSecret="9wEV8fh9sZgdOJQDzIu60uhKuAj7QNKBiOWOvZ7b4nwIsjuEni";
	String Token="231500768-8AQNPs1MENtlLc16AxVDRzdgyCqYhSvdXbHeZcoq";
	String TokenSecret="3d0CBtK2P7i3nPDGjwfoU89yKCIP29kXli6qSIaBVEEZh";
	String id;
	Properties prp;
	
	@BeforeClass
	public void commonService() throws IOException
	{
		prp = ReusableMethods.getPropertyFile();
	}
	
	@Test
	public void getLatestTweet()
	{
		
		RestAssured.baseURI= "https://api.twitter.com/1.1/statuses";
		Response res= given().
							  auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret).
							  queryParam("count", "1").
						 
					  when().    
				              get("/home_timeline.json").
				              
				  
				      then().extract().response();
	
							JsonPath js= ReusableMethods.Raw_to_Json(res);
								
							
							System.out.println(js.get("text"));
							System.out.println(js.get("id"));
							
		
	
	}
	
	@Test
	public void createTweet()
	{
		
		RestAssured.baseURI=prp.getProperty("TWITTER_HOST");;
		Response res= given().
				              auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret).
							  queryParam("status", "I am creating this tweet using Automation date-7/14").
									
					   when().
					          post("/update.json").
					          
					   then().extract().response();
	
							JsonPath js= ReusableMethods.Raw_to_Json(res);
							System.out.println("Below is the tweet added");
							//System.out.println(js.get("text"));
							System.out.println(js.get("id"));
							id=js.get("id").toString();
	
	
	}
	
	@Test
	public void E2E()
	{
		createTweet();
		
		RestAssured.baseURI=prp.getProperty("TWITTER_HOST");;
		Response res= given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret).
				
					   when().
							  post("/destroy/"+id+".json").
							  
					   then().extract().response();
	
							JsonPath js= ReusableMethods.Raw_to_Json(res);
							//System.out.println(js.get("text"));
							System.out.println("Tweet which got deleted with automation is below");
							System.out.println(js.get("text"));
							System.out.println(js.get("truncated"));
								
	}
}
