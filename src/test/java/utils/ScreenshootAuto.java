package utils;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;

public class ScreenshootAuto implements TestWatcher {
    private static WebDriver driver;

    public static void setDriver(WebDriver driver){
        ScreenshootAuto.driver=driver;
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause){
        Screenshoot.takeScreenShoot(driver,"Failure Screenshot");
    }

    @Override
    public void testSuccessful(ExtensionContext context){
        Screenshoot.takeScreenShoot(driver,"Successful Screenshot");
    }
}
