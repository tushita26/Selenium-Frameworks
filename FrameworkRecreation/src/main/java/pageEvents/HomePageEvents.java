package main.java.pageEvents;


import com.aventstack.extentreports.Status;
import main.java.pageObjects.HomePageElements;
import main.java.utils.ElementFetch;
import test.java.BaseTest;

public class HomePageEvents {

    public void clickOnSignInButton() {
        try {
            ElementFetch elementFetch = new ElementFetch();
            elementFetch.getWebElement("XPATH", HomePageElements.signInButton).click();
            BaseTest.logger.log(Status.PASS, "Clicked on Sign in button successfully");
        }catch (Exception e) {
            BaseTest.logger.log(Status.FAIL, "Issue while clicking on Sign In button\n" + e);
        }
    }
}
