package org.parabank.config;

public class TestConstants {
    // URLs
    public static final String BASE_URL = "https://parabank.parasoft.com/parabank/index.htm";
    public static final String LOGIN_URL = "https://parabank.parasoft.com/parabank/index.htm";

    // Test Data - Credentials
    public static final String VALID_USERNAME = "john";
    public static final String VALID_USERNAME_2 = "jane";
    public static final String VALID_PASSWORD = "demo";
    public static final String VALID_PASSWORD_2 = "demo";
    public static final String INVALID_USERNAME = "invalid";
    public static final String INVALID_PASSWORD = "wrong";

    // Wait Times (in seconds)
    public static final int IMPLICIT_WAIT = 10;
    public static final int EXPLICIT_WAIT = 15;
    public static final int FLUENT_WAIT = 10;
    public static final int PAGE_LOAD_TIMEOUT = 20;

    // Browser Types
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";
    public static final String EDGE = "edge";

    // File Paths
    public static final String SCREENSHOT_PATH = "screenshots/";
    public static final String REPORT_PATH = "reports/";
    public static final String EXCEL_PATH = "src/test/resources/testdata/";

    // Error Messages
    public static final String LOGIN_ERROR_MESSAGE = "The username and password combination you entered is invalid.";
    public static final String INVALID_CREDENTIALS_ERROR = "java.lang.Exception";

    // Dashboard Messages
    public static final String DASHBOARD_WELCOME_MESSAGE = "Welcome";

    // Timeouts
    public static final int SHORT_WAIT = 5;
    public static final int MEDIUM_WAIT = 10;
    public static final int LONG_WAIT = 30;
}
