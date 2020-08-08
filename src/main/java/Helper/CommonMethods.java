package Helper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CommonMethods {

    public static void selectFromDDL(WebElement ele, String name){
        Select select = new Select(ele);
        select.selectByValue(name);
    }
}
