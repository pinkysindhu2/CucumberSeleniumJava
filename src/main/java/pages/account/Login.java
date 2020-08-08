package pages.account;


import Helper.CustomDriverWaits;
import Helper.Excel.ExcelHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login
{
    private WebDriver Driver;

    public Login(WebDriver driver){
        this.Driver = driver;
        PageFactory.initElements(this.Driver, this);
        ExcelHelper.populateExcelDataToDataSetModel(System.getProperty("user.dir")+"/src/test/java/TestData/Profile.xlsx", "Login");
    }

    //Initialize Web Elements
    @FindBy(name = "email")
    private WebElement email;
    @FindBy(name = "password")
    private WebElement password;
    @FindBy(xpath = "//button[@class='fluid ui teal button' and contains(text(),'Login')] ")
    private WebElement loginBtn;
    //end

    //login into the website
    public Profile LoginWebsite(String emailId, String pass)
    {
        //ExcelHelper.readData(2,"Password");
        email.sendKeys(emailId);
        password.sendKeys(pass);
        CustomDriverWaits.waitForElementToClickable(loginBtn, Driver, 15);
        CustomDriverWaits.waitForUrl("http://192.168.99.100:5000/Account/Profile", Driver, 10);
        return new Profile(Driver);
        //Assert.AreEqual("Log", "Login");
    }
}

