package qtriptest.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class testCase_03 {
    private Response resp;
    public Response getResponse(){
        return resp;
    }
    private static JsonPath jp;
    public static JsonPath getJsonPath(){
        return jp;
    }
    private List<HashMap<String,String>> list;
    public List<HashMap<String,String>> getListOfHashMapFromReservations(){
        return list;
    }


    public void reservation(String userId, String name, String date, String personCount, String adventureId){
        RestAssured.baseURI = "https://content-qtripdynamic-qa-backend.azurewebsites.net";
        RestAssured.basePath = "/api/v1/reservations/new";

        JSONObject obj = new JSONObject();
        obj.put("userId",userId);
        obj.put("name",name);
        obj.put("date",date);
        obj.put("person",personCount);
        obj.put("adventure",adventureId);
        RequestSpecification http = RestAssured.given().header("Authorization","Bearer "+testCase_01.getToken()).header("Content-Type","application/json; charset=utf-8").body(obj.toString());

        resp = http.post();

        

    }

    public void getReservations(){
        RestAssured.baseURI = "https://content-qtripdynamic-qa-backend.azurewebsites.net";
        RestAssured.basePath = "/api/v1/reservations";

        RequestSpecification http = RestAssured.given().header("Authorization","Bearer "+testCase_01.getToken()).header("Content-Type","application/json; charset=utf-8").queryParam("id", testCase_01.getId());
        resp = http.get();

        jp = new JsonPath(resp.body().asString());

        list = jp.getList("$");       

    }
    
}
