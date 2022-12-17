package main.java.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static test.java.BaseTest.driver;

public class SuiteListener implements ITestListener, IAnnotationTransformer {
@Override
    public void onTestStart(ITestResult iTestResult) {

}
    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }
    @Override
    public void onTestFailure(ITestResult iTestResult) {
    String fileName = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + iTestResult.getMethod().getMethodName();
    File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(f, new File(fileName + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(main.java.utils.RetryAnalyzer.class);
    }
}
