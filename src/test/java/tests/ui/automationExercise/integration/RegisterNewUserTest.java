package tests.ui.automationExercise.integration;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ui.home.HomePage;
import pages.ui.loginSignup.signup.SignupPage;
import utils.ConfigReader;

import java.time.Duration;
import java.util.HashMap;

public class RegisterNewUserTest{
    WebDriver driver;
    ConfigReader configReader;

    @BeforeMethod
    public void setup() {
        configReader = new ConfigReader();
        if (configReader.getProperty("browser").equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get(configReader.getProperty("url"));
    }

    @Test
    public void registerNewUser(){
        HomePage homePage = new HomePage();
        SignupPage  SignupPage = new SignupPage();
        Faker faker = new Faker();
        String signupEmail = faker.internet().emailAddress();
        String signupUserName = faker.name().username();
//      click sinup/login link
        homePage.navigateToPage(driver,"LoginRegister");
//      enter signup user name
        WebElement enterSignupUserName = driver.findElement(By.xpath(SignupPage.dataQaXpath("input", "signup-name")));
        enterSignupUserName.sendKeys(signupUserName);
//      enter signup email
        WebElement enterSignupEmail = driver.findElement(By.xpath(SignupPage.dataQaXpath("input", "signup-email")));
        enterSignupEmail.sendKeys(signupEmail);
//      click signup button
        WebElement clickSignupBtn = driver.findElement(By.xpath(SignupPage.dataQaXpath("button", "signup-button")));
        clickSignupBtn.click();
//      fill new user data
        HashMap<String,String> signupCreateNewUser = SignupPage.registerNewUser(driver,faker,signupEmail);
//      click create account button
        WebElement clickCreateAccountBtn = driver.findElement(By.xpath(SignupPage.dataQaXpath("button", "create-account")));
        clickCreateAccountBtn.click();
//      click continue button
        WebElement clickSignupContinueBtn = driver.findElement(By.xpath(SignupPage.dataQaXpath("a", "continue-button")));
        clickSignupContinueBtn.click();
//      login with user to verify user is registered
        String actualSignupUsername = signupUserName;
        String expectedSignupUsername = driver.findElement(By.xpath("//a[contains(., ' Logged in as ')]")).getText();
        Assert.assertTrue(expectedSignupUsername.contains(actualSignupUsername));
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
