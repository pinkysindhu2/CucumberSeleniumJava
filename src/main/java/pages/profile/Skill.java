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

public class Skill extends ProfileSection {

    public Skill(WebDriver driver){
        super(driver);
        ExcelHelper.populateExcelDataToDataSetModel(System.getProperty("user.dir") + "/src/test/java/TestData/Profile.xlsx", "Skill");
        PageFactory.initElements(Driver, this);
    }
    //initialize Skill web elements
    @FindBy(xpath = "//div[@data-tab='second']/descendant::div[@class='fields']")
    private WebElement skillForm;
    @FindBy(xpath = "//div[@data-tab='second']/descendant::table/thead/tr/th[3]/div")
    private WebElement addNew;
    @FindBy(xpath = "//input[@name='name']")
    private WebElement skillName;
    @FindBy(xpath = "//div[@data-tab='second']/descendant::select")
    private WebElement skillLevel;
    @FindBy(css = "input[value='Add']")
    private WebElement addSkill;
    @FindAll({
            @FindBy(xpath = "//div[@data-tab='second']/descendant::table/tbody")
    })
    private List<WebElement> tbody;
    @FindBy(css = "input[value='Cancel']")
    private WebElement cancelSkill;
    @FindBy(xpath = "//div[@data-tab='second']/descendant::table/tbody[1]/tr/td[3]/span[1]")
    private WebElement editBtn;
    @FindBy(xpath = "//input[@value='Update']")
    private WebElement updateBtn;
    @FindBy(xpath = "//div[@data-tab='second']/descendant::table/tbody[1]/tr/td[3]/span[2]")
    private WebElement deleteBtn;
    //end

    public void AddSkill() {
        CustomDriverWaits.waitForElementToClickable(addNew, Driver, 5);
        CustomDriverWaits.waitForElementToBeDisplayed(skillName, Driver, 5);
        skillName.sendKeys(ExcelHelper.readData(2, "Skill"));
        CustomDriverWaits.waitForElementToBeDisplayed(skillLevel, Driver, 5);
        CommonMethods.selectFromDDL(skillLevel, ExcelHelper.readData(2, "Level"));
        CustomDriverWaits.waitForElementToClickable(addSkill, Driver, 10);
    }

    public boolean AddSkillSuccess() {
        return SkillSuccess(ExcelHelper.readData(2, "Skill"), ExcelHelper.readData(2, "Level"));

    }

    //check if 1 or more Skill is available for edit or delete
    public int ISSkillAvailable() {     //count how many tbody is present into table
        CustomDriverWaits.waitForElementsToBeDisplayed(tbody, Driver, 10);
        return tbody.size();
    }

    // Edit the First Skill
    public void EditSkill() {
        CustomDriverWaits.waitForElementToClickable(editBtn, Driver, 10);
        CustomDriverWaits.waitForElementToBeDisplayed(skillName, Driver, 10);
        skillName.clear();
        skillName.sendKeys(ExcelHelper.readData(3, "Skill"));
        CustomDriverWaits.waitForElementToBeDisplayed(skillLevel, Driver, 10);
        CommonMethods.selectFromDDL(skillLevel, ExcelHelper.readData(3, "Level"));
        CustomDriverWaits.waitForElementToClickable(updateBtn, Driver, 10);
    }

    public boolean UpdateEditSkilllSuccess() {
        return SkillSuccess(ExcelHelper.readData(3, "Skill"), ExcelHelper.readData(3, "Level"));
    }

    // Delete the Skill
    public void DeleteSkill() {
        CustomDriverWaits.waitForElementToClickable(deleteBtn, Driver, 10);
    }

    public boolean DeleteSkillSuccess() {
        return SkillSuccess(ExcelHelper.readData(3, "Skill"), ExcelHelper.readData(3, "Level"));
    }

    public void CancelSkill() {
        CustomDriverWaits.waitForElementToClickable(addNew, Driver, 10);
        CustomDriverWaits.waitForElementToClickable(cancelSkill, Driver, 10);
    }

    public boolean CancelSkillSuccess() {
        return skillForm.isDisplayed() ? true : false;
    }

    private boolean SkillSuccess(String lang, String level) {
        boolean status = false;
        CustomDriverWaits.waitForElementsToBeDisplayed(tbody, Driver, 10);
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
