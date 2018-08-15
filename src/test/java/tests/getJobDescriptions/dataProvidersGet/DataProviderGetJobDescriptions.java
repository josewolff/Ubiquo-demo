package tests.getJobDescriptions.dataProvidersGet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import utils.ManageFiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

/**
 * Created by josea.wolff on 8/12/18.
 */
public class DataProviderGetJobDescriptions {

    @DataProvider(name = "filterByJobAccountUid", parallel = true)
    public static Iterator<Object[]> filterByJobAccountUid() throws IOException {
        Collection<Object[]> dpInfo = new ArrayList<Object[]>();
        String content = ManageFiles.readFile("src/test/resources/getJobDescriptions/ScenariosFindJobDescription.json");
        JSONObject contentFile = new JSONObject(content);
        JSONArray scenarios = contentFile.getJSONArray("scenariosByAccountUID");
        for (int i = 0; i < scenarios.length(); i++){
            dpInfo.add(new Object[]{scenarios.get(i)});
        }
        return dpInfo.iterator();
    }

}
