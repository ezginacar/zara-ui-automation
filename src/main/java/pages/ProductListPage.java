package pages;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import pageobjects.ProductListPageLocators;
import utils.CommonHelper;
import utils.DriverManager;
import utils.FileUtil;
import utils.WaitHelper;

import java.io.IOException;

import static utils.LoggingUtils.info;

public class ProductListPage extends ProductListPageLocators {
    private WebDriver driver;
    private CommonHelper helper;
    private FileUtil addedProductsTxt;
    private BasketPage basketPage;

    public ProductListPage(WebDriver driver) throws IOException {
        this.driver = DriverManager.getDriver();
        addedProductsTxt = new FileUtil("AddedProducts.txt");
        helper = new CommonHelper(driver);
        basketPage = new BasketPage(driver);
    }

    //product list page methods
    public ProductListPage addTheFirtProductOnTheSearchResuls(){
        helper.click(addIconOnTheFirstProductFromSearchResults);
        info("Clicked on add icon of the first product on the search results.");
        
        // Wait for size selector modal to appear - CRITICAL for normal mode!
        WaitHelper waitHelper = new WaitHelper(driver);
        waitHelper.waitForElementClickable(fistAvailableSize);
        
        helper.click(fistAvailableSize);
        info("The first product added to the basket with selecting first available size");
        
        // Wait for product to be actually added to basket
        try {
            Thread.sleep(2000); // Give time for basket update
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        return this;
    }

    public ProductListPage typeTheFirstProductInfoToTheTextFile(){
        addedProductsTxt.deleteAllFileContent();
        info("Previous content of the 'AddedProducts.txt' file deleted.");
        addedProductsTxt.fillFile("Product Name: " + getFirstProductName() + "\n" );
        addedProductsTxt.fillFile("Product Price: " + getFirstProductPrice() + "\n");
        info("Product information added to the 'AddedProducts.txt' file.");
        return this;
    }

    private String getFirstProductName() {
        return helper.getText(productNameOfTheFistProduct);
    }

    private String getFirstProductPrice() {
        return helper.getText(productPriceOfTheFirstProduct);
    }

    public void goToCardAfterAddingProduct() {
        try{
            helper.click(addCardNotificationSecondaryButton);
            info("Clicked on 'Go to Card' button after adding product to the basket.");
        }catch (Exception e){
            info("Add to card notification secondary button not found, clicking on the primary button instead.");
            basketPage.clickBasketLinkText();
        }
    }

}
