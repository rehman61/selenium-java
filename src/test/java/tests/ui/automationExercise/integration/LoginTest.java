package tests.ui.automationExercise.integration;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.setup.BaseTest;


public class LoginTest extends BaseTest {
//    WebDriver driver;
//    ConfigReader configReader = new ConfigReader();
//    HomePage homePage = new HomePage();
//    LoginSignupPage loginSignupPage = new LoginSignupPage();

//    @BeforeMethod(alwaysRun = true)
//    public void setup() {
//        if (configReader.getProperty("browser").equals("chrome")) {
//            WebDriverManager.chromedriver().setup();
//            driver = new ChromeDriver();
//        }
//        TestListener.setDriver(driver);
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//        driver.get(configReader.getProperty("url"));
//
//    }

    @DataProvider(name = "loginData")
    public Object[][] getData() {
        return new Object[][]{
                {configReader.getProperty("emailPositive"), configReader.getProperty("passwordPositive"), true},
                {configReader.getProperty("emailNegative"), configReader.getProperty("passwordNegative"), false}
        };
    }

    public void loginTest(String email, String password, boolean IsPositiveTest) {
        homePage.navigateToPage(driver, "LoginRegister");
        loginSignUpPage.login(driver, email, password);

        if (IsPositiveTest) {
            Assert.assertTrue(driver.findElement(By.xpath(homePage.logoutBtnXpath)).isDisplayed());
        } else {
            Assert.assertTrue(driver.findElement(By.xpath(loginSignUpPage.signupIncorrectMessageXpath)).isDisplayed());
        }
    }
}