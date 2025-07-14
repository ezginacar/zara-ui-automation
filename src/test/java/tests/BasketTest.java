package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.ConfigUtil;
import utils.DriverManager;

import java.io.IOException;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static utils.LoggingUtils.info;

@DisplayName("Zara Basket Test")
@TestInstance(PER_CLASS)
public class BasketTest extends BaseTest{
    private HomePage homePage;
    private LoginPage loginPage;
    private ProductListPage productListPage;
    private CommonPage commonPage;
    private BasketPage basketPage;

    @BeforeAll
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
            homePage.clickLoginLinkText();
            loginPage.login(ConfigUtil.get("userEmail"), ConfigUtil.get("userPassword"));

            // Take screenshot after login for documentation
            commonPage.takeScreenshot("after_login");
            info("Screenshot taken after successful login");

            // delete all basket products
            basketPage.removeAllProductsIfExists();

            // Navigate: Menu -> Men->View All
            homePage.clickGivenCategoryOnTheNavMenu("ERKEK");
            commonPage.clickXpathWithText("TÜMÜNÜ GÖR");

            // Take screenshot after navigation
            commonPage.takeScreenshot("after_navigation_to_category");

            // Search "şort"
            commonPage.fillSearchInputFromExcel(1);
            // Delete input than search "gömlek"
            commonPage.fillSearchInputFromExcel(2);
            //press enter
            commonPage.pressEnterToSearching();

            // Take screenshot after search
            commonPage.takeScreenshot("after_search_results");

            productListPage.typeTheFirstProductInfoToTheTextFile()
                    .addTheFirtProductOnTheSearchResuls();

            // Navigate to the basket
            basketPage.clickBasketLinkText();

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

    @Test
    @DisplayName("Demonstration of Failed Test Screenshot")
    @Disabled("This test is intentionally disabled - it's just for screenshot demo")
    public void testScreenshotOnFailure() {
        // This test will fail intentionally to demonstrate screenshot capture
        homePage.clickLoginLinkText();
        
        // This assertion will fail and screenshot will be taken automatically
        Assertions.assertTrue(false, "This test is intentionally failed to demonstrate screenshot capture");
    }
}
