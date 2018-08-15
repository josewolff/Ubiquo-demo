package utils.apiCallsMethods;

import io.restassured.response.Response;
import org.json.JSONObject;
import utils.RequestsMakers;

import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by josea.wolff on 8/12/18.
 */
public class CreateJobDescription {

    public static String createJobProcess(String createJobDescriptionRoute, JSONObject bodyData){
        System.out.println("\n----------------------- CreateJobDescription Request ---------------------\n");
        System.out.println("\t\t Request: " + createJobDescriptionRoute);
        System.out.println("\t\t Body: " + bodyData.toString());
        System.out.println("------------------------------------------------------------\n");
        Response responseOfService = RequestsMakers.makePostRequest(createJobDescriptionRoute, bodyData.toString())
                .then()
                .assertThat()
                .statusCode(200)
                .body("status", equalTo("NOT_STARTED"))
                .extract().response();
        JSONObject response = new JSONObject(responseOfService.getBody().asString());
        String jobDescriptionId = response.getString("_id");
        System.out.println("############################## Response Create Job Description ###############################\n");
        System.out.println(response.toString(10));
        System.out.println("##############################################################################################\n");
        return jobDescriptionId;
    }
}


