package tests.closeJobDescription;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import tests.createJobDescription.dataProvider.DataProviderCreateJob;
import tests.init.SetUrls;
import utils.apiCallsMethods.CreateJobDescription;
import utils.RequestsMakers;
import java.io.IOException;

import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by josea.wolff on 8/12/18.
 */
public class CreateAndCloseJobDescription extends SetUrls {

    private String jobDescriptionId = "";


    @Test(groups = {"createJobDescription"},
            dataProviderClass = DataProviderCreateJob.class, dataProvider = "generateCreateJobDescriptionBody",
            description = "Creates a new job description.")
    public void createJobDescription(JSONObject bodyData) throws IOException {
        jobDescriptionId = CreateJobDescription.createJobProcess(createJobDescriptionRoute, bodyData);
    }


    @Test(groups = {"all", "closeJobDescription"},
            dependsOnMethods = "createJobDescription",
            description = "Close a new job description.")
    public void closeJobDescription() throws IOException {
        String closeRoute =  String.format(closeJobDescriptionRoute, jobDescriptionId);
        System.out.println("\n------------------------------------- Close Request ----------------------------------------\n");
        System.out.println("\t\t Request: " + closeRoute);
        System.out.println("----------------------------------------------------------------------------------------------\n");
        Response responseOfService = RequestsMakers.makePostRequest(closeRoute)
                .then()
                .assertThat()
                .statusCode(200)
                .body("status", equalTo("FINISHED"),
                        "expired_sessions", equalTo(true))
                .extract().response();
        JSONObject response = new JSONObject(responseOfService.getBody().asString());
        System.out.println("############################## Response Close Job Description = " + jobDescriptionId + " ###############################\n");
        System.out.println(response.toString(10));
        System.out.println("####################################################################################################################\n");
    }
}
