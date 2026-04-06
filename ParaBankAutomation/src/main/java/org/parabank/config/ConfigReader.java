package org.parabank.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    private static final String CONFIG_FILE = "src/test/resources/config.properties";

    static {
        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream(CONFIG_FILE);
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            System.out.println("Error reading config.properties: " + e.getMessage());
        }
    }

    /**
     * Get property value from config file
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Get property with default value
     */
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Get browser from config
     */
    public static String getBrowser() {
        return getProperty("browser", "chrome");
    }

    /**
     * Get application URL
     */
    public static String getAppUrl() {
        return getProperty("app.url", "https://parabank.parasoft.com/parabank/index.htm");
    }

    /**
     * Get implicit wait
     */
    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait", "10"));
    }

    /**
     * Get explicit wait
     */
    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait", "15"));
    }

    /**
     * Get page load timeout
     */
    public static int getPageLoadTimeout() {
        return Integer.parseInt(getProperty("page.load.timeout", "20"));
    }

    /**
     * Check if headless mode is enabled
     */
    public static boolean isHeadlessMode() {
        return Boolean.parseBoolean(getProperty("headless.mode", "false"));
    }

    /**
     * Get screenshot path
     */
    public static String getScreenshotPath() {
        return getProperty("screenshot.path", "screenshots/");
    }

    /**
     * Check if screenshot on failure is enabled
     */
    public static boolean isScreenshotOnFailure() {
        return Boolean.parseBoolean(getProperty("screenshot.on.failure", "true"));
    }

    /**
     * Get report path
     */
    public static String getReportPath() {
        return getProperty("report.path", "reports/");
    }

    /**
     * Get report title
     */
    public static String getReportTitle() {
        return getProperty("report.title", "ParaBank Automation Test Report");
    }

    /**
     * Get test username
     */
    public static String getTestUsername() {
        return getProperty("test.username", "john");
    }

    /**
     * Get test password
     */
    public static String getTestPassword() {
        return getProperty("test.password", "demo");
    }

    /**
     * Get invalid username
     */
    public static String getInvalidUsername() {
        return getProperty("invalid.username", "invalid");
    }

    /**
     * Get invalid password
     */
    public static String getInvalidPassword() {
        return getProperty("invalid.password", "wrong");
    }

    /**
     * Get environment
     */
    public static String getEnvironment() {
        return getProperty("environment", "testing");
    }
}
