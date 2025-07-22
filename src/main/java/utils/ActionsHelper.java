package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsHelper {
    private WebDriver driver;
    private WaitHelper waitHelper;

    public ActionsHelper(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
    }

    // Fixed: Now accepts By locators for both hover and click
    public void hoverAndClick(By hoverElementLocator, By clickElementLocator) {
        Actions actions = new Actions(driver);
        WebElement hoverElement = waitHelper.waitForElementVisible(hoverElementLocator);
        actions.moveToElement(hoverElement).perform();
        
        // Direct click to avoid circular dependency
        WebElement clickElement = waitHelper.waitForElementClickable(clickElementLocator);
        clickElement.click();
    }

    public void doubleClick(By locator) {
        Actions actions = new Actions(driver);
        WebElement element = waitHelper.waitForElementVisible(locator);
        actions.doubleClick(element).perform();
    }
}
