package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.ConfigUtil;

import utils.DriverManager;



import java.io.IOException;


import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

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
        homePage.clickLoginLinkText();
        loginPage.login(ConfigUtil.get("userEmail"), ConfigUtil.get("userPassword"));

        // delete all basket products
        basketPage.removeAllProductsIfExists();

        // Navigate: Menu -> Erkek->Tümünü Gör
        homePage.clickGivenCategoryOnTheNavMenu("ERKEK");
        commonPage.clickXpathWithText("TÜMÜNÜ GÖR");

        // Search "şort"
        commonPage.fillSearchInputFromExcel(1);
        // Delete input than search "gömlek"
        commonPage.fillSearchInputFromExcel(2);
        //press enter
        commonPage.pressEnterToSearching();
        //
        productListPage.typeTheFirstProductInfoToTheTextFile()
                .addTheFirtProductOnTheSearchResuls();
        // Navigate to the basket
        basketPage.clickBasketLinkText();
        // Validate that the product is added to the basket fro
        basketPage.validateAddedProductNameFromTheTxt();
        basketPage.validateAddedProductPriceFromTheTxt();
       //increase the product quantity
        basketPage.increaseTheProductQuantityInBasket();
        // Validate that the product quantity is increased
        basketPage.validateProductQuantityInBasket(2);



    }
}
