package utils.apiCallsMethods;

import io.restassured.response.Response;
import org.hamcrest.core.IsNot;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.RequestsMakers;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;

/**
 * Created by josea.wolff on 8/12/18.
 */
public class CreateSubjectRequest {

    public static JSONArray CreateSubject(String createSubjectRoute, JSONObject bodyData){
        System.out.println("\n----------------------- Create Subject ---------------------\n");
        System.out.println("\t\t Request: " + createSubjectRoute);
        System.out.println("\t\t Body: " + bodyData.toString());
        System.out.println("------------------------------------------------------------\n");
        Response responseOfService = RequestsMakers.makePostRequest(createSubjectRoute, bodyData.toString())
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()", equalTo(1),
                        "updated_on", new IsNot(isEmptyOrNullString()),
                        "created_on", new IsNot(isEmptyOrNullString()),
                        "_id", new IsNot(isEmptyOrNullString()))
                .extract().response();
        JSONArray response = new JSONArray(responseOfService.getBody().asString());
        System.out.println("############################## Response Create Job Description ###############################\n");
        System.out.println(response.toString(10));
        System.out.println("##############################################################################################\n");
        return response;
    }
}
