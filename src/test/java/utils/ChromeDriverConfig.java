package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class ChromeDriverConfig {
    public static WebDriver getDownloadConfigurationsChromeDriver(String downloadFilePath) {
        Map<String,Object> prefs = new HashMap<>();
        ChromeOptions options = new ChromeOptions();
        prefs.put("download.default_directory", downloadFilePath);
        options.setExperimentalOption("prefs", prefs);
        return new ChromeDriver(options);

    }
}
