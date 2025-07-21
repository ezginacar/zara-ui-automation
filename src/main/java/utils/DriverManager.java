package utils;

import driverfactory.ChromeDriverFactory;
import org.openqa.selenium.WebDriver;

import static utils.LoggingUtils.info;

public class DriverManager {

    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    //driverManager is not created for initilization
    private DriverManager(){
        throw new IllegalStateException("Utility class");
    }

    public static WebDriver getDriver(){
        if(driverThread.get() == null){
            initDriver();
        }
        return driverThread.get();
    }

    private static void setDriver(WebDriver driver){
        driverThread.set(driver);
    }

    private static void initDriver(){
        String browser = ConfigUtil.get("browser").toUpperCase();
        WebDriver driver = switch(browser){
            case "CHROME" -> ChromeDriverFactory.create();
            default -> throw new IllegalArgumentException("Invalid browser: " + browser);
        };   
        setDriver(driver);
        
        // Configure window settings after driver creation
        configureWindow(driver);
    }

    /**
     * Configure window settings based on configuration
     * Centralized window management - no need to call in test classes
     */
    private static void configureWindow(WebDriver driver) {
        boolean maximizeWindow = Boolean.parseBoolean(ConfigUtil.get("maximizeWindow", "true"));
        
        if (maximizeWindow) {
            driver.manage().window().maximize();
            info("Window maximized automatically by DriverManager");
        } else {
            String windowSize = ConfigUtil.get("windowSize", "1920,1080");
            String[] dimensions = windowSize.split(",");
            if (dimensions.length == 2) {
                try {
                    int width = Integer.parseInt(dimensions[0].trim());
                    int height = Integer.parseInt(dimensions[1].trim());
                    driver.manage().window().setSize(new org.openqa.selenium.Dimension(width, height));
                    info("Window size set to: " + width + "x" + height);
                } catch (NumberFormatException e) {
                    // Fallback to maximize if invalid dimensions
                    driver.manage().window().maximize();
                    info("Invalid window size format, falling back to maximize");
                }
            } else {
                // Fallback to maximize if invalid format
                driver.manage().window().maximize();
                info("Invalid window size format, falling back to maximize");
            }
        }
    }

    public static void quitDriver(){
        if(driverThread.get() != null){
            driverThread.get().quit();
            driverThread.remove();
        }
    }
}