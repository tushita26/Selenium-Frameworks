package org.parabank.base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.parabank.factory.BrowserFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseTest {
    
    protected WebDriver driver;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public static ThreadLocal<ExtentTest> tlExtentTest = new ThreadLocal<>();
    
    public static ExtentReports extentReports;
    private static final Object extentLock = new Object();
    private static final AtomicInteger testContextCount = new AtomicInteger(0);
    
    /**
     * Initialize Extent Reports once for parallel execution
     */
    @BeforeClass
    public static void initializeExtentReports() {
        synchronized (extentLock) {
            if (extentReports == null) {
                String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
                String reportPath = "reports/ExtentReport_" + timestamp + ".html";
                
                ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
                sparkReporter.config().setReportName("ParaBank Automation Report");
                sparkReporter.config().setDocumentTitle("Test Report - " + timestamp);
                
                // Load configuration from properties file (optional)
                try {
                    String configPath = BaseTest.class.getClassLoader().getResource("src/test/resources/extent-config.xml").getPath();
                    // String configPathSpark = BaseTest.class.getClassLoader().getResource("spark-config.xml").getPath();
                    sparkReporter.loadXMLConfig(configPath);
                    // sparkReporter.loadXMLConfig(configPathSpark);
                } catch (Exception e1) {
                    // If config file not found, continue with default settings
                    System.out.println("Extent config file not found, using default settings");
                }
                
                extentReports = new ExtentReports();
                extentReports.attachReporter(sparkReporter);
                extentReports.setSystemInfo("OS", System.getProperty("os.name"));
                extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
            }
        }
    }
    
    /**
     * Initialize WebDriver before each test
     */
    @BeforeMethod
    public void setUp() {
        initializeExtentReports();
        
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        
        // Use BrowserFactory to create WebDriver
        driver = BrowserFactory.createWebDriver(browser);
        tlDriver.set(driver);
    }
    
    /**
     * Close WebDriver after each test
     */
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            try{
                WebElement logOut = driver.findElement(By.xpath("//a[contains(text(),'Log Out')]"));
                logOut.click();
            }
            catch (Exception e) {
                System.out.println("User not logged in.");
            }

            driver.quit();
            tlDriver.remove();
        }
        tlExtentTest.remove();
    }
    
    /**
     * Get WebDriver instance for the current thread
     * @return WebDriver instance for current thread
     */
    public static WebDriver getDriver() {
        return tlDriver.get();
    }
    
    /**
     * Get WebDriver instance for a specific thread (advanced usage)
     * @param threadId The thread ID to get driver for
     * @return WebDriver instance for the specified thread, or null if not found
     */
    public static WebDriver getDriverByThreadId(long threadId) {
        // Note: ThreadLocal doesn't provide direct access by thread ID
        // This method returns the driver for the current thread only
        // For advanced thread-specific driver management, consider using a Map<Thread, WebDriver>
        System.out.println("Getting driver for thread ID: " + threadId + ", current thread: " + Thread.currentThread().getId());
        return tlDriver.get();
    }
    
    /**
     * Get current thread information for debugging
     * @return String with thread details
     */
    public static String getCurrentThreadInfo() {
        Thread currentThread = Thread.currentThread();
        return "Thread ID: " + currentThread.getId() + 
               ", Name: " + currentThread.getName() + 
               ", Has Driver: " + (tlDriver.get() != null);
    }
    
    /**
     * Increment test context counter on suite start
     */
    public static void incrementTestContextCount() {
        testContextCount.incrementAndGet();
    }
}
