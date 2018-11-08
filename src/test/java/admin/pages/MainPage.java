package admin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class MainPage {

    By userIcon = By.id("employee_infos");
    By logoutLink = By.id("header_logout");
    By logo = By.xpath("//a[@id='header_logo']");
    By activeSection = By.xpath("//h2[contains(@class,'title')]");

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

    public void selectAndCheckMenuItem() {
        List<WebElement> elements = driver.findElements(By.xpath("//ul[contains(@class,'menu')]//a/span/ancestor::li"));
        for (int i = 1; i <=elements.size(); i++) {
            // Получаем текущий элемент
            String xpath = String.format("(//ul[contains(@class,'menu')]//a/span/ancestor::li)[%d]",i);
            WebElement element = driver.findElement(By.xpath(xpath));

            //Выводим значение элемента, с которым необходимо взаимодействовать, в консоль
            String menuTitle = element.getText();
            System.out.println("Нажимаем на пункт меню: ".concat(menuTitle));

            // Нажимаем на текущий элемент меню
            element.click();

            // Проверим, что элемент был действительно выбран
            WebElement activeElement = driver.findElement(By.xpath(xpath));
            String activeTitle = activeElement.getAttribute("class");
            Assert.assertTrue(activeTitle.contains("active"));

            String currentSection = driver.findElement(activeSection).getText().trim();
            // Выводим в консоль заголовок текущего раздела
            System.out.printf("Текущий заголовок раздела - \"%s\"\n", currentSection);

            // Обновляем страницу
            driver.navigate().refresh();

            // Проверим, что текущий пункт меню остается активным после обновления страницы
            WebElement afterRefresh = driver.findElement(By.xpath(xpath));
            String afterRefreshTitle = afterRefresh.getAttribute("class");
            Assert.assertTrue(afterRefreshTitle.contains("active"));

            // Получим и проверим заголовок страницы после обновления страницы
            String currentSectionAfterReload = driver.findElement(activeSection).getText().trim();
            System.out.printf("Заголовок раздела после обновления страницы - \"%s\"\n", currentSectionAfterReload);
            Assert.assertEquals(currentSection, currentSectionAfterReload, "Page titles are different!!!");

            System.out.println("------------------------------------------------");

        }
    }
}
