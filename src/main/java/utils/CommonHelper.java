package utils;

import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.*;

import static utils.LoggingUtils.info;

public class CommonHelper {
    private SelfHealingDriver driver;
    private WaitHelper waitHelper = new WaitHelper(driver);

    public void click(By locator) {
        for (int i = 0; i < Constants.RETRY_COUNT; i++) {
            try {
                WebElement element = driver.findElement(locator);
                element.click();
            } catch (Exception e) {
                try {
                    Thread.sleep(Constants.RETRY_INTERVAL_MS);
                } catch (InterruptedException ignored) {}
            }
        }
    }

    public void clickToBeClickable(By locator){
        waitHelper.waitForElementClickable(locator).click();
    }
    /*
    public void sendKeys(By locator, String text) {
        WebElement element =  waitHelper.waitForElementClickable(locator);
        element.clear();
        element.sendKeys(text);
    }
    */

    public void sendKeys(By locator, String text) {
        try {
            WebElement element =  waitHelper.waitForElementClickable(locator);

            if (element.isDisplayed() && element.isEnabled()) {
                try {
                    element.click();
                } catch (Exception e) {
                    // Some inputs are not clickable but can be typed.
                }

                element.clear();
                element.sendKeys(text);
            } else {
                throw new RuntimeException("The element is visible but inactive: " + locator.toString());
            }

        } catch (TimeoutException e) {
            throw new RuntimeException("The element has expired, is not found, or is unclickable: " + locator.toString());
        } catch (Exception e) {
            throw new RuntimeException("Error during sendKeys: " + e.getMessage());
        }
    }

    public Boolean isElementDisplayed(By locator) {
        try {
            WebElement element = waitHelper.waitForElementVisible(locator);
            boolean isDisplayed = element.isDisplayed();
            info("Element display check '" + locator.toString() + "]': " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            info("Element not displayed: " + locator.toString() + " Reason: " + e.getMessage());
            return false;
        }
    }

    public By createDynamicXpath(String locatorText, String replateItem) {
        locatorText = String.format(locatorText, replateItem);
        return By.xpath(locatorText);
    }

    public String getText(By locator) {
        try {
            WebElement element = waitHelper.waitForElementVisible(locator);
            String text = element.getText();
            info("Text retrieved from element '" + locator.toString() + "': " + text);
            return text;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving text from element: " + locator.toString(), e);
        }
    }

    public void pressKey(WebElement element, Keys key){
        element.sendKeys(key);
        info("Key pressed: " + key.name() + " on element: " + element.toString());
    }

    public String getAttribute(By locator, String attributeName) {
        try {
            WebElement element = waitHelper.waitForElementVisible(locator);
            String attributeValue = element.getAttribute(attributeName);
            info("Attribute '" + attributeName + "' retrieved from element '" + locator.toString() + "': " + attributeValue);
            return attributeValue;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving attribute '" + attributeName + "' from element: " + locator.toString(), e);
        }
    }





}


