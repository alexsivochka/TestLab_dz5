package admin;

import admin.pages.LoginPage;
import admin.pages.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class TestRunner {

    WebDriver driver;
    LoginPage loginPage;
    MainPage mainPage;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
    }

    @Test(priority = 1,
            description = "Авторизация пользователя")
    public void login() {
        loginPage = new LoginPage(driver);
        loginPage.login("webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw");
        mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.checkLogoDisplay());
        mainPage.logout();
    }

    @Test(priority = 2,
            description = "Выбор пунктов меню")
    public void checkMenuItem() {
        loginPage = new LoginPage(driver);
        loginPage.login("webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw");
        mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.checkLogoDisplay());
        mainPage.selectAndCheckMenuItem();
        mainPage.logout();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
