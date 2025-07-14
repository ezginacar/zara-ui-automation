package utils;

public class Constants {
    // File Paths
    public static final String FILE_PATH = "src/test/resources/";
    public static final String SCREENSHOT_DIR = "target/screenshots";
    
    // Wait Configuration
    public static final int RETRY_COUNT = 10;
    public static final int RETRY_INTERVAL_MS = 500;
    public static final int DEFAULT_TIMEOUT_SECONDS = 30;
    
    // Screenshot Configuration
    public static final String SCREENSHOT_DATE_FORMAT = "yyyy-MM-dd_HH-mm-ss";
    public static final int SCREENSHOT_CLEANUP_DAYS = 7;
    
    // Test Configuration
    public static final String TEST_DATA_FILE = "ZaraUITestDatas.xlsx";
    
    // Browser Configuration
    public static final String DEFAULT_BROWSER = "Chrome";
}
