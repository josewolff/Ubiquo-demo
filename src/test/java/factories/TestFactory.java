package factories;

import org.testng.annotations.Factory;
import tests.closeJobDescription.CreateAndCloseJobDescription;
import tests.createJobDescription.CreateJobDescription;
import tests.createJobDescription.CreateJobDescriptionWithoutAccountID;
import tests.createJobDescription.CreateDuplicatedJobDescription;
import tests.createSubject.CreateSubject;
import tests.getJobDescriptions.GetJobDescription;
import tests.pushWorkFlow.PushWorkFlow;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by josea.wolff on 8/11/18.
 */
public class TestFactory {

    @Factory
    public Object[] factoryMethod() throws IOException {
        ArrayList<Object> result = new ArrayList<Object>();
        result.add(new CreateJobDescription());
        result.add(new CreateDuplicatedJobDescription());
        result.add(new CreateJobDescriptionWithoutAccountID());
        result.add(new GetJobDescription());
        result.add(new CreateSubject());
        result.add(new CreateAndCloseJobDescription());
        result.add(new PushWorkFlow());
        return result.toArray();
    }
}
