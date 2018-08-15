package listeners;

import org.testng.*;
import utils.GlobalVariables;

/**
 * Created by josea.wolff on 8/11/18.
 */
public class UbiquoListener implements ISuiteListener, ITestListener {

    public void onStart(ISuite iSuite) {
        GlobalVariables.env = iSuite.getParameter("env");
        GlobalVariables.host = iSuite.getParameter("host");
        System.out.println("############################### Changing Global Variables ######################################\n");
        System.out.println("\t\t\tEnv: " + GlobalVariables.env);
        System.out.println("\t\t\tHost: " + GlobalVariables.host);
        System.out.println("\n################################################################################################\n");
    }

    public void onFinish(ISuite iSuite) {

    }

    public void onTestStart(ITestResult iTestResult) {

    }

    public void onTestSuccess(ITestResult iTestResult) {

    }

    public void onTestFailure(ITestResult iTestResult) {

    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {

    }
}
