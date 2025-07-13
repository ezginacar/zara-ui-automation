package utils;

import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsHelper {
    private SelfHealingDriver driver;
    private CommonHelper commonHelper;
    private WaitHelper waitHelper;

    public void hoverAndClick(By hoverElementLocator, By clickElementLocator) {
        Actions actions = new Actions(driver);
        WebElement hoverElement = waitHelper.waitForElementVisible(hoverElementLocator);
        actions.moveToElement(hoverElement).perform();
        commonHelper.click(clickElementLocator);

    }
}
