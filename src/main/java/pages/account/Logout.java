package pages.account;

import org.openqa.selenium.WebDriver;
import pages.home.HomePage;

public class Logout extends Profile {

    public Logout(WebDriver driver) {
        super(driver);
    }
    public HomePage ClickOnLogout()
    {
        logout.click();
        return new HomePage(Driver);
    }
}


