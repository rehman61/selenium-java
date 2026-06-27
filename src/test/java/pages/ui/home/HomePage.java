package pages.ui.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class HomePage {
    String continueShoppingButtonXpath = "//button[@class='btn btn-success close-modal btn-block']";
    public String logoutBtnXpath = "//a[@href='/logout']";
    public Map <String,String> navigationLinks = Map.of(
            "Home","//a[text()=' Home']",
            "Cart","//a[text()=' Cart']",
            "LoginRegister","//a[text()=' Signup / Login']"
    );

    public void addProductToCart(WebDriver driver,String productNumber){
        WebElement productEl=driver.findElement(By.xpath("(//a[@data-product-id='"+productNumber+"'])[1]"));
        //scroll to  product
        //Actions actions=new Actions(driver);
        //actions.moveToElement(productEl).perform();
        /////////////////////////////////////
        productEl.click();
    }

    public void clickContinueShoppingButton(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        WebElement btnContinueShopping = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(continueShoppingButtonXpath)));
        btnContinueShopping.click();
    }

    public void navigateToPage(WebDriver driver,String navigateLink){
        WebElement navigateTo = driver.findElement(By.xpath(navigationLinks.get(navigateLink)));
        navigateTo.click();

    }



}
