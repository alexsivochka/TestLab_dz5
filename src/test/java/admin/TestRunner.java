package admin;

import admin.pages.CartPage;
import admin.pages.OrderPage;
import admin.pages.ShopPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class TestRunner {

    WebDriver driver;

    ShopPage shopPage;
    CartPage cartPage;
    OrderPage orderPage;

    @BeforeClass
    @Parameters("browser")
    public WebDriver getConfiguredDriver(String browser) {
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new EventFiringWebDriver(new FirefoxDriver());
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.navigate().to("http://prestashop-automation.qatestlab.com.ua/ru/");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
        return driver;
    }

//    @Parameters("browser")
//    @Test(priority = 1,
//        description = "Проверка отображения версии сайта")
//    public void test1(String browser) throws InterruptedException {
//        shopPage = new ShopPage(driver);
//        String version = shopPage.getSiteVersion();
//        System.out.println(version);
//        if(browser.equals("chrome")) {
//            Assert.assertFalse(version.contains("mobile"));
//        }
//    }


    @Parameters("browser")
    @Test(priority = 1,
            description = "Оформление заказа в магазине")
    public void test2(String browser) throws InterruptedException {
        shopPage = new ShopPage(driver);
        cartPage = new CartPage(driver);
        orderPage = new OrderPage(driver);

        // Переходим на страницу продуктов и выбираем случайный товар
        shopPage.goToProducts().openRandomProduct();
        String productName = shopPage.getProductName();
        String productPrice = shopPage.getProductPrice();
        String countInStock = shopPage.getProductStockCount();

        // Добавляем выбранный товар в корзину
        shopPage.addProductToCart();

        // Получаем параметры товара, добавленного в корзину и проводим проверки
        String productNameInCart = cartPage.getProductName();
        String productPriceInCart = cartPage.getProductPrice();
        String productCountInCart = cartPage.getProductCount();
        Assert.assertEquals(productName.toLowerCase(), productNameInCart.toLowerCase());
        Assert.assertEquals(productPrice, productPriceInCart);
        Assert.assertEquals(Integer.parseInt(productCountInCart), 1);

        // Оформляем заказ
        cartPage.createOrder();

        // Проверяем успешность оформления заказа
        orderPage.checkOrderConfirmation();

        // Получаем параметры товара в заказе и проводим проверки
        String productNameInOrder = orderPage.getProductName();
        String productCountInOrder = orderPage.getProductCount();
        String productPriceInOrder = orderPage.getProductPrice();
        Assert.assertTrue(productNameInOrder.toLowerCase().contains(productName.toLowerCase()));
        Assert.assertEquals(productPrice, productPriceInOrder);
        Assert.assertEquals(Integer.parseInt(productCountInOrder), 1);

        // Возвращаемся на главную страницу и проверяем, что количество товара уменьшилось на 1
        orderPage.navigateToShopPage();
        String newCount = shopPage.searchProduct(productName).getProductStockCount();
        Assert.assertEquals(Integer.parseInt(countInStock)-Integer.parseInt(newCount), 1);
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
