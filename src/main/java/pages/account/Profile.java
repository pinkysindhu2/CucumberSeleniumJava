package pages.account;

import Helper.CustomDriverWaits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.sharemanageskill.ManageListing;
import pages.sharemanageskill.ServiceListing;

import java.util.concurrent.TimeUnit;

public class Profile
{
    protected  WebDriver Driver;

    public Profile(WebDriver driver)
    {
        this.Driver = driver;
        Driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        PageFactory.initElements(this.Driver, this);
    }

    // Initialize First Menu Web Elements after Logged in
    @FindBy(xpath = "//a[contains(text(),'Mars Logo')]")
    protected WebElement marsLogo;
    @FindBy(xpath = "//div[@class='ui secondary menu']//input[@placeholder='Search skills']")
    protected WebElement searchBar;
    @FindBy(xpath = "//div[@class='ui compact menu']/div")
    protected WebElement notification;
    @FindBy(xpath = "//div[@class='ui compact menu']/a[contains(text(),'Chat')]")
    protected WebElement chat;
    @FindBy(xpath = "//div[@class='ui compact menu']/child::span")
    protected WebElement profileName;
    @FindBy(xpath = "//div[@class='ui compact menu']/descendant::button[contains(text(), 'Sign Out')]")
    protected WebElement logout;
    // End of First Menu Web Elements

    //Initialize Second Menu Web Elements
    @FindBy(linkText = "Dashboard")
    protected WebElement dashboard;
    @FindBy(css = "section[class='nav-secondary'] a[href='/Account/Profile']")
    protected WebElement profile;
    @FindBy(linkText = "Manage Listings")
    protected WebElement manageListing;
    @FindBy(linkText = "Manage Requests")
    protected WebElement manageRequest;
    @FindBy(linkText = "Received Requests")
    protected WebElement receivedRequest;
    @FindBy(linkText = "Sent Requests")
    protected WebElement sentRequest;
    @FindBy(linkText = "Share Skill")
    protected WebElement shareSkillBtn;
    //End of First Menu Web Elements

    //Initialize Section Web Elements
    //End


    public void LoginSuccess()
    {
        CustomDriverWaits.waitForPageLoad(Driver,20);
        Assert.assertEquals(Driver.getCurrentUrl(), ("http://192.168.99.100:5000/Account/Profile"));
        Assert.assertEquals(marsLogo.getText(), "Mars Logo");
    }

    public ProfileSettings HoverOnProfileName()
    {
        profileName.click();
        return new ProfileSettings(Driver);
    }

    public ServiceListing ClickOnShareSkillBtn()
    {
        shareSkillBtn.click();
        return new ServiceListing(Driver);
    }

    public ManageListing ClickOnManageListingTab()
    {
        manageListing.click();
        return new ManageListing(Driver);
    }
}

