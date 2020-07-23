package pages.home;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.account.Login;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
    protected WebDriver Driver;

    public HomePage(WebDriver driver)
    {
        this.Driver = driver;
        PageFactory.initElements(this.Driver, this);
    }

    //Initialize WebElements for First_Section on Home page
    @FindBy(xpath = "//a[contains(text(),'Sign')]")
    protected WebElement signInTab;
    @FindBy(xpath= "//button[contains(text(),'Join')]")
    protected WebElement joinTab;
    @FindBy(xpath = "//div[@class='main-search']/descendant::input")
    protected WebElement searchTextBox;
    @FindBy(xpath = "//div[@class='main-search']/descendant::button")
    protected WebElement searchBtn;
    // End of WebEelement for first section

    //Initialize WebElements for Explore Section on Home page
    @FindBy(xpath = "//h3[contains(text(),'Explore categories>')]")
    protected WebElement exploreCategory;
    @FindBy(xpath = "//div[@class='ui grid explore-category']//div[1]//div[1]")
    protected WebElement graphicDesign;
    @FindBy(xpath = "//div[@class='ui grid explore-category']//div[1]//div[2]")
    protected WebElement digitalMarketing;
    @FindBy(xpath = "//div[@class='ui grid explore-category']//div[1]//div[3]")
    protected WebElement writtingTranslation;
    @FindBy(xpath = "//div[@class='ui grid explore-category']//div[1]//div[4]")
    protected WebElement videoAnimation;
    @FindBy(xpath = "//div[@class='ui grid explore-category']//div[2]//div[1]")
    protected WebElement musicAudio ;
    @FindBy(xpath = "//div[@class='ui grid explore-category']//div[2]//div[2]")
    protected WebElement progTech;
    @FindBy(xpath = "//div[@class='ui grid explore-category']//div[2]//div[3]")
    protected WebElement business;
    @FindBy(xpath = "//div[@class='ui grid explore-category']//div[2]//div[4]")
    protected WebElement funLifestyle;
    //End of WebElements for Explore Section

    // Initialize WebElements for Suggestions_Section on Home page
    //end
    //Initialize WebElements for Visions_Section on Home page
    //end
    //Initialize WebElements for GuideLines_Section on Home page
    //end
    //Initialize WebElements for Category_Section on Home page
    //end

    public Login ClickOnSignInBtn()
    {
        signInTab.click();
        return new Login(Driver);
    }

    public void LogoutSuccess()
    {
        Assert.assertEquals(signInTab.isDisplayed(), true);
        Assert.assertEquals(Driver.getCurrentUrl(), "http://192.168.99.100:5000/Home");
    }
}
