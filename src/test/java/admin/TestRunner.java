package admin;

import admin.listeners.EventHandler;
import admin.pages.ProductPage;
import admin.pages.LoginPage;
import admin.pages.MainPage;
import admin.pages.ShopPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class TestRunner {

    WebDriver driver;
    LoginPage loginPage;
    MainPage mainPage;
    ProductPage productPage;
    ShopPage shopPage;

    String productName = "Sivochka";
    String productCount = Random.getRandomCount();
    String productPrice = Random.getRandomPrice();

    @BeforeClass
    @Parameters("browser")
    public WebDriver getConfiguredDriver(String browser) {
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        if (browser.equals("IE")) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        }
//        driver.register(new EventHandler());
        driver.manage().window().maximize();
        driver.navigate().to("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
        return driver;
    }

    @DataProvider(name = "Authentication")
    public static Object[][] credentials() {
        return new Object[][] { { "webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw" }};
    }

    @Test(priority = 1,
        description = "Авторизация на сайте",
        dataProvider = "Authentication")
    public void login(String login, String Password){
        loginPage = new LoginPage(driver);
        loginPage.login(login, Password);
        mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.checkLogoDisplay());
    }

    @Test(priority = 2,
            dependsOnMethods = "login",
            description = "Создание нового продукта")
    public void createNewProduct() throws InterruptedException {
        mainPage.goToProductsPage();
        productPage = new ProductPage(driver);
        productPage.addProduct(productName, productCount, productPrice);
    }

    @Test(priority = 3,
    dependsOnMethods = "createNewProduct",
    description = "Отображение созданного продукта в магазине")
    public void checkProductView() throws InterruptedException {
        driver.get("http://prestashop-automation.qatestlab.com.ua/ru/");
        shopPage = new ShopPage(driver);
        shopPage.searchProduct(productName);
        shopPage.checkProductDetails(productName);
        Assert.assertEquals(shopPage.getProductName(), this.productName);
        Assert.assertEquals(shopPage.getProductPrice(), this.productPrice);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
