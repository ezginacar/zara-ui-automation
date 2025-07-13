package pageobjects;

import org.openqa.selenium.By;

public class BasketPageLocators {
    protected By basketCardQuantity = By.cssSelector("[data-qa-id='layout-header-go-to-cart-items-count']");
    protected By basketLinkText = By.cssSelector("[data-qa-id='layout-header-go-to-cart']");
    protected By cartViewContent  = By.className("shop-cart-view__content");
    protected By anyProductInCart = By.xpath("(//li[@class='shop-cart-item shop-cart-item--is-vertical'])[1]");
    protected By removeItemIcon = By.className("shop-cart-item-actions__remove-item-icon");
    protected By singleProductNameInCart = By.xpath("//ul/li[contains(@class, 'shop-cart-item')]//*[@class='shop-cart-item-header__description--product-name']/a");
    protected By singleProductPriceInCart = By.xpath("//ul/li[contains(@class, 'shop-cart-item')]//*[@class='money-amount__main']");
    protected By singleProductIncreaseButton = By.xpath("//ul/li[contains(@class, 'shop-cart-item')]//*[@data-qa-id='add-order-item-unit']");
    protected By singleProductTotalAmount = By.xpath("//ul/li[contains(@class, 'shop-cart-item')]//*[@class='zds-quantity-selector']/input");




}
