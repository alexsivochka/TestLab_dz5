package admin.pages;

import admin.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShopPage {

    By logo = By.xpath("//img[contains(@class,'logo')]/ancestor::div[1]");
    By allProductLink = By.xpath("//a[contains(@class,'all-product')]");

    By productName = By.xpath("//h1[@itemprop='name']");
    By productPrice = By.xpath("//span[@itemprop='price']");

    By addToCartButton = By.xpath("//button[contains(@class,'add-to-cart')]");
    By productDetails = By.xpath("//a[@href='#product-details']");
    By stockCount = By.xpath("//div[@class='product-quantities']/span");
    By goToCart = By.xpath("//div[@class='cart-content']/a");
    By searchInput = By.xpath("//input[@name='s']");


    private final WebDriver driver;

    public ShopPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getSiteVersion() {
        return driver.findElement(logo).getAttribute("id");
    }

    public ShopPage goToProducts() {
        driver.findElement(allProductLink).click();
        return this;
    }

    public ShopPage openRandomProduct() {
        String productCount = Random.getRandomProductNumber();
        String xpath = String.format("//article[%s]//h1[@itemprop='name']/a", productCount);
        driver.findElement(By.xpath(xpath)).click();
        return this;
    }

    public String getProductName() {
        String name = driver.findElement(productName).getText();
        return name;
    }

    public String getProductPrice() {
        String price = driver.findElement(productPrice).getText();
        return price;
    }

    public String getProductStockCount() {
        driver.findElement(productDetails).click();
        String stock = driver.findElement(stockCount).getAttribute("innerHTML");
        String []arr = stock.split("\\s+");
        return arr[0];
    }

    public ShopPage searchProduct(String name) {
        driver.findElement(searchInput).sendKeys(name);
        driver.findElement(searchInput).submit();
        new WebDriverWait(driver,10)
                .until(ExpectedConditions.visibilityOfElementLocated(productName));
        driver.findElement(productName).click();
        return this;
    }

    public void addProductToCart() {
        driver.findElement(addToCartButton).click();
        new WebDriverWait(driver,10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='blockcart-modal']")));
        driver.findElement(goToCart).click();
    }
}
