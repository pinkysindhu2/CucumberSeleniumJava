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

public class Certificate extends ProfileSection {

    public Certificate(WebDriver driver) {
        super(driver);
        ExcelHelper.populateExcelDataToDataSetModel(System.getProperty("user.dir") + "/src/test/java/TestData/Profile.xlsx", "Certification");
        PageFactory.initElements(Driver, this);
    }

    //initialize Certificate web elements
    @FindBy(xpath = "//div[@data-tab='fourth']/descendant::div[@class='fields']")
    private WebElement CertificateForm;
    @FindBy(xpath = "//div[@data-tab='fourth']/descendant::table/thead/tr/th[4]/div")
    private WebElement addNew;
    @FindBy(css = "input[name='certificationName'")
    private WebElement certificateName;
    @FindBy(css = "input[name='certificationFrom']")
    private WebElement certifiedFrom;
    @FindBy(css = "select[name='certificationYear'")
    private WebElement year;
    @FindBy(css = "input[value='Add']")
    private WebElement addCertificate;
    @FindBy(css = "input[value='Cancel']")
    private WebElement cancelCertificate;
    @FindAll({
            @FindBy(xpath = "//div[@data-tab='fourth']/descendant::table/tbody")
    })
    private List<WebElement> tbody;
    @FindBy(xpath = "//div[@data-tab='fourth']/descendant::table/tbody[1]/tr/td[4]/span[1]")
    private WebElement editBtn;
    @FindBy(xpath = "//input[@value='Update']")
    private WebElement updateBtn;
    @FindBy(xpath = "//div[@data-tab='fourth']/descendant::table/tbody[1]/tr/td[4]/span[2]")
    private WebElement deleteBtn;
    //end

    public void AddCertificate() {
        CustomDriverWaits.waitForElementToClickable(addNew, Driver, 5);
        CustomDriverWaits.waitForElementToBeDisplayed(certificateName, Driver, 5);
        certificateName.sendKeys(ExcelHelper.readData(2, "Certification/Award Name"));
        CustomDriverWaits.waitForElementToBeDisplayed(certifiedFrom, Driver, 5);
        certifiedFrom.sendKeys(ExcelHelper.readData(2, "Certified From"));
        CustomDriverWaits.waitForElementToBeDisplayed(year, Driver,5);
        CommonMethods.selectFromDDL(year, ExcelHelper.readData(2, "Year"));
        CustomDriverWaits.waitForElementToClickable(addCertificate, Driver, 5);
    }

    public boolean AddCertificateSuccess() {
        return CertificateSuccess(ExcelHelper.readData(2, "Certification/Award Name"),
                ExcelHelper.readData(2, "Certified From"),
                ExcelHelper.readData(2, "Year"));
    }

    //check if 1 or more Certificate is available for edit or delete
    public int IsCertificateAvailable() {
        //count how many tbody is present into table
        CustomDriverWaits.waitForElementsToBeDisplayed(tbody, Driver, 10);
        return tbody.size();
    }

    // Edit the First Education
    public void UpdateCertificate() {
        CustomDriverWaits.waitForElementToClickable(editBtn, Driver, 10);
        CustomDriverWaits.waitForElementToBeDisplayed(certificateName, Driver, 5);
        certificateName.clear();
        certificateName.sendKeys(ExcelHelper.readData(3, "Certification/Award Name"));
        CustomDriverWaits.waitForElementToBeDisplayed(certifiedFrom, Driver, 5);
        certifiedFrom.clear();
        certifiedFrom.sendKeys(ExcelHelper.readData(3, "Certified From"));
        CustomDriverWaits.waitForElementToBeDisplayed(year, Driver, 5);
        CommonMethods.selectFromDDL(year, ExcelHelper.readData(3, "Year"));
        CustomDriverWaits.waitForElementToClickable(updateBtn, Driver, 10);
    }

    public boolean UpdateCertificateSuccess() {
        return CertificateSuccess(ExcelHelper.readData(3, "Certification/Award Name"),
                ExcelHelper.readData(3, "Certified From"),
                ExcelHelper.readData(3, "Year"));
    }

    public void DeleteCertificate() {
        CustomDriverWaits.waitForElementToClickable(deleteBtn, Driver, 10);
    }

    public boolean DeleteCertificateSuccess() {
        return CertificateSuccess(ExcelHelper.readData(3, "Certification/Award Name"),
                ExcelHelper.readData(3, "Certified From"),
                ExcelHelper.readData(3, "Year"));
    }

    public void CancelCertificate() {
        CustomDriverWaits.waitForElementToClickable(addNew, Driver, 10);
        CustomDriverWaits.waitForElementToClickable(cancelCertificate, Driver, 10);
    }

    public boolean CancelCertificateSuccess() {
        return CertificateForm.isDisplayed() ? true : false;
    }

    private boolean CertificateSuccess(String name, String from, String year) {
        CustomDriverWaits.waitForElementsToBeDisplayed(tbody, Driver, 10);
        boolean status = false;
        if (tbody.size() > 0) {
            for (int i = 0; i < tbody.size(); i++) {
                var Name = tbody.get(i).findElement(By.xpath("//tr/td[1]"));
                var From = tbody.get(i).findElement(By.xpath("//tr/td[2]"));
                var Year = tbody.get(i).findElement(By.xpath("//tr/td[3]"));


                if (Name.getText().equals(name) && From.getText().equals(from) && Year.getText().equals(year)) {
                    status = true;
                    System.out.println("Test Success");
                }
            }
        }
        return status;
    }
}
