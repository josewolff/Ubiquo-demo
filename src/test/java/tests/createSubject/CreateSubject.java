package tests.createSubject;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;
import tests.createSubject.dataProviderCreateSubject.DataProviderCreateSubject;
import tests.init.SetUrls;
import utils.apiCallsMethods.CreateSubjectRequest;


/**
 * Created by josea.wolff on 8/12/18.
 */
public class CreateSubject extends SetUrls {

    @Test(groups = {"all","createSubject"},
            dataProviderClass = DataProviderCreateSubject.class, dataProvider = "generateCreateSubjectBody",
            description = "This test create a new subject")
    public void createSubjectTest(JSONObject bodyData){
        String jobUid = bodyData.getString("job_uid");
        createSubjectRoute = String.format(createSubjectRoute,jobUid);
        System.out.println("\n----------------------- Create Request ---------------------\n");
        System.out.println("\t\t Request: " + createSubjectRoute);
        System.out.println("\t\t Body: " + bodyData.toString());
        System.out.println("------------------------------------------------------------\n");
        JSONArray response = CreateSubjectRequest.CreateSubject(createSubjectRoute,bodyData);
        System.out.println("********************* Response *************************\n");
        System.out.println("\t\t" + response.toString(10));
        System.out.println("********************************************************\n");
    }
}
