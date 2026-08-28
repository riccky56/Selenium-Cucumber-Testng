package framework;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// Class declaration that groups the related example logic in one place.
public class ConfigReader {
    // Method definition that performs a specific part of the program workflow.
    private static final Properties props = new Properties();
    static {
        try (InputStream is = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config/config.properties")) {
            // Check the condition before deciding whether this block should run.
            if (is != null) props.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Cannot load config.properties", e);
        }
    }

    // Method definition that performs a specific part of the program workflow.
    public static String get(String key) {
        // Store text data that will be processed by the program logic.
        String sys = System.getProperty(key);
        // Return the final result back to the caller.
        return sys != null ? sys : props.getProperty(key);
    }

    // Method definition that performs a specific part of the program workflow.
    public static String baseUrl()       { return get("baseUrl"); }
    // Method definition that performs a specific part of the program workflow.
    public static String browser()       { return get("browser"); }
    // Method definition that performs a specific part of the program workflow.
    public static boolean headless()     { return Boolean.parseBoolean(get("headless")); }
    // Method definition that performs a specific part of the program workflow.
    public static String loginEmail()    { return get("login.email"); }
    // Method definition that performs a specific part of the program workflow.
    public static String loginPassword() { return get("login.password"); }
}
