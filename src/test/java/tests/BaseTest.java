package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import pages.CommonPage;
import utils.ConfigUtil;
import utils.Constants;
import utils.DriverManager;
import utils.ScreenshotExtension;
import utils.ScreenshotUtils;

import java.io.IOException;

import static utils.LoggingUtils.info;

public class BaseTest {
    protected WebDriver driver;
    private CommonPage commonPage;
    
    // Register screenshot extension for automatic screenshot on test failures
    @RegisterExtension
    static ScreenshotExtension screenshotExtension = new ScreenshotExtension();


    @BeforeEach
    public void setUp() throws IOException {
        info("Initializing driver and navigating to base URL");
        
        // Initialize driver
        driver = DriverManager.getDriver();
        
        driver.get(ConfigUtil.get("baseUrl"));
        info("Navigated to base Url: " + ConfigUtil.get("baseUrl"));
        
        commonPage = new CommonPage(driver);
        commonPage.acceptCookiesIfExists();
    }

    @AfterEach
    public void tearDown() {
        // Clean old screenshots (keep last 7 days)
        ScreenshotUtils.cleanOldScreenshots(Constants.SCREENSHOT_CLEANUP_DAYS);
        
        DriverManager.quitDriver(); 
        info("Driver quit and resources cleaned up");
    }
}
