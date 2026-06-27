package tests.ui.automationExercise.integration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ui.cart.CartPage;
import pages.ui.home.HomePage;

import java.time.Duration;

public class CartTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://automationexercise.com/");
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    @Test
    public void emptyCart(){
        HomePage homePage = new HomePage();
        CartPage cartPage = new CartPage();

        homePage.addProductToCart(driver,"6");
        homePage.clickContinueShoppingButton(driver);
        homePage.addProductToCart(driver,"7");
        homePage.navigateToPage(driver,"Cart");
        cartPage.deleteAllCartItems(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cartPage.cartIsEmptyMessageXpath)));
        String cartIsEmptyActualResultsMessage = driver.findElement(By.xpath(cartPage.cartIsEmptyMessageXpath)).getText();
        String cartIsEmptyExpectedResultsMessage = "Cart is empty!";
        Assert.assertEquals(cartIsEmptyActualResultsMessage, cartIsEmptyExpectedResultsMessage);
    }

    @AfterMethod(alwaysRun = true)
    public void teardown(){
        driver.quit();
    }
}


