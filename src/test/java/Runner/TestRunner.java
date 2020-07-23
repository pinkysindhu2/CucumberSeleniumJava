package Runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = {"src/test/java/Feature/Account/Login.feature"},
        glue = {"Hook", "Steps"},
        monochrome = true,
        plugin = {"html:target/HTMLReport/cucumber", "json:target/JSONReport/cucumber.json",
               "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
