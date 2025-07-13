package pages;

import com.epam.healenium.SelfHealingDriver;
import pageobjects.BasketPageLocators;
import utils.ActionsHelper;
import utils.CommonHelper;
import static utils.LoggingUtils.info;

public class BasketPage extends BasketPageLocators {
    private SelfHealingDriver driver;
    private CommonHelper helper;
    private ActionsHelper actions;

    public BasketPage(SelfHealingDriver driver) {
        this.driver = driver;
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

}
