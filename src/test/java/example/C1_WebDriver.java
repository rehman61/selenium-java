package example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class C1_WebDriver {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.amazon.com");
        String actualTitle=driver.getTitle();
        String expectedTitle="Amazon.com. Spend less. Smile more.";
        System.out.println(driver.getTitle());
    }
}

