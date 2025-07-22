package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pageobjects.CommonPageLocators;
import utils.CommonHelper;
import utils.ExcelUtils;
import utils.WaitHelper;

import java.io.IOException;

import static utils.LoggingUtils.info;

public class CommonPage extends CommonPageLocators {
    private WebDriver  driver;
    private CommonHelper helper;
    private WaitHelper waitHelper;
    private ExcelUtils searchItems;



    public CommonPage(WebDriver driver) throws IOException {
        this.driver = driver;
        helper = new CommonHelper(driver);
        waitHelper = new WaitHelper(driver);
        searchItems = new ExcelUtils("ZaraUITestDatas.xlsx");
    }



    public CommonPage fillSearchInputFromExcel(int index) {
        String searchText = searchItems.getCellData("SearchboxTest", index, 2);
        helper.sendKeys(searchInput, searchText);
        info("Search input filled with: " + searchText);
        return this;
    }

    public CommonPage pressEnterToSearching(){
        helper.pressKey(waitHelper.waitForElementVisible(searchInput), Keys.ENTER);
        info("Pressed Enter key to search");
        return this;
    }

    public void acceptCookiesIfExists(){
        if (helper.isElementDisplayed(acceptCookiesButton)) {
            helper.click(acceptCookiesButton);
            info("Cookies accepted");
        } else {
            info("No cookies popup displayed");
        }
    }

    /**
     * Takes a manual screenshot for debugging purposes
     * @param screenshotName Custom name for the screenshot
     * @return Path of the saved screenshot
     */
    public String takeScreenshot(String screenshotName) {
        return helper.takeScreenshot(screenshotName);
    }

    /**
     * Takes a screenshot and throws an exception with the screenshot path
     * @param errorMessage Error message to include
     * @param screenshotName Name for the screenshot
     */
    public void takeScreenshotAndFail(String errorMessage, String screenshotName) {
        helper.takeScreenshotAndFail(errorMessage, screenshotName);
    }

    public CommonPage clickSearchIcon() {
        helper.click(searchIcon);
        info("Search icon clicked");
        return this;
    }


}
