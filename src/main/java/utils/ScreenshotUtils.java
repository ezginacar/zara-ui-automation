package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static utils.LoggingUtils.info;
import static utils.LoggingUtils.error;

public class ScreenshotUtils {
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern(Constants.SCREENSHOT_DATE_FORMAT);

    /**
     * Takes screenshot and saves with timestamp and test name
     * @param driver WebDriver instance
     * @param testName Name of the failed test
     * @return Full path of the saved screenshot
     */
    public static String takeScreenshot(WebDriver driver, String testName) {
        try {
            // Create screenshots directory if not exists
            createScreenshotDirectory();
            
            // Generate filename with timestamp
            String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
            String fileName = String.format("%s_%s.png", testName, timestamp);
            String fullPath = Constants.SCREENSHOT_DIR + File.separator + fileName;
            
            // Take screenshot
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            File destFile = new File(fullPath);
            
            // Copy file to destination
            Files.copy(sourceFile.toPath(), destFile.toPath());
            
            info("Screenshot saved: " + fullPath);
            return fullPath;
            
        } catch (Exception e) {
            error("Failed to take screenshot: " + e.getMessage());
            return null;
        }
    }

    /**
     * Takes screenshot for manual calls (debugging purposes)
     * @param driver WebDriver instance
     * @param screenshotName Custom name for screenshot
     * @return Full path of the saved screenshot
     */
    public static String takeManualScreenshot(WebDriver driver, String screenshotName) {
        return takeScreenshot(driver, "manual_" + screenshotName);
    }

    /**
     * Creates screenshot directory structure
     */
    private static void createScreenshotDirectory() {
        try {
            Path screenshotPath = Paths.get(Constants.SCREENSHOT_DIR);
            if (!Files.exists(screenshotPath)) {
                Files.createDirectories(screenshotPath);
                info("Screenshot directory created: " + Constants.SCREENSHOT_DIR);
            }
        } catch (IOException e) {
            error("Failed to create screenshot directory: " + e.getMessage());
        }
    }

    /**
     * Cleans old screenshots (older than specified days)
     * @param daysToKeep Number of days to keep screenshots
     */
    public static void cleanOldScreenshots(int daysToKeep) {
        try {
            Path screenshotPath = Paths.get(Constants.SCREENSHOT_DIR);
            if (Files.exists(screenshotPath)) {
                Files.walk(screenshotPath)
                    .filter(Files::isRegularFile)
                    .filter(file -> {
                        try {
                            long fileTime = Files.getLastModifiedTime(file).toMillis();
                            long cutoffTime = System.currentTimeMillis() - (daysToKeep * 24L * 60L * 60L * 1000L);
                            return fileTime < cutoffTime;
                        } catch (IOException e) {
                            return false;
                        }
                    })
                    .forEach(file -> {
                        try {
                            Files.delete(file);
                            info("Deleted old screenshot: " + file.getFileName());
                        } catch (IOException e) {
                            error("Failed to delete old screenshot: " + e.getMessage());
                        }
                    });
            }
        } catch (IOException e) {
            error("Failed to clean old screenshots: " + e.getMessage());
        }
    }
}
