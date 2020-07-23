package pages.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfileSettings extends Profile{

    public ProfileSettings(WebDriver driver) {
        super(driver);
    }
    //Initialize the Web Elements under the Trader Names(Hi Pinky)
    @FindBy(xpath = "//a[contains(text(),'Change Password')]" )
    private WebElement changePassword;
    //end

    public void ChangePassword()
    {
        changePassword.click();
    }
}
