package pageobjects;

import org.openqa.selenium.By;

public class BasketPageLocators {
    protected By basketCardQuantity = By.cssSelector("[data-qa-id='layout-header-go-to-cart-items-count']");
    protected By basketLinkText = By.cssSelector("[data-qa-id='layout-header-go-to-cart']");
    protected By cartViewContent  = By.className("shop-cart-view__content");
    protected By anyProductInCart = By.xpath("(//li[@class='shop-cart-item shop-cart-item--is-vertical'])[1]");
    protected By removeItemIcon = By.className("shop-cart-item-actions__remove-item-icon");


}
