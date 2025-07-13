package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver() {
        if (driver.get() == null) {
            WebDriverManager.chromedriver().driverVersion("138.0.0.0").setup();
            WebDriver webDriver = new ChromeDriver();
            driver.set(webDriver);
        }
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            initDriver();
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}