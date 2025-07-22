package pageobjects;

import org.openqa.selenium.By;

public class ProductListPageLocators {
    protected By addIconOnTheFirstProductFromSearchResults = By.xpath("(//section//li//button[@data-qa-action='product-grid-open-size-selector'])[1]");
    protected By fistAvailableSize = By.xpath("(//li[contains(@class,'size-selector-sizes-size--enabled')]/button)[1]");
    protected By productNameOfTheFistProduct = By.cssSelector("section li:first-child [class*='product-info__name'] h3");
    protected By productPriceOfTheFirstProduct = By.cssSelector("section li:first-child [class*='price'], section li:first-child [class*='money']");

    protected By addCardNotificationSecondaryButton = By.cssSelector("button[class*='add-to-cart-notification-content']");
}
