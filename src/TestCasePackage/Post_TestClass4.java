package TestCasePackage;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import commonFunctionsPackage.API_Common_Function;
import io.restassured.path.json.JsonPath;
import RepositoryPackage.Post_Method_Repository;
import commonFunctionsPackage.Utility_Common_Function;
public class Post_TestClass4 {
	@Test
	public static void execute() throws IOException {
		for(int i=0; i<5; i++) 
		{
			int statusCode = API_Common_Function.statusCode(Post_Method_Repository.base_URI(),Post_Method_Repository.post_resource(),Post_Method_Repository.requestBody4());
			if(statusCode==201)
			{
				String responsebody= API_Common_Function.responsebody(Post_Method_Repository.base_URI(),Post_Method_Repository.post_resource(),Post_Method_Repository.requestBody4());
				Post_TestClass4.validator(responsebody,statusCode);
                Utility_Common_Function.evidencefilecreator("Evidence_TestCase4", Post_Method_Repository.requestBody4(), responsebody);
				break;
			}
			else
			{
				System.out.println("Status code found is incorrect therefore retrying the API");
			}
		}
		}
	
		public static void validator(String response_Body,int status_Code) throws IOException {
			
			JsonPath jspr = new JsonPath(Post_Method_Repository.requestBody4());
			String req_name = jspr.getString("name");
			String req_job = jspr.getString("job");
			JsonPath jsp=new JsonPath(response_Body);
			String res_name=jsp.getString("name");
			String res_job=jsp.getString("job");
			String res_id=jsp.getString("id");
			String res_createdAt=jsp.getString("createdAt");
			
			System.out.println(res_name);
			System.out.println(res_job);
			System.out.println(res_id);
			System.out.println(res_createdAt);
			
	    Assert.assertEquals(status_Code,201);
		Assert.assertEquals(res_name,req_name);
		Assert.assertEquals(res_job,req_job);
		Assert.assertNotNull(res_id);
		Assert.assertNotNull(res_createdAt);
		System.out.println("Validated successfully");
	}
}
