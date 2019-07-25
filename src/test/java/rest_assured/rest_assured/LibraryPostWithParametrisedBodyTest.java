package rest_assured.rest_assured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.*;

import Library_File.ReusableMethods;
import Library_File.payload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class LibraryPostWithParametrisedBodyTest {
	
	
	@DataProvider(name="BooksData")
	public Object[][] getData()
	{
		return new Object[][]{{"dfd","392"},{"vfv","342"},{"fh","645"},{"gff","4451"}};
	}
	
	@Test(dataProvider = "BooksData")
	public void Book(String isbn,String aisle)
	{
		RestAssured.baseURI="http://216.10.245.166";

		Response resp=given().

		header("Content-Type","application/json").

		body(payload.addBook(isbn,aisle)).

		when().

		post("/Library/Addbook.php").

		then().assertThat().statusCode(200).

		extract().response();

		JsonPath js= ReusableMethods.Raw_to_Json(resp);

		   String id=js.get("ID");

		   System.out.println(id);
	}

}
