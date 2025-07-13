package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsHelper {
    private WebDriver driver;
    private CommonHelper commonHelper;
    private WaitHelper waitHelper;

    public ActionsHelper(WebDriver driver) {
        this.driver = driver;
        this.commonHelper = new CommonHelper(driver);
        this.waitHelper = new WaitHelper(driver);
    }

    public void hoverAndClick(By hoverElementLocator, By clickElementLocator) {
        Actions actions = new Actions(driver);
        WebElement hoverElement = waitHelper.waitForElementVisible(hoverElementLocator);
        actions.moveToElement(hoverElement).perform();
        commonHelper.click(clickElementLocator);

    }
}
