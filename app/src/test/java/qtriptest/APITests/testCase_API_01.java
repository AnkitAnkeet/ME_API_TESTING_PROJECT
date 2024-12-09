package qtriptest.APITests;

import io.restassured.RestAssured;

import qtriptest.DP;
import qtriptest.tests.testCase_01;
import org.testng.Assert;
import org.testng.annotations.*;



public class testCase_API_01 {
  testCase_01 api1;


    @BeforeClass(alwaysRun = true)
  public void init(){
    RestAssured.baseURI= "https://content-qtripdynamic-qa-backend.azurewebsites.net";
    api1 = new testCase_01();
  }

    @Test(enabled = true, description = "Verify user registration - login - logout", dataProvider = "data-provider" , dataProviderClass = DP.class, groups ={"API Tests"})
    public void TestCase01(String UserName, String Password) throws InterruptedException {
        //testCase_API_01 class instatiation

        //post registation
        api1.registration(UserName, Password, true);
        //validate the response code to be 201 for registration API
        Assert.assertEquals(api1.getResponse().getStatusCode(), 201, "status code of registration response is not 201");
        //validate the success is true for registration API
        Assert.assertEquals(api1.getJsonPath().getBoolean("success"), true, "success is not true");
        
        //post login
        api1.logIn();
        
        //validate the response body contains success=true
        Assert.assertEquals(api1.getJsonPath().getBoolean("success"), true, "success is not true");
        Assert.assertTrue(api1.getJsonPath().getBoolean("success"));
        //validate the response code to be 201 for login API
        Assert.assertEquals(api1.getResponse().getStatusCode(), 201,"status code of login response is not 201");
        //validate token is not null
        Assert.assertNotNull(testCase_01.getToken(), "token is null");
        //validate id is not null
        Assert.assertNotNull(testCase_01.getId(), "id is null");
    }

    
    

}
