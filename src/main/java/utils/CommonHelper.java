package utils;

import org.openqa.selenium.*;

import static utils.LoggingUtils.info;

public class CommonHelper {
    private WebDriver driver;
    private WaitHelper waitHelper ;

    public CommonHelper(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
    }

    public void click(By locator) {
        for (int i = 0; i < Constants.RETRY_COUNT; i++) {
            try {
                waitHelper.waitForElementClickable(locator)
                .click();
                break;
            } catch (Exception e) {
                info("Retry " + (i + 1) + " failed for locator: " + locator + " due to: " + e.getMessage());
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
            WebElement element = waitHelper.waitForElementClickable(locator);

            if (element.isDisplayed() && element.isEnabled()) {
                try {
                    element.click();
                } catch (Exception e) {

                }

                // clear input-> Double click + DELETE
                org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
                actions.doubleClick(element).perform();
                element.sendKeys(Keys.DELETE);
                info("Element cleared before sending keys: " + locator.toString());

                // new text
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

    public By createDynamicXpath(String locatorText, String replaceItem) {
        locatorText = String.format(locatorText, replaceItem);
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

    /**
     * Takes a manual screenshot for debugging purposes
     * @param screenshotName Custom name for the screenshot
     * @return Path of the saved screenshot
     */
    public String takeScreenshot(String screenshotName) {
        return ScreenshotUtils.takeManualScreenshot(driver, screenshotName);
    }

    /**
     * Takes a screenshot and throws an exception with the screenshot path
     * Useful for debugging failed assertions
     * @param errorMessage Error message to include
     * @param screenshotName Name for the screenshot
     */
    public void takeScreenshotAndFail(String errorMessage, String screenshotName) {
        String screenshotPath = takeScreenshot(screenshotName);
        String fullMessage = errorMessage + (screenshotPath != null ? " (Screenshot: " + screenshotPath + ")" : "");
        throw new AssertionError(fullMessage);
    }

}


