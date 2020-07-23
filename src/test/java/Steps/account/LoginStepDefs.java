package Steps.account;

import Helper.CustomDriverWaits;
import base.WebDriverContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.account.Login;
import pages.account.Profile;
import pages.home.HomePage;

public class LoginStepDefs{

    private WebDriver Driver;
    private Login login;
    private Profile profile;

    public LoginStepDefs(WebDriverContext context){
        Driver =  context.getDriver();
    }

    @Given("I visit to Home page")
    public void iVisitToHomePage(){
        Driver.navigate().to("http://192.168.99.100:5000/");
    }

    @And("I click on SigIn button")
    public void iClickOnSigInButton() {
        HomePage home = new  HomePage(Driver);
        login = home.ClickOnSignInBtn();
    }

    @When("^I enter (.*) and (.*)$")
    public void iEnterAnd(String Email, String Password) {
       profile = login.LoginWebsite(Email, Password);
    }

    @Then("I should be in Profile page")
    public void iShouldBeInProfilePage() {
        System.out.println("Step");
        CustomDriverWaits.waitForPageLoad(Driver,20);
        profile.LoginSuccess();
    }


}
