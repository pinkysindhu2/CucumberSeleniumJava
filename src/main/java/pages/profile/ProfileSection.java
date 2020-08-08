package pages.profile;

import Helper.CommonMethods;
import Helper.CustomDriverWaits;
import Helper.Excel.ExcelHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.account.Profile;

/*
  This class represents the Content Area of User's Profile where user can add, update, delete
  the information regarding his profile eg lang, skill, edu, certificates, hours, upload image,
  description, availability and hours
*/
public class ProfileSection extends Profile {

    public ProfileSection(WebDriver driver) {
        super(driver);
        ExcelHelper.populateExcelDataToDataSetModel(System.getProperty("user.dir") + "/src/test/java/TestData/Profile.xlsx", "Profile");
        PageFactory.initElements(Driver, this);
    }

    //Initialize the User Details Web elements
    @FindBy(css = ".user-details .image")
    private WebElement profileImg;
    @FindBy(className = "title")
    private WebElement editName;
    @FindBy(css = "input[name='firstName']")
    private WebElement firstName;
    @FindBy(css = "input[name='lastName']")
    private WebElement lastName;

    //this is Save btn for saving your name
    @FindBy(xpath = "//div[@class='user-details']/descendant::div[@class='content']/descendant::button" )
    private WebElement updateNameBtn;
    @FindBy(xpath = "//div[@class='user-details']/descendant::div[@class='extra content']/div/div[2]/div/span")
    private WebElement availableTime;
    @FindBy(xpath = "//div[@class='user-details']/descendant::div[@class='extra content']/div/div[2]/div/span/i")
    private WebElement editAvailability;
    @FindBy(xpath = "//div[@class='user-details']/descendant::div[@class='extra content']/div/div[2]/div/span/select")
    private WebElement availabilityDDL;
    @FindBy(xpath = "//div[@class='user-details']/descendant::div[@class='extra content']/div/div[3]/div/span")
    private WebElement availableHours;
    @FindBy(xpath = "//div[@class='user-details']/descendant::div[@class='extra content']/div/div[3]/div/span/i")
    private WebElement editHours;
    @FindBy(xpath = "//div[@class='user-details']/descendant::div[@class='extra content']/div/div[3]/div/span/select")
    private WebElement hoursDDL;
    @FindBy(xpath = "//div[@class='user-details']/descendant::div[@class='extra content']/div/div[4]/div/span")
    private WebElement availableEarnings;
    @FindBy(xpath = "//div[@class='user-details']/descendant::div[@class='extra content']/div/div[4]/div/span/i")
    private WebElement editEarns;
    @FindBy(xpath = "//div[@class='user-details']/descendant::div[@class='extra content']/div/div[4]/div/span/select")
    private WebElement earnsDDL;
    //end

    //Initialize web elements for Description
    @FindBy(xpath = "//div[@id='account-profile-section']/div/section[2]/div/div/div/div[3]/div/div/div/h3/span")
    protected WebElement description;
    //end

    // Language Tab
    @FindBy(css = "a[data-tab='first']")
    protected WebElement langTab;
    //end

    //Skill Tab
    @FindBy(css = "a[data-tab='second']")
    protected WebElement skillTab;
    //end

    // Education Tab
    @FindBy(css = "a[data-tab='third']")
    protected WebElement eduTab;
    //end

    // Certificate Tab
    @FindBy(css = "a[data-tab='fourth']")
    protected WebElement certificateTab;
    //end

    public void UpdateAvailability() {
        editAvailability.click();
        // Add to wait
        CommonMethods.selectFromDDL(availabilityDDL, ExcelHelper.readData(2, "Available Time"));
    }

    public boolean AvailableTimeSuccess() {
        // Add wait
        return availableTime.getText().equals(ExcelHelper.readData(2, "Available Time")) ? true : false;
    }

    public void UpdateHours() {
        editHours.click();
        CustomDriverWaits.waitForElementToBeDisplayed(hoursDDL,Driver,10);
        CommonMethods.selectFromDDL(hoursDDL,ExcelHelper.readData(2, "Hours"));
    }

    public boolean AvailableHoursSuccess() {
        CustomDriverWaits.waitForElementToBeDisplayed(availableHours, Driver, 5);
        return availableHours.getText().equals(ExcelHelper.readData(2, "Hours")) ? true : false;
    }

    public void UpdateEarnTarget() {
        CustomDriverWaits.waitForElementToClickable(editEarns, Driver, 5);
        CustomDriverWaits.waitForElementToBeDisplayed(earnsDDL, Driver, 5);
        CommonMethods.selectFromDDL(earnsDDL,ExcelHelper.readData(2, "Earn Target"));
    }

    public boolean EarnTargetSuccess() {
        CustomDriverWaits.waitForElementToBeDisplayed(availableEarnings, Driver, 5);
        return availableEarnings.getText() == ExcelHelper.readData(2, "Earn Target") ? true : false;
    }

    public Language ClickOnLanguageTab() {
        CustomDriverWaits.waitForElementToClickable(langTab, Driver, 10);
        return new Language(Driver);
    }

    public Skill ClickOnSkillTab() {
        CustomDriverWaits.waitForElementToClickable(skillTab, Driver, 10);
        return new Skill(Driver);
    }

    public Education ClickOnEducationTab() {
        CustomDriverWaits.waitForElementToClickable(eduTab, Driver, 10);
        return new Education(Driver);
    }

    public Certificate ClickOnCertificateTab() {
        CustomDriverWaits.waitForElementToClickable(certificateTab, Driver, 10);
        return new Certificate(Driver);
    }

    public Description ClickOnDescription() {
        CustomDriverWaits.waitForElementToClickable(description, Driver, 10);
        return new Description(Driver);
    }
}
