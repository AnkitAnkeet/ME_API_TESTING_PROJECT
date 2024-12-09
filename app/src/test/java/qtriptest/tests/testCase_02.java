package qtriptest.tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;


import java.util.HashMap;
import java.util.List;

public class testCase_02 {
    
    private Response resp;
    public Response getResponseFromCitySearch(){
        return resp;
    }

    private List<HashMap<String,String>> list;
    public List<HashMap<String,String>> getListOfHashMapFromCitySearch(){
        return list;
    }

    
     

    public void searchCityWithQuery(String queryKey, String queryValue){

        RestAssured.baseURI= "https://content-qtripdynamic-qa-backend.azurewebsites.net";
        RestAssured.basePath = "/api/v1/cities";
        RequestSpecification http = RestAssured.given().header("Content-Type","application/json; charset=utf-8").queryParam(queryKey,queryValue);

        resp = http.get();

        JsonPath jp = new JsonPath(resp.body().asString());
        list = jp.getList("$");
        // System.out.println(list);

        
        

    }

   



}
