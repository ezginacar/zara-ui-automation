package utils;

import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {
    private static final ThreadLocal<SelfHealingDriver> driver = new ThreadLocal<>();

    public static void initDriver() {
        WebDriver webdriver;
        if (driver.get() == null) {
            String browser = ConfigUtil.get("browser");
            switch (browser.toLowerCase()) {
                case "firefox":
                    webdriver = new FirefoxDriver();
                    break;
                case "chrome":
                    webdriver = new ChromeDriver();
                    break;
                default:
                    webdriver = new ChromeDriver();
                    break;
            }
            driver.set(SelfHealingDriver.create(webdriver));

        }
    }

    public static SelfHealingDriver getDriver() {
        if(driver == null)
            initDriver();
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
