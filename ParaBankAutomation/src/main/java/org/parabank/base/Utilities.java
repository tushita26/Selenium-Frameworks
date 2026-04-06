package org.parabank.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Utilities {
    
    private static final int EXPLICIT_WAIT_TIMEOUT = 10;
    
    /**
     * Wait for element to be visible
     */
    public static WebElement waitForElement(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    /**
     * Wait for element to be clickable
     */
    public static WebElement waitForClickableElement(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    /**
     * Wait for element to be present in DOM
     */
    public static WebElement waitForElementPresence(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    /**
     * Click on element with explicit wait
     */
    public static void click(WebDriver driver, By locator) {
        waitForClickableElement(driver, locator).click();
    }
    
    /**
     * Type text in input field
     */
    public static void typeText(WebDriver driver, By locator, String text) {
        WebElement element = waitForElement(driver, locator);
        element.clear();
        element.sendKeys(text);
    }
    
    /**
     * Get text from element
     */
    public static String getText(WebDriver driver, By locator) {
        return waitForElement(driver, locator).getText();
    }
    
    /**
     * Get attribute value from element
     */
    public static String getAttribute(WebDriver driver, By locator, String attributeName) {
        return waitForElement(driver, locator).getAttribute(attributeName);
    }
    
    /**
     * Check if element is displayed
     */
    public static boolean isElementDisplayed(WebDriver driver, By locator) {
        try {
            return waitForElement(driver, locator).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }
    
    /**
     * Check if element exists
     */
    public static boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    
    /**
     * Take screenshot of the current page
     */
    public static String takeScreenshot(WebDriver driver, String screenshotName) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String screenshotPath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + 
                                   screenshotName + "_" + timestamp + ".png";
            
            // Create screenshots directory if it doesn't exist
            new File(System.getProperty("user.dir") + File.separator + "screenshots").mkdirs();
            
            File destination = new File(screenshotPath);
            FileUtils.copyFile(source, destination);
            
            return screenshotPath;
        } catch (Exception e) {
            System.out.println("Exception occurred while taking screenshot: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Take screenshot of the current page using ThreadLocal WebDriver
     */
    public static String takeScreenshot(String screenshotName) {
        try {
            WebDriver driver = BaseTest.tlDriver.get();
            if (driver == null) {
                System.out.println("WebDriver not found in ThreadLocal");
                return null;
            }
            return takeScreenshot(driver, screenshotName);
        } catch (Exception e) {
            System.out.println("Exception occurred while taking screenshot: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Switch to frame by locator
     */
    public static void switchToFrame(WebDriver driver, By locator) {
        WebElement frameElement = waitForElementPresence(driver, locator);
        driver.switchTo().frame(frameElement);
    }
    
    /**
     * Switch to frame by index
     */
    public static void switchToFrame(WebDriver driver, int frameIndex) {
        driver.switchTo().frame(frameIndex);
    }
    
    /**
     * Switch to default content (exit frame)
     */
    public static void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }
    
    /**
     * Switch to alert and accept it
     */
    public static void acceptAlert(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }
    
    /**
     * Switch to alert and dismiss it
     */
    public static void dismissAlert(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
    }
    
    /**
     * Get alert text
     */
    public static String getAlertText(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
        return wait.until(ExpectedConditions.alertIsPresent()).getText();
    }
    
    /**
     * Navigate to URL
     */
    public static void navigateTo(WebDriver driver, String url) {
        driver.navigate().to(url);
    }
    
    /**
     * Get current URL
     */
    public static String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }
    
    /**
     * Get page title
     */
    public static String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }
    
    /**
     * Wait for specific time in milliseconds
     */
    public static void hardWait(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
