package Hook;

import base.WebDriverContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hook {
    private WebDriverContext driverContext;

    public Hook(WebDriverContext context){
        this.driverContext = context;
    }

    @Before
    public void before(){
        driverContext.initializeChromeDriver();
    }
    @After
    public void after(){
        driverContext.Driver.quit();
    }

}
