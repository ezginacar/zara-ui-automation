package pageobjects;

import org.openqa.selenium.By;

public class CommonPageLocators  {
    public String getElementWithTextXpath = "//*[contains(text(),'%s')]";
    protected By searchInput = By.className("layout-header-action-search__content");
    protected By acceptCookiesButton = By.id("onetrust-accept-btn-handler");

}
