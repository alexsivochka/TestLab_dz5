package admin.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

    By addProductButton = By.xpath("//a[contains(@id,'add')]");
    By productNameInput = By.id("form_step1_name_1");
    By productQuantityInput = By.id("form_step1_qty_0_shortcut");
    By productPriceInput = By.id("form_step1_price_shortcut");



    private final WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addProduct(String name) {
        driver.findElement(addProductButton).click();
        driver.findElement(productNameInput).sendKeys(name);
        driver.findElement(productQuantityInput).sendKeys(RandomStringUtils.randomNumeric(1,100));
        driver.findElement(productPriceInput).sendKeys(RandomStringUtils.randomNumeric(1,50));
        driver.findElement(productPriceInput).submit();
//        driver.findElement(addCategoryButton).click();
//        new WebDriverWait(driver,10)
//                .withMessage("Add category error")
//                .until(ExpectedConditions.visibilityOfElementLocated(alertSuccess));
    }

//    public void findCategory(String categoryName) {
//        driver.findElement(nameFilter).sendKeys(categoryName);
//        driver.findElement(submitFilter).click();
//        String searchXpath = String.format("//td[contains(text(),'%s')]", categoryName);
//        By search = By.xpath(searchXpath);
//        new WebDriverWait(driver, 10)
//                .withMessage("Category not found")
//                .until(ExpectedConditions.visibilityOfElementLocated(search));
//    }
}
