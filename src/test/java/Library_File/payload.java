package Library_File;

public class payload {
	public static String postData()
	{
		String body = "{"+
			    "\"location\":{"+
		        "\"lat\" : -38.383494,"+
		        "\"lng\" : 33.427362"+
		    "},"+
		    "\"accuracy\":50,"+
		   "\"name\":\"Frontline house\","+
		    "\"phone_number\":\"(+91) 983 893 3937\","+
		    "\"address\" : \"29, side layout, cohen 09\","+
		    "\"types\": [\"shoe park\",\"shop\"],"+
		    "\"website\" : \"http://google.com\","+
		    "\"language\" : \"French-IN\""+
		"}";
		return body;
		
	}
	
	public static String addBook(String isbn, String aisle)
	{
		String body = "{"+
			"\"name\":\"Learn Appium Automation with Java\","+
			"\"isbn\":\""+isbn+"\","+
			"\"aisle\":\""+aisle+"\","+
			"\"author\":\"John foe\""+
			"}";
		return body;
		
	}
	
	

}
