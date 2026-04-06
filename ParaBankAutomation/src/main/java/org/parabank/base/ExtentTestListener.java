package org.parabank.base;

import com.aventstack.extentreports.Status;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestListener implements ITestListener {
    
    @Override
    public void onTestStart(ITestResult result) {
        BaseTest.tlExtentTest.set(
            BaseTest.extentReports.createTest(result.getClass().getName() + "." + result.getMethod().getMethodName())
        );
        BaseTest.tlExtentTest.get().log(Status.INFO, "Test Started: " + result.getClass().getName() + "." + result.getMethod().getMethodName());
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        BaseTest.tlExtentTest.get().log(Status.PASS, "Test Passed: " + result.getClass().getName() + "." + result.getMethod().getMethodName());
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        BaseTest.tlExtentTest.get().log(Status.FAIL, "Test Failed: " + result.getClass().getName() + "." + result.getMethod().getMethodName());
        BaseTest.tlExtentTest.get().log(Status.FAIL, "Failure Reason: " + result.getThrowable().getMessage());
        
        // Capture screenshot on failure
        try {
            WebDriver driver = BaseTest.tlDriver.get();
            if (driver != null) {
                String screenshotPath = Utilities.takeScreenshot(driver, result.getMethod().getMethodName());
                if (screenshotPath != null) {
                    BaseTest.tlExtentTest.get().addScreenCaptureFromPath(screenshotPath);
                }
            }
        } catch (Exception e) {
            BaseTest.tlExtentTest.get().log(Status.WARNING, "Failed to capture screenshot: " + e.getMessage());
        }
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        BaseTest.tlExtentTest.get().log(Status.SKIP, "Test Skipped: " + result.getClass().getName() + "." + result.getMethod().getMethodName());
    }
    
    @Override
    public void onStart(ITestContext context) {
        // Initialize extent reports
        BaseTest.initializeExtentReports();
        // Increment context counter for parallel tracking
        BaseTest.incrementTestContextCount();
    }
    
    @Override
    public void onFinish(ITestContext context) {
        // Log summary only if tests were executed
        ExtentTest test = BaseTest.tlExtentTest.get();
        if (test != null) {
            int totalTests = context.getAllTestMethods().length;
            int passedTests = context.getPassedTests().size();
            int failedTests = context.getFailedTests().size();
            int skippedTests = context.getSkippedTests().size();
            
            test.log(Status.INFO, 
                "Test Summary - Total: " + totalTests + ", Passed: " + passedTests + 
                ", Failed: " + failedTests + ", Skipped: " + skippedTests);
        }
        
    }
}
