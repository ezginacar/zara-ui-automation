package tests;

import org.junit.jupiter.api.*;
import pages.*;


import java.io.IOException;

import static utils.LoggingUtils.info;

@DisplayName("Zara Basket Test")
public class BasketTest extends BaseTest{
    private HomePage homePage;
    private LoginPage loginPage;
    private ProductListPage productListPage;
    private CommonPage commonPage;
    private BasketPage basketPage;

    @BeforeEach
    public void setUpClass() throws IOException {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        productListPage = new ProductListPage(driver);
        commonPage = new CommonPage(driver);
        basketPage = new BasketPage(driver);
    }

    @Tag("smoke")
    @Test
    @DisplayName("Basket Flow: Search product, adding, validate")
    public void testBasketFlow() throws IOException {
        try {
            

            // Login info is not valid, so commenting out the login part
            // homePage.clickLoginLinkText();
            // loginPage.login(ConfigUtil.get("userEmail"), ConfigUtil.get("userPassword"));
            // Take screenshot after login for documentation
            // commonPage.takeScreenshot("after_login");
            // info("Screenshot taken after successful login");


            // delete all basket products
            basketPage.removeAllProductsIfExists();

            // Navigate: Menu -> Men->View All
            homePage.goToGivenCategoryOnTheNavMenu("ERKEK");
            homePage.clickSubcategoryOnTheNewCollectionList("TÜMÜNÜ GÖR");


            // Take screenshot after navigation
            commonPage.takeScreenshot("after_navigation_to_category");


            // Search "şort"

            commonPage.clickSearchIcon().fillSearchInputFromExcel(1)
            // Delete input than search "gömlek"
            .fillSearchInputFromExcel(2)
            //press enter
            .pressEnterToSearching();

            // Take screenshot after search
            commonPage.takeScreenshot("after_search_results");

            productListPage.typeTheFirstProductInfoToTheTextFile();

            productListPage.addTheFirtProductOnTheSearchResuls();
                
            commonPage.takeScreenshot("product_added");
            // Navigate to the basket
            productListPage.goToCardAfterAddingProduct();

            // Take screenshot of basket page
            commonPage.takeScreenshot("basket_page_with_product");

            // Validate that the product is added to the basket
            basketPage.validateAddedProductNameFromTheTxt();
            basketPage.validateAddedProductPriceFromTheTxt();

            //increase the product quantity
            basketPage.increaseTheProductQuantityInBasket();

            // Validate that the product quantity is increased
            basketPage.validateProductQuantityInBasket(2);

            // Take final screenshot
            commonPage.takeScreenshot("final_basket_state");
            info("Test completed successfully with screenshots captured at key steps");

        } catch (Exception e) {
            // Manual screenshot on exception before re-throwing
            commonPage.takeScreenshot("exception_occurred");
            info("Exception screenshot captured: " + e.getMessage());
            throw e;
        }
    }

}
