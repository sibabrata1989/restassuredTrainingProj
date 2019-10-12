package rest_assured.rest_assured;
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
import java.util.Properties;
import java.util.Random;

public class Twitter_APIs_Test implements ConstantsVariable
{
	
	String ConsumerKey="QTkOtUHGmJ5rOKh4Qn2OMEfzg";
	String ConsumerSecret="gxCuOhnqE2g9LSegDgFSIYWqcYpptFhDz3OgqRWn46ATQ9Qbmf";
	String Token="231500768-wf3nRAXTDu7xzZeg9FGACbJhJvmSVmKxLAAY69JZ";
	String TokenSecret="kYKkK7vk2yrQ2wb9wfF7PonGAg1XGSwEsGRKqnU9q32k8";
	String id;
	Properties prp;
	
	@BeforeClass
	public void commonService() throws IOException
	{
		prp = ReusableMethods.getPropertyFile();
	}
	
	@Test(priority = 1)
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
	
	@Test(priority = 2)
	public void createTweet()
	{
		Random rnd = new Random();
		
		RestAssured.baseURI=prp.getProperty("TWITTER_HOST");;
		Response res= given().
				              auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret).
							  queryParam("status", "I am creating this tweet using Automation-"+rnd.nextInt(100)).
									
					   when().
					          post("/update.json").
					          
					   then().extract().response();
	
							JsonPath js= ReusableMethods.Raw_to_Json(res);
							System.out.println("Below is the tweet added");
							//System.out.println(js.get("text"));
							System.out.println(js.get("id"));
							id=js.get("id").toString();
	
	
	}
	
	@Test(priority = 3)
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
							Assert.assertFalse((Boolean) js.get("truncated"),"The truncated value is true!");
								
	}
}
