package qtriptest.tests;

import org.json.JSONObject;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;

import java.util.UUID;


//Verify that a new user can be registered and login using APIs of QTrip
public class testCase_01 {
  private static String randomUserName;
  public static String getUserName(){
    return randomUserName;
  }
  
  private static String randomPassword;
  public static String getPassword(){
    return randomPassword;
  }

  private  Response resp;
  public Response getResponse(){
    return resp;
  } 
  private JsonPath jp;
  public JsonPath getJsonPath(){
    return jp;
  } 

  private static String token;
  public static String getToken(){
    return token;
  } 
  private static String id;
  public static String getId(){
    return id;
  } 

  
  
  //registration flow
   public void registration(String UserName, String Password, boolean isDynamic){
    RestAssured.baseURI= "https://content-qtripdynamic-qa-backend.azurewebsites.net";
    RestAssured.basePath="/api/v1/register";
   
    if(isDynamic){
    testCase_01.randomUserName = UUID.randomUUID().toString()+UserName;
    }else{
    testCase_01.randomUserName = UserName;
    }
    testCase_01.randomPassword = Password;
    JSONObject json = new JSONObject();
    json.put("email",testCase_01.randomUserName);
    json.put("password",testCase_01.randomPassword);
    json.put("confirmpassword",testCase_01.randomPassword);

    


    RequestSpecification http = RestAssured.given().header("Content-Type","application/json; charset=utf-8").body(json.toString());
    resp = http.post();
    // System.out.println(resp.getStatusCode());
    // System.out.println(resp.getBody().asPrettyString());
    jp = new JsonPath(resp.body().asString());

  }

  public void logIn(){
    RestAssured.baseURI= "https://content-qtripdynamic-qa-backend.azurewebsites.net";
    RestAssured.basePath="/api/v1/login";

    JSONObject json = new JSONObject();
    json.put("email",testCase_01.randomUserName);
    json.put("password",testCase_01.randomPassword);

    RequestSpecification http = RestAssured.given().header("Content-Type","application/json; charset=utf-8").body(json.toString());
    resp = http.post();
    // System.out.println(resp.getStatusCode());
    // System.out.println(resp.getBody().asPrettyString());

    jp = new JsonPath(resp.body().asString());
    

    token = jp.getString("data.token");
    // System.out.println("token is "+token);
    id = jp.getString("data.id");
    // System.out.println("id is "+id);
    
  } 
   
}
