package test.java;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import main.java.utils.Constants;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static WebDriver driver;
    public ExtentSparkReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest logger;

    @BeforeTest
    public void beforeTestMethod() {
        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "reports"+ File.separator + "AutomationReports.html");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Automation Test Results");
        htmlReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Automation Tester", "Tushita Chaturvedi");
    }

    @BeforeMethod
    @Parameters(value={"browserName"})
    public void beforeMethodMethod(String browserName, Method testMethod){
        logger = extent.createTest(testMethod.getName());
        setupDriver(browserName);
        driver.manage().window().maximize();
        driver.get(Constants.url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void afterMethodMethod(ITestResult result){
        if(result.getStatus()==ITestResult.SUCCESS) {
            String methodName = result.getMethod().getMethodName();
            String logText = "Test Case: " + methodName + "Passed";
            Markup m = (Markup) MarkupHelper.createLabel(logText, ExtentColor.GREEN);
            logger.log(Status.PASS, (Media) m);
        } else if(result.getStatus()==ITestResult.FAILURE) {
            String methodName = result.getMethod().getMethodName();
            String logText = "Test Case: " + methodName + "Failed";
            Markup m = (Markup) MarkupHelper.createLabel(logText, ExtentColor.RED);
            logger.log(Status.FAIL, (Media) m);
        }
        driver.quit();
    }

    @AfterTest
    public void afterTestMethod(){
        extent.flush();
    }
    public void setupDriver(String browserName) {
        if(browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            Proxy proxy = new Proxy();
            proxy.setHttpProxy("myhttpproxy:3337");
            options.setCapability("proxy", proxy);
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "drivers" + File.separator +  "chromedriver.exe");
            driver = new ChromeDriver(options);
        }  else if(browserName.equalsIgnoreCase("firefox")) {
  /* FirefoxProfile profile = new FirefoxProfile();
   profile.setPreference("network.proxy.no_proxies_on", "localhost");
   profile.setPreference("javascript.enabled", true);

   MutableCapabilities capabilities = new MutableCapabilities();
   capabilities.setCapability("browserVersion", "105");
   capabilities.setCapability("browserName", Browser.FIREFOX);

   FirefoxOptions options = new FirefoxOptions();
   options.merge(capabilities);
   options.addPreference("browser.link.open_newwindow", 3);
   options.addPreference("browser.link.open_newwindow.restriction", 0);*/
            System.out.println(System.getProperty("user.dir") + File.separator + "drivers" + File.separator +  "geckodriver.exe");
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator + "drivers" + File.separator +  "geckodriver.exe");
            driver = new FirefoxDriver();
        }else {
            System.out.println(System.getProperty("user.dir") + File.separator + "drivers" + File.separator +  "chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver.exe");
            driver= new ChromeDriver();
        }
    }
}
