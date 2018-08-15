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
public class CreateJobDescriptionWithoutAccountID extends SetUrls {

    @Test(groups = {"all","createJobDescriptionTests", "createJobDescriptionWithoutAccountID"},
            dataProviderClass = DataProviderCreateJob.class, dataProvider = "generateCreateJobDescriptionBody",
          description = "This test tries to create a job description withhout account_uid, check that the scode id PreconditionFailed")
    public void createJobDescriptionWithoutAccountID(JSONObject bodyData){
        bodyData.remove("account_uid");
        System.out.println("\n----------------------- Create Request ---------------------\n");
        System.out.println("\t\t Request: " + createJobDescriptionRoute);
        System.out.println("\t\t Body: " + bodyData.toString());
        System.out.println("------------------------------------------------------------\n");
        Response responseOfService = RequestsMakers.makePostRequest(createJobDescriptionRoute, bodyData.toString())
                .then()
                .assertThat()
                .statusCode(412)
                .body("code",equalTo(10001),
                        "scode",equalTo("PreconditionFailed"),
                        "message",equalTo("Missing account_uid attribute"))
                .extract().response();
        JSONObject response = new JSONObject(responseOfService.getBody().asString());
        System.out.println("********************* Response *************************\n");
        System.out.println("\t\t" + response.toString(10));
        System.out.println("********************************************************\n");
    }

}
