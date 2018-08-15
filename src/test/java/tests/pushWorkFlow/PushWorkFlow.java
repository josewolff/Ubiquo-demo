package tests.pushWorkFlow;

import io.restassured.response.Response;
import org.hamcrest.core.IsNot;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;
import tests.createJobDescription.dataProvider.DataProviderCreateJob;
import tests.createSubject.dataProviderCreateSubject.DataProviderCreateSubject;
import tests.init.SetUrls;
import utils.GenerateUid;
import utils.ManageFiles;
import utils.RequestsMakers;
import utils.apiCallsMethods.CreateJobDescription;
import utils.apiCallsMethods.CreateSubjectRequest;
import java.io.IOException;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;

/**
 * Created by josea.wolff on 8/12/18.
 */
public class PushWorkFlow extends SetUrls {

    private String jobDescriptionId = "";
    private String jobUid = "";
    private String subjectId = "";


    @Test(groups = {"createJobDescriptionPW"},
            dataProviderClass = DataProviderCreateJob.class, dataProvider = "generateCreateJobDescriptionBody",
            description = "Creates a new job description.")
    public void createJobDescriptionPW(JSONObject bodyData) throws IOException {
        jobDescriptionId = CreateJobDescription.createJobProcess(createJobDescriptionRoute, bodyData);
    }

    @Test(groups = {"createSubjectPW"},
            dependsOnMethods = "createJobDescriptionPW",
            description = "Creates a new Subject")
    public void createSubjectPW() throws IOException {
        String subjectBodyFormat = ManageFiles.readFile("src/test/resources/createSubject/CreateSubjectBody.json");
        jobUid = GenerateUid.getUid();
        createSubjectRoute = String.format(createSubjectRoute,jobUid);
        String profileUid = GenerateUid.getUid();
        String body = DataProviderCreateSubject.formatSubjectBody(subjectBodyFormat,jobDescriptionId,jobUid,profileUid);
        JSONArray responseSubjectService = CreateSubjectRequest.CreateSubject(createSubjectRoute, new JSONObject(body));
        subjectId = responseSubjectService.getJSONObject(0).getString("_id");
        System.out.println(responseSubjectService.toString(10));
    }

    @Test(groups = {"all","pushWorkFlowTest"},
            dependsOnMethods = "createSubjectPW",
            description = "Push the subject to the workflow")
    public void pushWorkFlowTest() throws IOException {
        String pushWorkFlowBody = ManageFiles.readFile("src/test/resources/pushWorkFlow/PushWorkFlow.json");
        String workFlowUId = GenerateUid.getUid();
        String workFlowUnitId = GenerateUid.getUid();
        pushWorkFlowBody = String.format(pushWorkFlowBody,workFlowUId,workFlowUnitId);
        pushWorkFlowRoute = String.format(pushWorkFlowRoute,jobUid,subjectId);
        System.out.println("\n----------------------- PushWorkFlow Request ---------------------\n");
        System.out.println("\t\t Request: " + pushWorkFlowRoute);
        System.out.println("\t\t Body: " + pushWorkFlowBody.toString());
        System.out.println("------------------------------------------------------------\n");
        Response responseOfService = RequestsMakers.makePostRequest(pushWorkFlowRoute, pushWorkFlowBody.toString())
                .then()
                .assertThat()
                .statusCode(200)
                .body("__v", equalTo(0),
                        "updated_on", new IsNot(isEmptyOrNullString()),
                        "created_on", new IsNot(isEmptyOrNullString()),
                        "_id", equalTo(subjectId),
                        "workflow_uid",equalTo(workFlowUId),
                        "workflow_unit_uid",equalTo(workFlowUnitId),
                        "status", equalTo("PENDING"))
                .extract().response();
        JSONObject response = new JSONObject(responseOfService.getBody().asString());
        System.out.println("********************* Response *************************\n");
        System.out.println("\t\t" + response.toString(10));
        System.out.println("********************************************************\n");
    }

}
