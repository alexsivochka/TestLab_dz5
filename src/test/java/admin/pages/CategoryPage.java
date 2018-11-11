package admin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoryPage {

    By addCatigoryLink = By.xpath("//a[contains(@id,'new_category')]");
    By categoryInput = By.name("name_1");
    By addCategoryButton = By.id("category_form_submit_btn");
    By alertSuccess = By.xpath("//div[@class='alert alert-success']");
    By nameFilter = By.name("categoryFilter_name");
    By submitFilter = By.name("submitFilter");

    private final WebDriver driver;

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addCategory (String name) {
        driver.findElement(addCatigoryLink).click();
        driver.findElement(categoryInput).sendKeys(name);
        driver.findElement(addCategoryButton).click();
        new WebDriverWait(driver,10)
                .withMessage("Add category error")
                .until(ExpectedConditions.visibilityOfElementLocated(alertSuccess));
    }

    public void findCategory(String categoryName) {
        driver.findElement(nameFilter).sendKeys(categoryName);
        driver.findElement(submitFilter).click();
        String searchXpath = String.format("//td[contains(text(),'%s')]", categoryName);
        By search = By.xpath(searchXpath);
        new WebDriverWait(driver, 10)
                .withMessage("Category not found")
                .until(ExpectedConditions.visibilityOfElementLocated(search));
    }
}
