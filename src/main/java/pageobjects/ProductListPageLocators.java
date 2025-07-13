package pageobjects;

import org.openqa.selenium.By;

public class ProductListPageLocators {
    protected By addIconOnTheFirstProductFromSearchResults = By.xpath("(//section//li//button[@data-qa-action='product-grid-open-size-selector'])[1]");
    protected By fistAvailableSize = By.xpath("(//li[contains(@class,'size-selector-sizes-size--enabled')]/button)[1]");
    protected By productNameOfTheFistProduct = By.xpath("(//section[@class='product-grid']//li)[1]//*[contains(@class, 'product-grid-product-info__name')]");
    protected By productPriceOfTheFirstProduct = By.xpath("(//section[@class='product-grid']//li)[1]//*[@class='money-amount__main']");
}
