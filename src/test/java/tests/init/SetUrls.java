package tests.init;

import org.testng.annotations.BeforeClass;
import utils.GlobalVariables;

/**
 * Created by josea.wolff on 8/12/18.
 */
public class SetUrls {
    protected String createJobDescriptionRoute;
    protected String closeJobDescriptionRoute;
    protected String getJobDescriptionRoute;
    protected String createSubjectRoute;
    protected String pushWorkFlowRoute;

    @BeforeClass(alwaysRun = true)
    public void initUrl(){
        createJobDescriptionRoute = GlobalVariables.host + "job_descriptions";
        closeJobDescriptionRoute = GlobalVariables.host + "job_descriptions/%s/close";
        getJobDescriptionRoute = GlobalVariables.host + "job_descriptions";
        createSubjectRoute = GlobalVariables.host + "jobs/%s/subjects";
        pushWorkFlowRoute = GlobalVariables.host + "jobs/%s/subjects/%s/pushWorkflow";
    }
}
