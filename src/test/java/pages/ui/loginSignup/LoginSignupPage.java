package pages.ui.loginSignup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginSignupPage {
    String emailXpath = "//input[@data-qa='login-email']";
    String passwordXpath = "//input[@data-qa='login-password']";
    String loginXpath = "//button[@data-qa='login-button']";
    public String signupIncorrectMessageXpath = "//p[text()='Your email or password is incorrect!']";

    public void login(WebDriver driver, String email, String password) {
        driver.findElement(By.xpath(emailXpath)).sendKeys(email);
        driver.findElement(By.xpath(passwordXpath)).sendKeys(password);
        driver.findElement(By.xpath(loginXpath)).click();

    }
}
