package utils;

import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {
    private static final Properties props = new Properties();

    static {
        try (var inputStream = ConfigUtil.class.getClassLoader().getResourceAsStream("config.properties"))
        {
            if (inputStream == null) {
                throw new RuntimeException("config.properties not found in classpath!");
            }
            props.load(inputStream);
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

    /**
     * Get config value with default fallback
     * @param key Config key
     * @param defaultValue Default value if key not found
     * @return Config value or default value
     */
    public static String get(String key, String defaultValue) {
        try {
            return get(key);
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
