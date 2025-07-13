package tests;

import com.epam.healenium.SelfHealingDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import utils.ConfigUtil;
import utils.DriverManager;

import static utils.LoggingUtils.info;

public class BaseTest {
    protected SelfHealingDriver driver;


    @BeforeEach
    public void setUp() {
        info("Initializing driver and navigating to base URL");
        DriverManager.initDriver();
        driver.manage().window().maximize();
        driver.get(ConfigUtil.get("baseUrl"));
        info("Navigated to base Url: " + ConfigUtil.get("baseUrl"));

    }

    @AfterEach
    public void tearDown() {
      //  DriverManager
        info("Driver quit and resources cleaned up");
    }
}
