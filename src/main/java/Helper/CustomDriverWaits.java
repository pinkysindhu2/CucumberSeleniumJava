package Helper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CustomDriverWaits {

    public static int timeOut = 30;

    public static void waitForPageLoad(WebDriver driver, int timeOut){
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(
                d -> String.valueOf(((JavascriptExecutor)d)
                        .executeScript("return document.readyState"))
                        .equals("complete")
        );
    }

    public static void implicitWait(WebDriver Driver){
        Driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }
    public static void waitForUrl(String url, WebDriver driver, int timeOuteout) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        ExpectedCondition<Boolean> urlIsCorrect = arg0 -> driver.getCurrentUrl().equals(url);
        wait.until(urlIsCorrect);
        waitForPageLoad(driver, timeOut);
    }
    public static void pageLoad(WebDriver Driver){
        Driver.manage().timeouts().pageLoadTimeout(timeOut, TimeUnit.SECONDS);
    }
    public static void fluentWait(WebDriver driver) {
        Wait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(4))
                .ignoring(Exception.class);
    }
    public static void waitForElementToBeDisplayed(WebElement element, WebDriver driver, int specifiedTimeout) {
        WebDriverWait wait = new WebDriverWait(driver, specifiedTimeout);
        ExpectedCondition<Boolean> elementIsDisplayed = arg0 -> element.isDisplayed();
        wait.until(elementIsDisplayed);
    }

    public static void waitForElementsToBeDisplayed(List<WebElement> element, WebDriver driver, int specifiedTimeout) {
        WebDriverWait wait = new WebDriverWait(driver, specifiedTimeout);
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public static void waitForElementToClickable(WebElement ele, WebDriver driver,  int timeOut){
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.elementToBeClickable(ele));
        ele.click();
    }

}

