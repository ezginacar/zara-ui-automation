package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageobjects.HomePageLocators;
import utils.CommonHelper;
import utils.ExcelUtils;
import utils.WaitHelper;

import java.io.IOException;

import static utils.LoggingUtils.info;

public class HomePage extends HomePageLocators {
    private WebDriver driver;
    private CommonHelper helper;
    private ExcelUtils searchItems;
    private WaitHelper waitHelper;

    public HomePage(WebDriver driver) throws IOException {
        this.driver = driver;
        helper = new CommonHelper(driver);
        searchItems =new ExcelUtils("ZaraUITestDatas.xlsx");
        waitHelper = new WaitHelper(driver);
    }

    //home page actions
    public HomePage clickLoginLinkText() {
        helper.click(loginLinkText);
        info("Login link text clicked");
        return this;
    }

    public HomePage clickToggleMenu() {
        helper.click(toggleMenu);
        info("Hamburger menu clicked");
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
        Assertions.assertTrue(helper.isElementDisplayed(by), "Category '" + category + "' is NOT selected on the navigation menu");    }

    public void goToGivenCategoryOnTheNavMenu(String category){
        clickToggleMenu().clickGivenCategoryOnTheNavMenu(category).validateTheCategoryIsSelected(category);
    }

    public void clickSubcategoryOnTheNewCollectionList(String category){
        By by = helper.createDynamicXpath(subCategoriesOnTheNewCollectionXpath, category);
        helper.click(by);
        info("Subcategory: '" + category + "' clicked on the new collection list");
    }













}
