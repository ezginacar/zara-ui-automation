package pages;

import com.epam.healenium.SelfHealingDriver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import pageobjects.BasketPageLocators;
import utils.ActionsHelper;
import utils.CommonHelper;
import utils.FileUtil;

import static utils.LoggingUtils.info;

public class BasketPage extends BasketPageLocators {
    private SelfHealingDriver driver;
    private CommonHelper helper;
    private ActionsHelper actions;
    private FileUtil addedProductsTxt;

    public BasketPage(SelfHealingDriver driver) {
        this.driver = driver;
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
        info("Basket link text clicked");
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
        helper.click(singleProductIncreaseButton);
        info("Cliced on the increase button of the product in the basket");
        return null;
    }

    public void validateProductQuantityInBasket(int expectedQuantity) {
        int actualQuantity = Integer.parseInt(helper.getAttribute(singleProductTotalAmount,"value"));
        Assertions.assertEquals(expectedQuantity, actualQuantity, "Product quantity in the basket is not as expected");    }



}
