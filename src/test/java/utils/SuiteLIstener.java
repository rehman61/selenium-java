package utils;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteLIstener implements ISuiteListener {
    public void onStart(ISuite suite) {
        System.out.println("Suite "+suite.getName()+" Started");
    }

    public void onFinish(ISuite suite) {
        System.out.println("Suite "+suite.getName()+" Finished");
        suite.getResults().forEach((testName,result)->{
            System.out.println("Test Name: "+testName);
            System.out.println("Passed Test: "+result.getTestContext().getPassedTests().size());
            System.out.println("Failed Test: "+result.getTestContext().getFailedTests().size());
            System.out.println("Skipped Test: "+result.getTestContext().getSkippedTests().size());
        });
    }
}
