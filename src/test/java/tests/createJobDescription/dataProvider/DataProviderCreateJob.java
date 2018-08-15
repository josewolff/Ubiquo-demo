package tests.createJobDescription.dataProvider;

import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import utils.GenerateUid;
import utils.ManageFiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

/**
 * Created by josea.wolff on 8/11/18.
 */
public class DataProviderCreateJob {

    @DataProvider(name = "createJobDescriptionBody")
    public static Iterator<Object[]> createJobDescriptionBody() throws IOException {
        Collection<Object[]> dpInfo = new ArrayList<Object[]>();
        String bodyFormat = ManageFiles.readFile("src/test/resources/CreateJobDescription/CreateJobDescriptionBody.json");
        for (int i = 0; i < 15; i++){
            String uID = GenerateUid.getUid();
            String body = String.format(bodyFormat,uID,uID);
            dpInfo.add(new Object[]{new JSONObject(body)});
        }
        return dpInfo.iterator();
    }

    @DataProvider(name = "generateCreateJobDescriptionBody")
    public static Iterator<Object[]> generateCreateJobDescriptionBody() throws IOException {
        Collection<Object[]> dpInfo = new ArrayList<Object[]>();
        String bodyFormat = ManageFiles.readFile("src/test/resources/CreateJobDescription/CreateJobDescriptionBody.json");
        String uID = GenerateUid.getUid();
        String body = String.format(bodyFormat,uID,uID);
        dpInfo.add(new Object[]{new JSONObject(body)});
        return dpInfo.iterator();
    }
}