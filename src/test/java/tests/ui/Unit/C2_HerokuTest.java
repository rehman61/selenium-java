package tests.ui.Unit;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.ScreenshootAuto;

import java.time.Duration;

@ExtendWith(ScreenshootAuto.class)
public class C2_HerokuTest {
    WebDriver driver;
    String url="https://the-internet.herokuapp.com/";

    @BeforeAll
    public static void intialize(){
        System.out.println("Heroku tests start");
    }

    @AfterAll
    public static void teardownAll(){
        System.out.println("This tests finish");
    }

    @BeforeEach
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        ScreenshootAuto.setDriver(driver);
    }

  /*  @AfterEach
    public void tearDown(){ driver.quit(); } */

    @ParameterizedTest
    @Description("Parametrized test")
    @Severity(SeverityLevel.MINOR)
    @ValueSource(strings={"https://www.google.com","https://www.amazon.com"})
    public void parametrizedTest(String pUrl){
        driver.get(pUrl);
    }

    @Test
    @Description("This test verify that checkbox is selected")
    @Severity(SeverityLevel.NORMAL)
    @Epic("Heroku App")
    @Feature("Checkbox")
    @Story("User should check the given checkbox")
    public void unitTests(){
        driver.get(url);
        WebElement checkBoxlink= driver.findElement(By.linkText("Checkboxes"));
        checkBoxlink.click();
        //Manually Screenshoot
     //   Screenshoot.takeScreenShoot(driver,"Checkbox Page");

        WebElement checkBox2= driver.findElement(By.xpath("//input[@type='checkbox'][2]"));
        Assertions.assertTrue(checkBox2.isSelected());
        //Assertions.assertFalse(checkBox2.isEnabled());
        //Manually Screenshoot
     //   Screenshoot.takeScreenShoot(driver,"Checkbox 2 is selected");

    }
}
