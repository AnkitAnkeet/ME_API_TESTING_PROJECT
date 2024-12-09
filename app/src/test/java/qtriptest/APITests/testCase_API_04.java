package qtriptest.APITests;


import qtriptest.tests.testCase_01;

import org.testng.Assert;
import org.testng.annotations.*;




public class testCase_API_04{
    private testCase_01 api1;




@BeforeClass(alwaysRun = true)
public void init(){
    api1 = new testCase_01();
}





@Test(enabled = true, description = "Verify that a duplicate user account cannot be created on the Qtrip Website", groups ={"API Tests"})
public void TestCase04(){
    api1.registration("UserName@gmail.com", "Password", true);
    api1.registration(testCase_01.getUserName(), testCase_01.getPassword(), false);
    //validate the response code to be 400 for re-registration API 
    Assert.assertEquals(api1.getResponse().getStatusCode(), 400, "status code of re-registration response is not 400");
    //validate the message for re-registration API
    Assert.assertEquals(api1.getJsonPath().getString("message"), "Email already exists", "Re-registration happened");
   }
}  
