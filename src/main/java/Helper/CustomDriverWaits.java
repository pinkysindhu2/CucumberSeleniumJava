package Helper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public static void waitForElementToClick(WebDriver driver, WebElement ele, int timeOut){
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.elementToBeClickable(ele));
        ele.click();
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

}

