package pages;

import org.openqa.selenium.WebDriver;
import pageobjects.ProductListPageLocators;
import utils.CommonHelper;
import utils.DriverManager;
import utils.FileUtil;

import static utils.LoggingUtils.info;

public class ProductListPage extends ProductListPageLocators {
    private WebDriver driver;
    private CommonHelper helper;
    private FileUtil addedProductsTxt;

    public ProductListPage(WebDriver driver) {
        this.driver = DriverManager.getDriver();
        addedProductsTxt = new FileUtil("AddedProducts.txt");
        helper = new CommonHelper(driver);
    }

    //product list page methods
    public ProductListPage addTheFirtProductOnTheSearchResuls(){
        helper.click(addIconOnTheFirstProductFromSearchResults);
        info("Clicked on add icon of the first product on the search results.");
        helper.click(fistAvailableSize);
        info("The first product added to the basket with  selecting first available size");
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


}
