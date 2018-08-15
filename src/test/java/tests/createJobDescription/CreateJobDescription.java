package tests.createJobDescription;

import io.restassured.response.Response;
import org.hamcrest.core.IsNot;
import org.json.JSONObject;
import org.testng.annotations.Test;
import tests.createJobDescription.dataProvider.DataProviderCreateJob;
import tests.init.SetUrls;
import utils.RequestsMakers;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;

/**
 * Created by josea.wolff on 8/11/18.
 */
public class CreateJobDescription extends SetUrls {

    @Test(groups = {"all","createJobDescriptionTests", "createNewJobDescription"},
          dataProviderClass = DataProviderCreateJob.class, dataProvider = "createJobDescriptionBody",
          description = "This test create a new job description.")
    public void createJobDescriptionTest(JSONObject bodyData){
        System.out.println("\n----------------------- Create Request ---------------------\n");
        System.out.println("\t\t Request: " + createJobDescriptionRoute);
        System.out.println("\t\t Body: " + bodyData.toString());
        System.out.println("------------------------------------------------------------\n");
        Response responseOfService = RequestsMakers.makePostRequest(createJobDescriptionRoute, bodyData.toString())
                .then()
                .assertThat()
                .statusCode(200)
                .body("__v", equalTo(0),
                      "is_executing",equalTo(false),
                      "is_starting",equalTo(false),
                      "panel_check_session",equalTo(false),
                      "updated_on", new IsNot(isEmptyOrNullString()),
                      "created_on", new IsNot(isEmptyOrNullString()),
                      "_id", new IsNot(isEmptyOrNullString()))
                .extract().response();
        JSONObject response = new JSONObject(responseOfService.getBody().asString());
        System.out.println("********************* Response *************************\n");
        System.out.println("\t\t" + response.toString(10));
        System.out.println("********************************************************\n");
    }
}
