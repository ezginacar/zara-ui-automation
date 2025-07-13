package pages;

import com.epam.healenium.SelfHealingDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import pageobjects.HomePageLocators;
import utils.CommonHelper;
import static utils.LoggingUtils.info;

public class HomePage extends HomePageLocators {
    private SelfHealingDriver driver;
    private CommonHelper helper;

    public HomePage(SelfHealingDriver driver) {
        this.driver = driver;
    }

    //home page actions
    public HomePage clickLoginLinkText() {
        helper.click(loginLinkText);
        info("Login link text clicked");
        return this;
    }

    public HomePage clickToggleMenu() {
        helper.click(toggleMenu);
        info("Hemburger menu clicked");
        return this;
    }

    public HomePage clickGivenCategoryOnTheNavMenu(String category) {
        By by = helper.createDynamicXpath(categoryOnTheNavXpath, category.toUpperCase());
        helper.click(by);
        info("Category '" + category + "' clicked on the navigation menu");
        return this;
    }

    public void validateTheCategoryIsSelected(String category) {
        By by = helper.createDynamicXpath(selectedCategoryOnTheNavXpath, category.toUpperCase());
        Assert.assertTrue("Category '" + category + "' is NOT  selected on the navigation menu", helper.isElementDisplayed(by));
    }

    public void goToGivenCategoryOnTheNavMenu(String category){
        clickToggleMenu().clickGivenCategoryOnTheNavMenu(category).validateTheCategoryIsSelected(category);
    }


}
