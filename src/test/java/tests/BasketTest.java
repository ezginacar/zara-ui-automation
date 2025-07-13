package tests;

import com.epam.healenium.SelfHealingDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;
import pageobjects.CommonPageLocators;
import pages.*;
import utils.ConfigUtil;

import utils.DriverManager;
import utils.ExcelUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public class BasketTest extends BaseTest{
    private SelfHealingDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private ProductListPage productListPage;
    private ProductDetailPage productDetailPage;
    private CommonPage commonPage;
    private BasketPage basketPage;

    @BeforeAll
    public void setUpClass() {
        driver = DriverManager.getDriver();

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        productListPage = new ProductListPage(driver);
        productDetailPage = new ProductDetailPage(driver);
        commonPage = new CommonPage(driver);
        basketPage = new BasketPage(driver);


    }

    @Test
    public void testBasketFlow() throws IOException {
        homePage.clickLoginLinkText();
        loginPage.login(ConfigUtil.get("userEmail"), ConfigUtil.get("userPassword"));

        // delete all basket products
        basketPage.removeAllProductsIfExists();

        // Navigate: Menu -> Erkek->Tümünü Gör
        homePage.clickGivenCategoryOnTheNavMenu("ERKEK");
        commonPage.clickXpathWithText("TÜMÜNÜ GÖR");

    }
}
