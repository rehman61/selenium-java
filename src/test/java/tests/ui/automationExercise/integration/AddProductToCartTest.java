package tests.ui.automationExercise.integration;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ui.cart.CartPage;
import pages.ui.home.HomePage;
import pages.ui.loginSignup.LoginSignupPage;

import java.time.Duration;

public class AddProductToCartTest {
    WebDriver driver;
    String productNameTextXpath = "(//div[@class='productinfo text-center']//p[text()='Blue Top'])[1]";
    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://automationexercise.com/");

    }

    @Test
    @Description("Add product to cart without log in")
    public void addToCartWithoutLogin() {
        HomePage homePage = new HomePage();
        CartPage cartPage = new CartPage();

        WebElement loginBoxEl = driver.findElement(By.xpath(homePage.navigationLinks.get("LoginRegister")));
        Assert.assertTrue(loginBoxEl.isDisplayed());

        homePage.addProductToCart(driver,"1");

        String productNameText=driver.findElement(By.xpath(productNameTextXpath)).getText();

        homePage.navigateToPage(driver,"Cart");
        driver.navigate().refresh();
        String firstCartItemtext = cartPage.getCartItem(driver,"1").getText();
        Assert.assertTrue(firstCartItemtext.contains(productNameText));
    }

    @Test
    @Description("Add product to cart with log in")
    public void addToCartWithtLogin() {
        HomePage homePage = new HomePage();
        CartPage cartPage = new CartPage();
        LoginSignupPage loginSignupPage = new LoginSignupPage();

        WebElement loginBoxEl = driver.findElement(By.xpath(homePage.navigationLinks.get("LoginRegister")));
        Assert.assertTrue(loginBoxEl.isDisplayed());
        loginBoxEl.click();
        loginSignupPage.login(driver,"user.user@gmail.com","12345");

        homePage.addProductToCart(driver,"1");

        String productNameText=driver.findElement(By.xpath(productNameTextXpath)).getText();

        homePage.navigateToPage(driver,"Cart");
        driver.navigate().refresh();
        String firstCartItemtext = cartPage.getCartItem(driver,"1").getText();
        Assert.assertTrue(firstCartItemtext.contains(productNameText));
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}



