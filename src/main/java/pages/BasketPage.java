package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import pageobjects.BasketPageLocators;
import utils.ActionsHelper;
import utils.CommonHelper;
import utils.FileUtil;
import utils.WaitHelper;

import static utils.LoggingUtils.info;

public class BasketPage extends BasketPageLocators {
    private WebDriver  driver;
    private CommonHelper helper;
    private ActionsHelper actions;
    private FileUtil addedProductsTxt;

    public BasketPage(WebDriver  driver) {
        this.driver = driver;
        helper = new CommonHelper(driver);
        actions = new ActionsHelper(driver);
        addedProductsTxt = new FileUtil("AddedProducts.txt");
    }


    //Basket page actions
    public void removeAllProductsIfExists(){
        if(helper.getText(basketCardQuantity).equals("0"))
            info("Basket is also empty");

        else{
            clickBasketLinkText().removeAllProductsFromBasket();
        }

    }

    public BasketPage removeAllProductsFromBasket(){
        while(!helper.isElementDisplayed(cartViewContent)) {
            actions.hoverAndClick(anyProductInCart, removeItemIcon);
            info("One of product removed from basket");
        }
        info("All products removed from basket");
        return this;
    }

    public BasketPage clickBasketLinkText() {
        helper.click(basketLinkText);
        return this;
    }

    public void validateAddedProductNameFromTheTxt(){
        String text = addedProductsTxt.readFile();
        Assertions.assertTrue(text.contains("Product Name: " + helper.getText(singleProductNameInCart)));
        info("Product name validated from the 'AddedProducts.txt' file");
    }

    public void validateAddedProductPriceFromTheTxt(){
        String text = addedProductsTxt.readFile();
        Assertions.assertTrue(
                text.contains("Product Price: " + helper.getText(singleProductPriceInCart)),
                "Expected text to contain: Product Price");
        info("Product price validated from the 'AddedProducts.txt' file");
    }

    public BasketPage increaseTheProductQuantityInBasket() {
        // Get current quantity before clicking
        int currentQuantity = Integer.parseInt(helper.getAttribute(singleProductTotalAmount, "value"));
        info("Current quantity before increase: " + currentQuantity);
        
        // Click increase button with proper wait
        WaitHelper waitHelper = new WaitHelper(driver);
        waitHelper.waitForElementClickable(singleProductIncreaseButton);
        helper.click(singleProductIncreaseButton);
        info("Clicked on the increase button of the product in the basket");
        
        // Wait for quantity to actually increase
        int expectedQuantity = currentQuantity + 1;
        try {
            // Wait up to 5 seconds for quantity to update
            for (int i = 0; i < 10; i++) {
                Thread.sleep(500);
                int newQuantity = Integer.parseInt(helper.getAttribute(singleProductTotalAmount, "value"));
                if (newQuantity == expectedQuantity) {
                    info("Quantity successfully increased from " + currentQuantity + " to " + newQuantity);
                    break;
                }
                if (i == 9) {
                    info("Warning: Quantity did not increase as expected. Current: " + newQuantity + ", Expected: " + expectedQuantity);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        return this;
    }

    public void validateProductQuantityInBasket(int expectedQuantity) {
        int actualQuantity = Integer.parseInt(helper.getAttribute(singleProductTotalAmount,"value"));
        Assertions.assertEquals(expectedQuantity, actualQuantity, "Product quantity in the basket is not as expected");
    }



}
