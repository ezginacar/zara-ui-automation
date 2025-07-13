package utils;

import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utils.LoggingUtils.info;

public class WaitHelper {
    private final SelfHealingDriver driver;
    private final WebDriverWait wait;

    public WaitHelper(SelfHealingDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_TIMEOUT_SECONDS));
    }

    public WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementDisappear(By locator) {
        boolean visibility = wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        if (visibility) {
            info("Element disappeared: " + locator.toString());
        } else {
            throw new TimeoutException("Element did not disappear in time: " + locator.toString());
        }

    }



}
