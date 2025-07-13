package pages;

import org.openqa.selenium.WebDriver;
import utils.CommonHelper;

public class SearchPage {
    private WebDriver driver;
    private CommonHelper helper;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        helper = new CommonHelper(driver);
    }

}
