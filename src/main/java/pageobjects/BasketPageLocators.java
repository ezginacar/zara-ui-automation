package pageobjects;

import org.openqa.selenium.By;

public class BasketPageLocators {
    protected By basketCardQuantity = By.cssSelector("[data-qa-id='layout-header-go-to-cart-items-count']");
    protected By basketLinkText = By.cssSelector("[data-qa-id='layout-header-go-to-cart']");
    
    // Modal elements after adding product to cart - SMART DETECTION
    protected By cartViewContent  = By.className("shop-cart-view__content");
    protected By anyProductInCart = By.xpath("(//li[@class='shop-cart-item shop-cart-item--is-vertical'])[1]");
    protected By removeItemIcon = By.className("shop-cart-item-actions__remove-item-icon");
    // Modern Selenium 4 - Flexible CSS Selectors for better reliability
    protected By singleProductNameInCart = By.cssSelector("[class*='shop-cart-item'] [class*='product-name'] a, [class*='shop-cart-item'] [class*='description'] a, [class*='cart-item'] [class*='name'] a");
    protected By singleProductPriceInCart = By.cssSelector("[class*='shop-cart-item'] [class*='money-amount'], [class*='cart-item'] [class*='price']");
    // Modern Selenium 4 - CSS Selectors for quantity management
    protected By singleProductIncreaseButton = By.cssSelector("[class*='shop-cart-item'] [data-qa-id='add-order-item-unit'], [class*='shop-cart-item'] button[class*='increase'], [class*='quantity-selector'] button[class*='plus']");
    protected By singleProductTotalAmount = By.cssSelector("[class*='shop-cart-item'] [class*='quantity-selector'] input, [class*='shop-cart-item'] input[type='number']");




}
