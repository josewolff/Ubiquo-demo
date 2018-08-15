package tests.createSubject.dataProviderCreateSubject;

import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import utils.GenerateUid;
import utils.ManageFiles;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by josea.wolff on 8/12/18.
 */
public class DataProviderCreateSubject {

    @DataProvider(name = "generateCreateSubjectBody")
    public static Iterator<Object[]> generateCreateSubjectBody() throws IOException {
        Collection<Object[]> dpInfo = new ArrayList<Object[]>();
        String bodyFormat = ManageFiles.readFile("src/test/resources/createSubject/CreateSubjectBody.json");
        String jobDescriptionID = GenerateUid.getUid();
        String jobUid = GenerateUid.getUid();
        String profileUid = GenerateUid.getUid();
        String body = formatSubjectBody(bodyFormat,jobDescriptionID,jobUid,profileUid);
        dpInfo.add(new Object[]{new JSONObject(body)});
        return dpInfo.iterator();
    }

    public static String formatSubjectBody(String bodyFormat, String jobDesriptionUid, String jobUid, String profileUid){
        String body = String.format(bodyFormat,jobDesriptionUid,jobUid,profileUid);
        return body;
    }


}
