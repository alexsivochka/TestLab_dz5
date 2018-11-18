package admin;

import admin.listeners.EventHandler;
import admin.pages.ProductPage;
import admin.pages.LoginPage;
import admin.pages.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class TestRunner {

    EventFiringWebDriver driver;
    LoginPage loginPage;
    MainPage mainPage;
    ProductPage categoryPage;

    @BeforeClass
    public EventFiringWebDriver getConfiguredDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.register(new EventHandler());
        driver.manage().window().maximize();
        driver.navigate().to("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
        return driver;
    }

    @Test(priority = 1,
            description = "Создание новой категории товара")
    public void createNewCategory() {
        loginPage = new LoginPage(driver);
        loginPage.login("webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw");
        mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.checkLogoDisplay());
        mainPage.goToProductsPage();
        categoryPage = new ProductPage(driver);
        categoryPage.addProduct("Test product" +
                "");
//        categoryPage.findCategory("Test category");

    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
