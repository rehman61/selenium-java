package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class TestListener implements ITestListener {
    private static WebDriver driver;

    public static void setDriver(WebDriver driver){
        TestListener.driver = driver;
    }

    @Override
    public void onTestStart(ITestResult result) {
//        ITestListener.super.onTestStart(result);
        System.out.println("Test Start "+result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
//        ITestListener.super.onTestSuccess(result);
        System.out.println("Test Success "+result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
//        ITestListener.super.onTestFailure(result);
        System.out.println("Test Failure "+result.getName());
        //take a screenshoot
        takeScreenShot(result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
//        ITestListener.super.onTestSkipped(result);
        System.out.println("Test Skipped "+result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }

    private void takeScreenShot(String testName){
        if (driver == null) {
            System.out.println("Driver is null, skipping screenshot for " + testName);
            return;
        }
        try {
            File screenshoot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshoot, new File("screenshots/"+testName+".png"));
            System.out.println("Screenshot saved for test "+testName);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed to save screenshot for test "+testName);
        }
    }
}
