package driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.ConfigUtil;

/**
 * ChromeDriverFactory - Chrome WebDriver creation ve configuration
 * 
 * Responsibilities:
 * - Chrome-specific options configuration
 * - Chrome-specific service setup  
 * - Chrome performance optimizations
 * - Chrome security settings
 */
public class ChromeDriverFactory {

    /**
     * Create fully configured Chrome WebDriver
     * @return Configured Chrome WebDriver instance
     */
    public static WebDriver create() {
        ChromeOptions options = buildOptions();
        ChromeDriverService service = buildService();
        
        // Selenium 4 SeleniumManager handles driver download automatically
        return new ChromeDriver(service, options);
    }

    /**
     * Build Chrome-specific options from configuration (Simplified for Zara testing)
     */
    private static ChromeOptions buildOptions() {
        ChromeOptions options = new ChromeOptions();
        
        // Essential stability options for Zara testing
        addEssentialOptions(options);
        
        // Basic config-driven options
        addBasicConfigOptions(options);
        
        return options;
    }

    /**
     * Build Chrome-specific service configuration
     * @return Configured ChromeDriverService
     */
    private static ChromeDriverService buildService() {
        return new ChromeDriverService.Builder()
            .usingAnyFreePort()
            .withSilent(true)  // Reduce service logs
            .build();
    }

    /**
     * Add essential Chrome options for Zara e-commerce testing
     */
    private static void addEssentialOptions(ChromeOptions options) {
        // Core stability options (required for CI/CD and Docker)
        options.addArguments("--disable-gpu");           // Prevents crashes in headless
        options.addArguments("--no-sandbox");            // Required for CI/CD environments
        options.addArguments("--disable-dev-shm-usage"); // Required for Docker environments
        options.addArguments("--disable-extensions");    // Faster startup, no interference
        
        // UI enhancements for better testing
        options.addArguments("--disable-infobars");      // Remove automation infobar
        options.addArguments("--disable-notifications"); // Prevent notification popups
    }

    /**
     * Add basic configuration-driven options for Zara testing
     */
    private static void addBasicConfigOptions(ChromeOptions options) {
        // Chrome-specific headless mode
        boolean headless = Boolean.parseBoolean(ConfigUtil.get("chrome.headless", "false"));
        if (headless) {
            options.addArguments("--headless");
        }
        

    }
} 