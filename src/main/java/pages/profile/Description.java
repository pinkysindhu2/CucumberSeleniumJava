package pages.profile;


import Helper.CustomDriverWaits;
import Helper.Excel.ExcelHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Description extends ProfileSection {

    public Description(WebDriver driver){
        super(driver);
        ExcelHelper.populateExcelDataToDataSetModel(System.getProperty("user.dir") + "/src/test/java/TestData/Profile.xlsx", "Description");
        PageFactory.initElements(Driver, this);
    }

    //initialize Description web elements
    @FindBy(tagName = "textarea")
    private WebElement textAreaDes;

    @FindBy(xpath = "//button[@class='ui teal button' and @type='button']")
    private WebElement save;

    @FindBy(xpath = "//div[@id='account-profile-section']/div/section[2]/div/div/div/div[3]/div/div/div/span")
    private WebElement shtDescription;
    //end

    public void AddDescription() {
        CustomDriverWaits.waitForElementToBeDisplayed(textAreaDes, Driver, 10);
        textAreaDes.clear();
        textAreaDes.sendKeys(ExcelHelper.readData(2, "A short Description"));
        CustomDriverWaits.waitForElementToClickable(save, Driver, 5);
    }

    public boolean IsDescriptionSaved() {
        CustomDriverWaits.waitForElementToBeDisplayed(shtDescription, Driver, 10);
        return shtDescription.getText().equals(ExcelHelper.readData(2, "A short Description")) ? true : false;
    }

    public void UpdateDescription() {
        CustomDriverWaits.waitForElementToBeDisplayed(textAreaDes, Driver, 10);
        textAreaDes.clear();
        textAreaDes.sendKeys(ExcelHelper.readData(2, "A short Description"));
        CustomDriverWaits.waitForElementToClickable(save, Driver, 5);
    }

    public boolean IsDescriptionUpdated() {
        CustomDriverWaits.waitForElementToBeDisplayed(shtDescription, Driver, 10);
        return shtDescription.getText().equals(ExcelHelper.readData(2, "Update Description")) ? true : false;
    }
}


