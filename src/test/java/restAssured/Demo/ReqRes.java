package restAssured.Demo;
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
public class ReqRes {
    Properties prp;

    @Test
    public void addUserTest() throws IOException {
        String body = ReusableMethods.generateStringFromResources("./Resource/addUser.json");
        prp = ReusableMethods.getPropertyFile();
        RestAssured.baseURI = "https://reqres.in/";

        Response rs = given().header("Content-Type","application/json").body(body).

        when().post("api/users").

                then().assertThat().statusCode(201).log().all().

                extract().response();

        String response = rs.asString();
        JsonPath js = new JsonPath(response);
        System.out.println("Response :"+js.get("email"));
        Assert.assertEquals(js.get("password"),"pistol3");



    }



}
