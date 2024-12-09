package qtriptest.APITests;

import io.restassured.module.jsv.JsonSchemaValidator;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.*;


import qtriptest.tests.testCase_02;

public class testCase_API_02{
    @Test(enabled = true, description = "Verify that the search City API Returns the correct number of results", groups ={"API Tests"})
    public static void TestCase02(){
        testCase_02 api2 = new testCase_02();

        //search city with query parameter q=beng
        api2.searchCityWithQuery("q", "beng");
        //validate the response code to be 200
        Assert.assertEquals(api2.getResponseFromCitySearch().getStatusCode(),200,"Response code is not 200");
        //validate there is 1 json object in response body
        Assert.assertEquals(api2.getListOfHashMapFromCitySearch().size(),1,"There are more than one json object");
        //validate the description to be "100+ Places"
        Assert.assertEquals(api2.getListOfHashMapFromCitySearch().get(0).get("description"),"100+ Places","Description is not as given'");

        //validate JSON Schema
        JsonSchemaValidator validator = JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/JsonSchema.json"));
        api2.getResponseFromCitySearch().then().assertThat().body(validator);
        
    }

}
