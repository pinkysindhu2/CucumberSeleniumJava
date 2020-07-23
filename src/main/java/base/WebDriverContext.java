package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverContext {
    public WebDriver Driver;

    public void setDriver(WebDriver driver) {
        Driver = driver;
    }

    public WebDriver getDriver() {
        return Driver;
    }

    public void initializeChromeDriver(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        Driver = new ChromeDriver();
        Driver.manage().window().maximize();
    }
}
