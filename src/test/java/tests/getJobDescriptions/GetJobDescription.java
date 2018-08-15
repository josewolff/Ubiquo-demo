package tests.getJobDescriptions;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;
import tests.getJobDescriptions.dataProvidersGet.DataProviderGetJobDescriptions;
import tests.init.SetUrls;
import utils.RequestsMakers;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by josea.wolff on 8/11/18.
 */
public class GetJobDescription extends SetUrls {

    @Test(groups = {"all","getJobDescriptionsTests","getJobDescriptionsByAccountUid"},
          dataProviderClass = DataProviderGetJobDescriptions.class, dataProvider = "filterByJobAccountUid",
          description = "This test gets the jobs by account id and check that the number of elements is correct.")
    public void GetJobDescriptionByAccountUid(JSONObject data){
        System.out.println(data);
        String accountUid = data.getString("accountUid");
        int expectedCount = data.getInt("expectedCount");
        RequestSpecification queryStringParams = RestAssured.given().param("account_uid",accountUid);
        Response responseOfService = RequestsMakers.makeGetRequest(getJobDescriptionRoute,queryStringParams)
                .then()
                .statusCode(200)
                .body("size()", equalTo(expectedCount))
                .extract().response();
        JSONArray response = new JSONArray(responseOfService.getBody().asString());
        if(response.length() > 0){
            responseOfService.then()
                    .body("account_uid",hasItem(accountUid));
        }
        System.out.println(response.toString(10));
    }


    @Test(groups = {"all","getJobDescriptionsTests","GetJobDescriptionByKeyword_MissingChannelIdentifier"},
          description = "This test checks that when the parameter channel_identifier is not present, the service return an error.")
    public void GetJobDescriptionByKeyword_MissingChannelIdentifier(){
        RequestSpecification queryStringParams = RestAssured.given().param("keyword","TestingKeyword");
        Response responseOfService = RequestsMakers.makeGetRequest(getJobDescriptionRoute,queryStringParams)
                .then()
                .statusCode(412)
                .body("code", equalTo(10001),
                        "scode",equalTo("PreconditionFailed"),
                        "message",equalTo("Missing channel_identifier parameter"))
                .extract().response();
        JSONObject response = new JSONObject(responseOfService.getBody().asString());
        System.out.println(response.toString(10));
    }

    //Segun yo PreconditionFailedError	412	missing parameter
    /*.body("code", equalTo(10001),
                        "scode",equalTo("InternalError"),
                        "message",equalTo("Missing channel_identifier parameter"))*/
}
