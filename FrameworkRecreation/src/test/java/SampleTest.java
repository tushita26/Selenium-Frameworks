package test.java;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import main.java.pageEvents.HomePageEvents;
import main.java.pageEvents.LoginPageEvents;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

public class SampleTest extends BaseTest{


    @Test
    public void sampleMethodForEnteringEmail() throws Exception {
        HomePageEvents homePageEvents = new HomePageEvents();
        homePageEvents.clickOnSignInButton();

        LoginPageEvents loginPageEvents = new LoginPageEvents();
        loginPageEvents.verifyLoginPageOpenOrNot();

        loginPageEvents.enterEmailId();
    }
}
