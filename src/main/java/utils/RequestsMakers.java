package utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

/**
 * Created by josea.wolff on 8/11/18.
 */
public class RequestsMakers {

    public static Response makePostRequest(String route, String bodyData){
        Response responseOfService = given()
                .header("Content-Type", "application/json")
                .body(bodyData.toString())
                .post(route);
        return responseOfService;
    }

    public static  Response makePostRequest(String route){
        Response responseOfService = given()
                .header("Content-Type", "application/json")
                .post(route);
        return responseOfService;
    }

    public static Response makeGetRequest(String route,RequestSpecification specification){
        Response responseOfService = specification
                .get(route);
        return responseOfService;
    }
}
