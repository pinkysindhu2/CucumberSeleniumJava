package pages.profile;

import Helper.CommonMethods;
import Helper.CustomDriverWaits;
import Helper.Excel.ExcelHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Language extends ProfileSection {
    public Language(WebDriver driver) {
        super(driver);
        ExcelHelper.populateExcelDataToDataSetModel(System.getProperty("user.dir") + "/src/test/java/TestData/Profile.xlsx", "Language");
        PageFactory.initElements(Driver, this);
    }

    //initialize Language elements
    @FindBy(xpath = "//div[@data-tab='first']/descendant::div[@class='fields']")
    private WebElement form;
    @FindBy(xpath = "//div[@data-tab='first']/descendant::table/thead/tr/th[3]/div")
    private WebElement addNew;
    @FindBy(xpath = "//input[@name='name']")
    private WebElement langName;
    @FindBy(xpath = "//div[@data-tab='first']/descendant::select")
    private WebElement langLevel;
    @FindBy(xpath = "//input[@value='Add']")
    private WebElement addLang;
    @FindBy(xpath = "//input[@value='Cancel']")
    private WebElement cancelLang;
    @FindAll({
            @FindBy(xpath = "//div[@data-tab='first']/descendant::table/tbody")
    })
    private List<WebElement> tbody;
    @FindBy(xpath = "//div[@data-tab='first']/descendant::table/tbody[1]/tr/td[3]/span[1]")
    private WebElement editBtn;
    @FindBy(xpath = "//input[@value='Update']")
    private WebElement updateBtn;
    @FindBy(xpath = "//div[@data-tab='first']/descendant::table/tbody[1]/tr/td[3]/span[2]")
    private WebElement deleteBtn;
    //endregion

    public void AddLanguage() {
        CustomDriverWaits.waitForElementToClickable(addNew, Driver, 5);
        CustomDriverWaits.waitForElementToBeDisplayed(langName, Driver, 5);
        langName.sendKeys(ExcelHelper.readData(2, "Language"));
        CustomDriverWaits.waitForElementToBeDisplayed(langLevel, Driver, 5);
        CommonMethods.selectFromDDL(langLevel,ExcelHelper.readData(2, "Level"));
        CustomDriverWaits.waitForElementToClickable(addLang, Driver, 5);
    }

    public boolean AddLangSuccess() {
        return LanguageSuccess(ExcelHelper.readData(2, "Language"), ExcelHelper.readData(2, "Level"));

    }

    //check if 1 or more language is available for edit or delete
    public int ISLangAvailable() {
        //count how many tbody is present into table
        return tbody.size();

    }

    // Edit the First Language
    public void EditLanguage() {
        CustomDriverWaits.waitForElementToClickable(editBtn, Driver, 10);
        CustomDriverWaits.waitForElementToBeDisplayed(langName, Driver, 5);
        langName.clear();
        langName.sendKeys(ExcelHelper.readData(3, "Language"));
        CustomDriverWaits.waitForElementToBeDisplayed(langLevel, Driver, 5);
        CommonMethods.selectFromDDL(langLevel, ExcelHelper.readData(3, "Level"));
        CustomDriverWaits.waitForElementToClickable(updateBtn, Driver, 10);
    }

    public boolean UpdateEditLangSuccess() {
        return LanguageSuccess(ExcelHelper.readData(3, "Language"), ExcelHelper.readData(3, "Level"));
    }

    // Delete the Language
    public void DeleteLanguage() {
        CustomDriverWaits.waitForElementToClickable(deleteBtn, Driver, 5);
    }

    public boolean DeleteLangSuccess() {
        return LanguageSuccess(ExcelHelper.readData(3, "Language"), ExcelHelper.readData(3, "Level"));
    }

    public void CancelLanguage() {
        CustomDriverWaits.waitForElementToClickable(addNew, Driver, 5);
        CustomDriverWaits.waitForElementToClickable(cancelLang, Driver, 5);
    }

    public boolean CancelLangSuccess() {
        return form.isDisplayed() ? true : false;
    }

    private boolean LanguageSuccess(String lang, String level) {
        boolean status = false;
        if (tbody.size() > 0) {
            for (int i = 0; i < tbody.size(); i++) {
                var Lang = tbody.get(i).findElement(By.xpath("//tr/td[1]"));
                var Level = tbody.get(i).findElement(By.xpath("//tr/td[2]"));

                if (Lang.getText().equals(lang) && Level.getText().equals(level)) {
                    status = true;
                    System.out.println("Test Success");
                }
            }
        }
        return status;
    }
}
