package pages.ui.loginSignup.signup;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;

public class SignupPage {
    public String dataQaXpath(String tagName, String dataQaName) {
        return "(//" + tagName + "[@data-qa='" + dataQaName + "'])";
    }
    String signupGenderRadioBtnXpath = "//input[@id='id_gender1']";
    String signupCheckboxXpath = "//input[@id='newsletter']";

    public HashMap<String,String> registerNewUser(WebDriver driver, Faker faker, String signupEmail) {
        Actions actions = new Actions(driver);
        String signupPassword = faker.internet().password();

//      accaount information
        WebElement clickSignupGenderRadioBtn = driver.findElement(By.xpath(signupGenderRadioBtnXpath));
        clickSignupGenderRadioBtn.click();
        WebElement enterSignupPassword = driver.findElement(By.xpath(dataQaXpath("input", "password")));
        enterSignupPassword.sendKeys(signupPassword);

        WebElement selectDayDropdownSignup = driver.findElement(By.xpath(dataQaXpath("select", "days")));
        Select daySelect = new Select(selectDayDropdownSignup);
        daySelect.selectByIndex(2);
        WebElement selectMonthDropdownSignup = driver.findElement(By.xpath(dataQaXpath("select", "months")));
        Select monthSelect = new Select(selectMonthDropdownSignup);
        monthSelect.selectByIndex(4);
        WebElement selectYearDropdownSignup = driver.findElement(By.xpath(dataQaXpath("select", "years")));
        Select yearSelect = new Select(selectYearDropdownSignup);
        yearSelect.selectByIndex(3);

        WebElement checkSignupCheckbox = driver.findElement(By.xpath(signupCheckboxXpath));
        checkSignupCheckbox.click();
//      adress information
        WebElement enterSignupFirstName = driver.findElement(By.xpath(dataQaXpath("input", "first_name")));
        enterSignupFirstName.sendKeys(faker.name().firstName());
        WebElement enterSignupLastName = driver.findElement(By.xpath(dataQaXpath("input", "last_name")));
        enterSignupLastName.sendKeys(faker.name().lastName());
        WebElement enterSignupCompany = driver.findElement(By.xpath(dataQaXpath("input", "company")));
        enterSignupCompany.sendKeys(faker.company().name());
        WebElement enterSignupAddress = driver.findElement(By.xpath(dataQaXpath("input", "address")));
        enterSignupAddress.sendKeys(faker.address().fullAddress());

        WebElement selectSignupCountryDropdown = driver.findElement(By.xpath(dataQaXpath("select", "country")));
        Select countrySelect = new Select(selectSignupCountryDropdown);
        countrySelect.selectByIndex(5);

        WebElement enterSignupStateName = driver.findElement(By.xpath(dataQaXpath("input", "state")));
        enterSignupStateName.sendKeys(faker.address().state());
        WebElement enterSignupCity = driver.findElement(By.xpath(dataQaXpath("input", "city")));
        enterSignupCity.sendKeys(faker.address().city());
        WebElement enterSignupZipcode = driver.findElement(By.xpath(dataQaXpath("input", "zipcode")));
        enterSignupZipcode.sendKeys(faker.address().zipCode());
        WebElement enterSignupMobileNUmber = driver.findElement(By.xpath(dataQaXpath("input", "mobile_number")));
        enterSignupMobileNUmber.sendKeys(faker.phoneNumber().phoneNumber());
//      actions.sendKeys(Keys.TAB).sendKeys(faker.address().state())
//                .sendKeys(Keys.TAB).sendKeys(faker.address().city())
//                .sendKeys(Keys.TAB).sendKeys(faker.address().zipCode())
//                .sendKeys(Keys.TAB).sendKeys(faker.phoneNumber().phoneNumber())
//                .perform();

        HashMap<String,String> SignupUserData = new HashMap<>();
        SignupUserData.put("email", signupEmail);
        SignupUserData.put("password", signupPassword);
        return SignupUserData;
    }
}
