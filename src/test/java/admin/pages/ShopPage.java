package admin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ShopPage {

    By searchInput = By.xpath("//input[@name='s']");
    String xpath = "//a[contains(text(),'%s')]";

    By priceDetails = By.xpath("//div[@class='modal-content']//span[@itemprop='price']");
    By nameDetails = By.xpath("//div[@class='modal-content']//h1");
    By detailsForm = By.xpath("//div[@class='modal-content']");

    String productName;
    String productPrice;

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    private final WebDriver driver;

    public ShopPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchProduct(String productName) {
        driver.findElement(searchInput).sendKeys(productName);
        driver.findElement(searchInput).submit();
        String productXpath = String.format(xpath, productName);
        WebElement product = driver.findElement(By.xpath(productXpath));
        new WebDriverWait(driver,15)
                .until(ExpectedConditions.visibilityOf(product));
        Assert.assertTrue(product.isDisplayed(), "Товар не отображается на странице магазина");
    }

    public void checkProductDetails(String productName) {
        Actions actions = new Actions(driver);
        String productXpath = String.format(xpath, productName);
        WebElement result = driver.findElement(By.xpath(productXpath));
        WebElement details = driver.findElement(By.xpath("//a[@class='quick-view']"));
        actions.moveToElement(result).pause(500).moveToElement(details).pause(500).click().build().perform();
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(detailsForm));
        this.productName = driver.findElement(nameDetails).getAttribute("innerHTML");
        this.productPrice = driver.findElement(priceDetails).getAttribute("content");
    }

}
