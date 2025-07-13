package pageobjects;

import org.openqa.selenium.By;

public class HomePageLocators {

    protected By loginLinkText = By.xpath("//*[contains(@data-qa-id ,'user-logon')]");
    protected By toggleMenu = By.cssSelector("button[data-qa-id='layout-header-toggle-menu']");

    protected String categoryOnTheNavXpath = "//nav//*[@class='layout-categories-category__name' and normalize-space(text())='%s']";
    protected String selectedCategoryOnTheNavXpath = "//a[@aria-expanded='true']/span[normalize-space(text())='%s']";



}
