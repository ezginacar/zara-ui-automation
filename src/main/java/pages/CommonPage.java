package pages;

import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pageobjects.CommonPageLocators;
import utils.CommonHelper;
import utils.ExcelUtils;
import utils.WaitHelper;

import java.io.IOException;

import static utils.LoggingUtils.info;

public class CommonPage extends CommonPageLocators {
    private SelfHealingDriver driver;
    private CommonHelper helper;
    private WaitHelper waitHelper;
    private ExcelUtils searchItems = new ExcelUtils("ZaraUITestDatas.xlsx");



    public CommonPage(SelfHealingDriver driver) throws IOException {
        this.driver = driver;
    }

    public CommonPage clickXpathWithText(String text) {
        helper.click(helper.createDynamicXpath(getElementWithTextXpath, text));
        info("Clicked on element with text: " + text);
        return this;
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


}
