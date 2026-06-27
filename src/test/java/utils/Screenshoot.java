package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class Screenshoot {
    public static void takeScreenShoot(WebDriver driver, String screenshootName) {
        byte[] screenshootByte=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment(screenshootName,"image/png",new ByteArrayInputStream(screenshootByte),".png");
        driver.quit();
    }
}
