package qtriptest.APITests;

import qtriptest.tests.testCase_01;
import qtriptest.tests.testCase_03;

import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.*;

public class testCase_API_03{
    private testCase_01 api1;
    private testCase_03 api3;

    @BeforeClass(alwaysRun = true)
    public void init(){
        api1 = new testCase_01();
        api3 = new testCase_03();
    }

    @Test(enabled = true, description = "Verify that a reservation can be made using the QTrip API", groups ={"API Tests"})
    public void TestCase03(){
        api1.registration("UserName@gmail.com", "Password", true);
        api1.logIn();
        api3.reservation(testCase_01.getId(), "Hayabusa Sanjo", LocalDate.now().plusDays(1).toString() , "1", "2447910730");

        //Validate that On a successful booking, status code 200 should be returned
        Assert.assertEquals(api3.getResponse().getStatusCode(), 200,"status code 200 is not returned");

        //get all reservations
        api3.getReservations();
        //validate the response code to be 200 
        Assert.assertEquals(api3.getResponse().getStatusCode(),200,"Response code is not 200");
        for(int i=0;i<api3.getListOfHashMapFromReservations().size();i++){
            if(api3.getListOfHashMapFromReservations().get(i).get("name").equals("Hayabusa Sanjo")){
                Assert.assertEquals(api3.getListOfHashMapFromReservations().get(i).get("adventure"), "2447910730");
                Assert.assertEquals(api3.getListOfHashMapFromReservations().get(i).get("person"), "1");
                Assert.assertEquals(api3.getListOfHashMapFromReservations().get(i).get("userId"), testCase_01.getId());

            }
        }

    }
}