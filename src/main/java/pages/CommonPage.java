package pages;

import com.epam.healenium.SelfHealingDriver;
import pageobjects.CommonPageLocators;
import utils.CommonHelper;
import static utils.LoggingUtils.info;

public class CommonPage extends CommonPageLocators {
    private SelfHealingDriver driver;
    private CommonHelper helper;

    public CommonPage(SelfHealingDriver driver) {
        this.driver = driver;
    }

    public CommonPage clickXpathWithText(String text) {
        helper.click(helper.createDynamicXpath(getElementWithTextXpath, text));
        info("Clicked on element with text: " + text);
        return this;
    }

}
