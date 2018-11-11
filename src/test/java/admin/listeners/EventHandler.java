package admin.listeners;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class EventHandler implements WebDriverEventListener{

    public void beforeAlertAccept(WebDriver webDriver) {
    }

    public void afterAlertAccept(WebDriver webDriver) {
    }

    public void afterAlertDismiss(WebDriver webDriver) {
    }

    public void beforeAlertDismiss(WebDriver webDriver) {
    }

    public void beforeNavigateTo(String url, WebDriver webDriver) {
        System.out.println("Переходим по адресу " + url);
    }

    public void afterNavigateTo(String url, WebDriver webDriver) {
        System.out.println("Текущее значение адресса страницы: " + url);
    }

    public void beforeNavigateBack(WebDriver webDriver) {
        System.out.println("Возвращаемся на предыдущую страницу.");
    }

    public void afterNavigateBack(WebDriver webDriver) {
        System.out.println("Переход на предыдущую страницу выполнен. Текущая страница: " + webDriver.getTitle());
    }

    public void beforeNavigateForward(WebDriver webDriver) {
    }

    public void afterNavigateForward(WebDriver webDriver) {
    }

    public void beforeNavigateRefresh(WebDriver webDriver) {
        System.out.println("Обновляем страницу...");
    }

    public void afterNavigateRefresh(WebDriver webDriver) {
    }

    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        System.out.println("Ищем елемент на странице " + by.toString());
    }

    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
        System.out.println("Нашли элемент " +by.toString());
    }

    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        System.out.println("Кликаем по элементу " + elementDescription(webElement) + " ('" + webElement.getText() + "')");
    }

    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        System.out.println("Клик по элементу успешно выполнен");
    }

    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
    }

    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        System.out.println("Текущее значение элемента \"" +
                elementDescription(webElement) +
                "\" - " +
                webElement.getAttribute("value"));
    }

    public void beforeScript(String s, WebDriver webDriver) {
    }

    public void afterScript(String s, WebDriver webDriver) {
    }

    public void beforeSwitchToWindow(String window, WebDriver webDriver) {
    }

    public void afterSwitchToWindow(String window, WebDriver webDriver) {
    }

    public void onException(Throwable throwable, WebDriver webDriver) {
    }

    public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {
    }

    public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x) {
    }

    public void beforeGetText(WebElement webElement, WebDriver webDriver) {
    }

    public void afterGetText(WebElement webElement, WebDriver webDriver, String text) {
    }

    private String elementDescription(WebElement element) {
        String description = "tag: " + element.getTagName();
        if (element.getAttribute("id") != null) {
            description += " id: " + element.getAttribute("id");
        }
        else if (element.getAttribute("name") != null) {
            description += " name: " + element.getAttribute("name");
        }
        return description;
    }
}
