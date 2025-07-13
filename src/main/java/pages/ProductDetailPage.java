package pages;

import org.openqa.selenium.WebDriver;
import utils.CommonHelper;

public class ProductDetailPage {
    private WebDriver driver;
    private CommonHelper helper;

    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
        helper = new CommonHelper(driver);
    }
}
