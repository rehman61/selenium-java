package pages.ui.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CartPage {
    String cartItemXpath = "//tbody//tr";
    String deleteAllItemsButtonXpath = "//tbody//tr//td[6]";
    public String cartIsEmptyMessageXpath = "//b[text()='Cart is empty!']";
    public String proceedToCheckoutBtnXpath = "//a[@class='btn btn-default check_out']";
    public String placeaOrderBtnXpath = "//a[text()='Place Order']";
    public String payAndConfirmOrderBtnXpath = "//button[@id='submit']";
    public String downloadInvoiceBtnXpath = "//a[@href='/download_invoice/1400']";
    public String siteProductsTotalAmountXpath = "//td[contains(.,'Total Amount')]//following-sibling::td//p[@class='cart_total_price']";

    public WebElement getCartItem(WebDriver driver, String productNumber) {
        return driver.findElement(By.xpath(cartItemXpath + "[" + productNumber + "]"));

    }

    public void deleteAllCartItems(WebDriver driver) {
        List<WebElement> products = driver.findElements(By.xpath(deleteAllItemsButtonXpath));
        for (WebElement product : products) {
            product.click();
        }
    }

    //  invoice.txt file read  and convert method
    public String writeAndConvertInvoiceTxtFileAmount() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(System.getProperty("user.dir") + "/downloads/invoice.txt"));
            return lines.get(0).replaceAll("[^0-9]", "");
        } catch (IOException e) {
            return "";
        }
    }
}
