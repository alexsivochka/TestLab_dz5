package admin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class MainPage {

    By userIcon = By.id("employee_infos");
    By logoutLink = By.id("header_logout");
    By logo = By.xpath("//a[@id='header_logo']");

    By menuCatalog = By.id("subtab-AdminCatalog");
    By menuCategories = By.id("subtab-AdminCategories");

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean checkLogoDisplay() {
        return driver.findElement(logo).isDisplayed();
    }

    public LoginPage logout() {
        driver.findElement(userIcon).click();
        driver.findElement(logoutLink).click();
        return new LoginPage(driver);
    }

    public CategoryPage goToCategoryPage() {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(menuCatalog)).pause(500)
            .moveToElement(driver.findElement(menuCategories)).pause(500)
            .click().build().perform();
        return new CategoryPage(driver);
    }

}
