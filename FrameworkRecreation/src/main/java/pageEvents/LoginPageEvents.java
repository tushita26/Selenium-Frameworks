package main.java.pageEvents;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import main.java.pageObjects.LoginPageElements;
import main.java.utils.ElementFetch;
import org.testng.Assert;
import test.java.BaseTest;
import test.java.BaseTest.*;

public class LoginPageEvents {

    public void verifyLoginPageOpenOrNot() {
        try {
            ElementFetch elementFetch = new ElementFetch();
            Assert.assertTrue(elementFetch.getListWebElement("XPATH", LoginPageElements.loginText).size() > 0);
            BaseTest.logger.log(Status.PASS, "Login Page opened successfully");
        } catch (Exception e) {
            BaseTest.logger.log(Status.FAIL, "Issue in navigating to Sign in page\n" + e);
        }
    }

    public void enterEmailId() throws Exception{
            ElementFetch elementFetch1 = new ElementFetch();
            elementFetch1.getWebElement("ID", LoginPageElements.emailAddress).sendKeys("scorpio26.tushi@gmail.com");
            BaseTest.logger.log(Status.PASS, "Entered email id");
    }
}
