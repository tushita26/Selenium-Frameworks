package org.parabank.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.parabank.config.TestConstants;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.atomic.AtomicInteger;

public class BrowserFactory {

    // Thread-safe counter for unique debugging ports
    private static final AtomicInteger portCounter = new AtomicInteger(9222);

    /**
     * Create WebDriver instance based on browser name
     * @param browserName - Browser type (chrome, firefox, edge)
     * @return WebDriver instance
     */
    public static WebDriver createWebDriver(String browserName) {
        WebDriver driver = null;

        if (browserName == null || browserName.isEmpty()) {
            browserName = TestConstants.CHROME;
        }

        switch (browserName.toLowerCase()) {
            case TestConstants.CHROME:
                driver = createChromeDriver();
                break;
            case TestConstants.FIREFOX:
                driver = createFirefoxDriver();
                break;
            case TestConstants.EDGE:
                driver = createEdgeDriver();
                break;
            default:
                System.out.println("Browser not found. Launching Chrome browser.");
                driver = createChromeDriver();
        }

        // Set implicit wait
        driver.manage().timeouts().implicitlyWait(
            java.time.Duration.ofSeconds(TestConstants.IMPLICIT_WAIT));
        
        // Set page load timeout
        driver.manage().timeouts().pageLoadTimeout(
            java.time.Duration.ofSeconds(TestConstants.PAGE_LOAD_TIMEOUT));

        // Maximize window
        driver.manage().window().maximize();

        return driver;
    }

    /**
     * Create Chrome WebDriver with unique debugging port for parallel execution
     */
    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        
        // Assign unique debugging port to avoid conflicts in parallel execution
        int debugPort = portCounter.getAndIncrement();
        options.addArguments("--remote-debugging-port=" + debugPort);
        
        // Uncomment below line to run in headless mode
        // options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-gpu");
        options.addArguments("--start-maximized");
        
        System.out.println("Creating ChromeDriver on port: " + debugPort);
        return new ChromeDriver(options);
    }

    /**
     * Create Firefox WebDriver
     */
    private static WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        // Uncomment below line to run in headless mode
        // options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        return new FirefoxDriver(options);
    }

    /**
     * Create Edge WebDriver
     */
    private static WebDriver createEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        // Uncomment below line to run in headless mode
        // options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        return new EdgeDriver(options);
    }

    /**
     * Close WebDriver
     */
    public static void closeWebDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}
