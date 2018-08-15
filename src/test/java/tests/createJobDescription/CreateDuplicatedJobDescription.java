package tests.createJobDescription;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import tests.createJobDescription.dataProvider.DataProviderCreateJob;
import tests.init.SetUrls;
import utils.RequestsMakers;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by josea.wolff on 8/11/18.
 */
public class CreateDuplicatedJobDescription extends SetUrls {

    @Test(groups = {"all","createJobDescriptionTests", "createDuplicatedJobDescription"},
            dataProviderClass = DataProviderCreateJob.class, dataProvider = "generateCreateJobDescriptionBody",
          description = "Creates a duplicated job description and check that the scode is ConflictError")
    public void createDuplicatedJobDescriptionTest(JSONObject bodyData){
        System.out.println("\n----------------------- Create Request ---------------------\n");
        System.out.println("\t\t Request: " + createJobDescriptionRoute);
        System.out.println("\t\t Body: " + bodyData.toString());
        System.out.println("------------------------------------------------------------\n");
        RequestsMakers.makePostRequest(createJobDescriptionRoute, bodyData.toString());
        Response responseOfService = RequestsMakers.makePostRequest(createJobDescriptionRoute, bodyData.toString())
                .then()
                .assertThat()
                .statusCode(409)
                .body("code",equalTo(10001),
                      "scode",equalTo("ConflictError"),
                      "message",equalTo("INVALID KEYWORD"))
                .extract().response();
        JSONObject response = new JSONObject(responseOfService.getBody().asString());
        System.out.println("********************* Response *************************\n");
        System.out.println("\t\t" + response.toString(10));
        System.out.println("********************************************************\n");
    }
}
