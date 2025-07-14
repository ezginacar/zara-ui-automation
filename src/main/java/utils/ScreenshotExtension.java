package utils;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;

import static utils.LoggingUtils.info;
import static utils.LoggingUtils.error;

/**
 * JUnit 5 extension for automatic screenshot capture on test failures
 */
public class ScreenshotExtension implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        String testName = context.getDisplayName();
        info("Test failed: " + testName + " - Taking screenshot...");
        
        try {
            WebDriver driver = DriverManager.getDriver();
            if (driver != null) {
                String screenshotPath = ScreenshotUtils.takeScreenshot(driver, 
                    "FAILED_" + testName.replaceAll("[^a-zA-Z0-9]", "_"));
                
                if (screenshotPath != null) {
                    info("Screenshot captured for failed test: " + screenshotPath);
                    // Add screenshot path to test context for potential reporting
                    context.getStore(ExtensionContext.Namespace.GLOBAL)
                           .put("screenshot.path", screenshotPath);
                }
            } else {
                error("WebDriver instance is null - cannot take screenshot");
            }
        } catch (Exception e) {
            error("Failed to capture screenshot for failed test: " + e.getMessage());
        }
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        // Optional: Log successful test completion
        info("Test passed: " + context.getDisplayName());
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        String testName = context.getDisplayName();
        info("Test aborted: " + testName + " - Taking screenshot...");
        
        try {
            WebDriver driver = DriverManager.getDriver();
            if (driver != null) {
                String screenshotPath = ScreenshotUtils.takeScreenshot(driver, 
                    "ABORTED_" + testName.replaceAll("[^a-zA-Z0-9]", "_"));
                
                if (screenshotPath != null) {
                    info("Screenshot captured for aborted test: " + screenshotPath);
                }
            }
        } catch (Exception e) {
            error("Failed to capture screenshot for aborted test: " + e.getMessage());
        }
    }

    @Override
    public void testDisabled(ExtensionContext context, java.util.Optional<String> reason) {
        info("Test disabled: " + context.getDisplayName() + 
             (reason.isPresent() ? " - Reason: " + reason.get() : ""));
    }
} 