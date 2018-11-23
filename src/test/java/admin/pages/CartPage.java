package admin.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    By productName = By.xpath("//div[@class='product-line-info']/a");
    By productPrice = By.xpath("(//div[@class='product-line-info']/span[@class='value'])[1]");
    By productCount = By.name("product-quantity-spin");
    By createOrderButton = By.xpath("//a[text()='Оформление заказа']");
    By clientFirstName = By.name("firstname");
    By clientLastName = By.name("lastname");
    By clientEmail = By.name("email");
    By continueButton = By.name("continue");
    By address = By.name("address1");
    By postCode = By.name("postcode");
    By city = By.name("city");
    By deliveryNextButton = By.name("confirm-addresses");
    By deliveryOptionContinue = By.name("confirmDeliveryOption");
    By paymentOption = By.xpath("//input[@id='payment-option-2']");
    By approveCheckbox = By.xpath("//input[contains(@id,'approve')]");
    By confirmationButton = By.xpath("//div[@id='payment-confirmation']//button");

    private final WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductName() {
        String name = driver.findElement(productName).getText();
        return name;
    }

    public String getProductPrice() {
        String price = driver.findElement(productPrice).getText();
        return price;
    }

    public String getProductCount() {
        String count = driver.findElement(productCount).getAttribute("value");
        return count;
    }

    private void fillClientData() {
        driver.findElement(clientFirstName).sendKeys("Тестовый");
        driver.findElement(clientLastName).sendKeys("Клиент");
        driver.findElement(clientEmail).sendKeys("testClient@gmail.com");
        driver.findElement(continueButton).click();
    }

    private void fillDeliveryData() {
        driver.findElement(address).sendKeys("Тестовый адрес");
        driver.findElement(postCode).sendKeys(RandomStringUtils.randomNumeric(5));
        driver.findElement(city).sendKeys("Тестовый город");
        driver.findElement(deliveryNextButton).click();
        driver.findElement(deliveryOptionContinue).click();
    }

    private void fillPaymentData() {
        driver.findElement(paymentOption).click();
        driver.findElement(approveCheckbox).click();
        driver.findElement(confirmationButton).click();
    }

    public void createOrder() {
        driver.findElement(createOrderButton).click();
        fillClientData();
        fillDeliveryData();
        fillPaymentData();
    }
}
