package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {
    private static final Properties props = new Properties();

    static {
        try (var fis = new FileInputStream(Constants.FILE_PATH + "config.properties"))
        {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Config file not found!", e);
        } finally {
            if (props.isEmpty()) {
                throw new RuntimeException("Config file is empty or not loaded properly!");
            }
        }
    }

    public static String get(String key) {
        try {
            String value = props.getProperty(key);
            if (value == null) {
                throw new IllegalArgumentException("Key not found in config: " + key);
            }
            return value;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving key from config: " + key, e);
        }
    }
}
