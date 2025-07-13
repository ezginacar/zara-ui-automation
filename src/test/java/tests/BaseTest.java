package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import pages.CommonPage;
import utils.ConfigUtil;
import utils.DriverManager;

import java.io.IOException;

import static utils.LoggingUtils.info;

public class BaseTest {
    protected WebDriver driver;
    private CommonPage commonPage;


    @BeforeEach
    public void setUp() throws IOException {
        info("Initializing driver and navigating to base URL");
        driver= DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.get(ConfigUtil.get("baseUrl"));
        info("Navigated to base Url: " + ConfigUtil.get("baseUrl"));
        commonPage = new CommonPage(driver);
        commonPage.acceptCookiesIfExists();

    }

    @AfterEach
    public void tearDown() {
      //  DriverManager
        info("Driver quit and resources cleaned up");
    }
}
