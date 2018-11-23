package admin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class OrderPage {

    By orderSuccess = By.xpath("//section[@id='content-hook_order_confirmation']//h3");
    By productName = By.xpath("//div[contains(@class,'details')]//span");
    By productCount = By.xpath("//div[contains(@class,'qty')]//div[2]");
    By productPrice = By.xpath("//div[contains(@class,'qty')]//div[3]");
    By shopPageLink = By.xpath("//img[contains(@class,'logo')]");

    private final WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkOrderConfirmation() {
        String message = driver.findElement(orderSuccess).getText().trim().toLowerCase();
        Assert.assertTrue(message.contains("ваш заказ подтверждён"));
    }

    public ShopPage navigateToShopPage() {
        driver.findElement(shopPageLink).click();
        return new ShopPage(driver);
    }

    public String getProductName() {
        String name = driver.findElement(productName).getText();
        return name;
    }

    public String getProductCount() {
        String count = driver.findElement(productCount).getText();
        return count;
    }

    public String getProductPrice() {
        String price = driver.findElement(productPrice).getText();
        return price;
    }
}
