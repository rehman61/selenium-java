package tests.ui.automationExercise.e2e;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ui.cart.CartPage;
import pages.ui.home.HomePage;
import pages.ui.loginSignup.signup.SignupPage;
import utils.ChromeDriverConfig;
import utils.ConfigReader;
import utils.RetryAnalyzer;
import utils.TestListener;

import java.time.Duration;
import java.util.HashMap;

@Listeners(TestListener.class)
public class PurchaseProductTest {
    WebDriver driver;
    ConfigReader configReader;
    Faker faker = new Faker();
//    JavascriptExecutor js = (JavascriptExecutor) driver;


    HomePage homePage = new HomePage();
    SignupPage signupPage = new SignupPage();
    CartPage cartPage = new CartPage();

    String signupEmail = faker.internet().emailAddress();
    String signupUserName = faker.name().username();

    String downloadPath = System.getProperty("user.dir") + "/downloads";

    @BeforeMethod
    public void setup() {
        configReader = new ConfigReader();

        java.io.File downloadDir = new java.io.File(downloadPath);
        if (!downloadDir.exists()) {
            downloadDir.mkdirs(); // create folder
        }

        if (configReader.getProperty("browser").equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = ChromeDriverConfig.getDownloadConfigurationsChromeDriver(downloadPath);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get(configReader.getProperty("url"));
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void purchaseProduct() {
//      1. Register new user
//      click sinup/login link
        homePage.navigateToPage(driver, "LoginRegister");
//      enter signup user name
        WebElement enterSignupUserName = driver.findElement(By.xpath(signupPage.dataQaXpath("input", "signup-name")));
        enterSignupUserName.sendKeys(signupUserName);
//      enter signup email
        WebElement enterSignupEmail = driver.findElement(By.xpath(signupPage.dataQaXpath("input", "signup-email")));
        enterSignupEmail.sendKeys(signupEmail);
//      click signup button
        WebElement clickSignupBtn = driver.findElement(By.xpath(signupPage.dataQaXpath("button", "signup-button")));
        clickSignupBtn.click();
//      fill new user data
        HashMap<String, String> signupCreateNewUser = signupPage.registerNewUser(driver, faker, signupEmail);
//      click create account button
        WebElement clickCreateAccountBtn = driver.findElement(By.xpath(signupPage.dataQaXpath("button", "create-account")));
//        js.executeScript("arguments[0].scrollIntoView(true);", clickCreateAccountBtn);
        clickCreateAccountBtn.click();
//      click continue button
        WebElement clickSignupContinueBtn = driver.findElement(By.xpath(signupPage.dataQaXpath("a", "continue-button")));
        clickSignupContinueBtn.click();

//      2.Add product to cart
        homePage.addProductToCart(driver, "6");
        homePage.clickContinueShoppingButton(driver);
        homePage.addProductToCart(driver, "7");

//      navigate to cart page
        homePage.navigateToPage(driver, "Cart");

//      3. Payment to product
//      click proceed to checkout buttton
        WebElement clickProceedToCheckoutBtn = driver.findElement(By.xpath(cartPage.proceedToCheckoutBtnXpath));
        clickProceedToCheckoutBtn.click();
//      get site products total amount
        String siteProductsTotalAmount = driver.findElement(By.xpath(cartPage.siteProductsTotalAmountXpath)).getText();
//      click place order button
        WebElement clickPlaceOrderBtn = driver.findElement(By.xpath(cartPage.placeaOrderBtnXpath));
        clickPlaceOrderBtn.click();
//      fill to payment methods
        WebElement enterNameOnCard = driver.findElement(By.xpath(signupPage.dataQaXpath("input", "name-on-card")));
        enterNameOnCard.sendKeys(faker.name().firstName() + " " + faker.name().lastName());
        WebElement enterCardNumber = driver.findElement(By.xpath(signupPage.dataQaXpath("input", "card-number")));
        enterCardNumber.sendKeys(faker.finance().creditCard());
        WebElement enterCardCvcNumber = driver.findElement(By.xpath(signupPage.dataQaXpath("input", "cvc")));
        enterCardCvcNumber.sendKeys("956");
        WebElement enterCardExpirationMonth = driver.findElement(By.xpath(signupPage.dataQaXpath("input", "expiry-month")));
        enterCardExpirationMonth.sendKeys("05");
        WebElement enterCardExpirationYear = driver.findElement(By.xpath(signupPage.dataQaXpath("input", "expiry-year")));
        enterCardExpirationYear.sendKeys("1996");
//      click pay and confirm button
        WebElement clickPayAndConfirmOrderBtn = driver.findElement(By.xpath(cartPage.payAndConfirmOrderBtnXpath));
        clickPayAndConfirmOrderBtn.click();
//      4. download payment file and file total products amount compare site total products amount
//      click download invoice button
        WebElement clickDownloadInvoiceBtn = driver.findElement(By.xpath(cartPage.downloadInvoiceBtnXpath));
        clickDownloadInvoiceBtn.click();
//      read and convert amount invoice.txt file with method
        String invoiceTxtFileAmount = cartPage.writeAndConvertInvoiceTxtFileAmount();
//      compare invoice.txt file amount and site products total amount
        Assert.assertTrue(siteProductsTotalAmount.contains(invoiceTxtFileAmount));
    }


    @AfterMethod
    public void teardown() {
        driver.quit();
//      invoice.txt file deleting
        java.io.File downloadDir = new java.io.File(System.getProperty("user.dir") + "/downloads");
        if (downloadDir.exists() && downloadDir.listFiles() != null) {
            for (java.io.File file : downloadDir.listFiles()) {
                file.delete();
            }
            downloadDir.delete();
        }
    }
}


