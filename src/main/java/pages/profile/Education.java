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

public class Education extends ProfileSection{

    public Education(WebDriver driver) {
        super(driver);
        ExcelHelper.populateExcelDataToDataSetModel(System.getProperty("user.dir") + "/src/test/java/TestData/Profile.xlsx", "Education");
        PageFactory.initElements(Driver, this);
    }
    // Initialize the web elements
    @FindBy(xpath = "//div[@data-tab='third']/descendant::div[@class='fields']")
    private WebElement eduForm;
    @FindBy(xpath = "//div[@data-tab='third']/descendant::table/thead/tr/th[6]/div")
    private WebElement addNew;
    @FindBy(css = "input[name='instituteName']")
    private WebElement uniName;
    @FindBy(css = "select[name='country']")
    private WebElement country;
    @FindBy(css = "select[name='title']")
    private WebElement title;
    @FindBy(css = "input[name='degree']")
    private WebElement degree;
    @FindBy(css = "select[name='yearOfGraduation']")
    private WebElement year;
    @FindBy(css = "input[value='Add']")
    private WebElement addEdu;
    @FindBy(css = "input[value='Cancel']")
    private WebElement cancelEdu;
    @FindAll({
            @FindBy(xpath = "//div[@data-tab='third']/descendant::table/tbody")
    })
    private List<WebElement> tbody;
    @FindBy(xpath = "//div[@data-tab='third']/descendant::table/tbody[1]/tr/td[6]/span[1]")
    private WebElement editBtn;
    @FindBy(xpath = "//input[@value='Update']")
    private WebElement updateBtn;
    @FindBy(xpath = "//div[@data-tab='third']/descendant::table/tbody[1]/tr/td[6]/span[2]")
    private WebElement deleteBtn;
    //end

    public void AddEducation()
    {
        CustomDriverWaits.waitForElementToClickable(addNew, Driver, 10);

        CustomDriverWaits.waitForElementToBeDisplayed(uniName, Driver, 4);
        uniName.sendKeys(ExcelHelper.readData(2, "University"));

        CustomDriverWaits.waitForElementToBeDisplayed(country, Driver, 4);
        CommonMethods.selectFromDDL(country,ExcelHelper.readData(2,"Country"));

        CustomDriverWaits.waitForElementToBeDisplayed(title, Driver, 4);
        CommonMethods.selectFromDDL(title,ExcelHelper.readData(2, "Title"));

        CustomDriverWaits.waitForElementToBeDisplayed(degree, Driver, 4);
        degree.sendKeys(ExcelHelper.readData(2,"Degree"));

        CustomDriverWaits.waitForElementToBeDisplayed(year, Driver, 4);
        CommonMethods.selectFromDDL(year, ExcelHelper.readData(2, "Graduated"));

        CustomDriverWaits.waitForElementToClickable(addEdu, Driver, 10);
    }

    public boolean AddEducationSuccess()
    {
        return EducationSuccess(ExcelHelper.readData(2, "University"),
                ExcelHelper.readData(2, "Country"),
                ExcelHelper.readData(2, "Title"),
                ExcelHelper.readData(2, "Degree"),
                ExcelHelper.readData(2, "Graduated"));
    }

    //check if 1 or more language is available for edit or delete
    public int IsEducationAvailable()
    {
        //count how many tbody is present into table
        return tbody.size();

    }
    // Edit the First Education
    public void UpdateEducation()
    {
        CustomDriverWaits.waitForElementToClickable(editBtn, Driver, 10);

        CustomDriverWaits.waitForElementToBeDisplayed(uniName, Driver, 4);
        uniName.clear();
        uniName.sendKeys(ExcelHelper.readData(3, "University"));


        CustomDriverWaits.waitForElementToBeDisplayed(country, Driver, 4);
        CommonMethods.selectFromDDL(country,ExcelHelper.readData(3,"Country"));

        CustomDriverWaits.waitForElementToBeDisplayed(title, Driver, 4);
        CommonMethods.selectFromDDL(title,ExcelHelper.readData(3, "Title"));

        CustomDriverWaits.waitForElementToBeDisplayed(degree, Driver, 4);
        degree.clear();
        degree.sendKeys(ExcelHelper.readData(3,"Degree"));

        CustomDriverWaits.waitForElementToBeDisplayed(year, Driver, 4);
        CommonMethods.selectFromDDL(year, ExcelHelper.readData(3, "Graduated"));

        CustomDriverWaits.waitForElementToClickable(updateBtn, Driver, 10);
    }

    public boolean UpdateEducationSuccess()
    {
        return EducationSuccess(ExcelHelper.readData(3, "University"),
                ExcelHelper.readData(3, "Country"),
                ExcelHelper.readData(3, "Title"),
                ExcelHelper.readData(3, "Degree"),
                ExcelHelper.readData(3, "Graduated"));
    }

    public void DeleteEducation()
    {
        CustomDriverWaits.waitForElementToClickable(deleteBtn, Driver, 10);
    }

    public boolean DeleteEducationSuccess()
    {
        return EducationSuccess(ExcelHelper.readData(3, "University"),
                ExcelHelper.readData(3, "Country"),
                ExcelHelper.readData(3, "Title"),
                ExcelHelper.readData(3, "Degree"),
                ExcelHelper.readData(3, "Graduated"));
    }

    public void CancelEducation()
    {
        CustomDriverWaits.waitForElementToClickable(addNew, Driver, 10);

        CustomDriverWaits.waitForElementToClickable(cancelEdu, Driver, 10);
    }
    public boolean CancelEducationSuccess()
    {
        return eduForm.isDisplayed() ? true : false;
    }
    private boolean EducationSuccess(String uni, String country, String title, String degree, String year)
    {
        boolean status = false;
        if (tbody.size() > 0)
        {
            for (int i = 0; i < tbody.size(); i++)
            {
                var Country = tbody.get(i).findElement(By.xpath("//tr/td[1]"));
                var Uni = tbody.get(i).findElement(By.xpath("//tr/td[2]"));
                var Title = tbody.get(i).findElement(By.xpath("//tr/td[3]"));
                var Degree = tbody.get(i).findElement(By.xpath("//tr/td[4]"));
                var Year = tbody.get(i).findElement(By.xpath("//tr/td[5]"));

                if (Uni.getText().equals(uni) && Country.getText().equals(country) &&
                        Title.getText().equals(title) && Degree.getText().equals(degree) &&
                        Year.getText().equals(year))
                {
                    status = true;
                    System.out.println("Test Success");
                }
            }
        }
        return status;
    }
}
