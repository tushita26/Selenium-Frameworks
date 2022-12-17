package main.java.pageEvents;

import org.testng.Assert;
import main.java.pageObjects.LoginPageElements;
import main.java.utils.ElementFetch;

public class LoginPageEvents {

    public void verifyLoginPageOpenOrNot() {
        ElementFetch elementFetch = new ElementFetch();
        Assert.assertTrue(elementFetch.getListWebElement("XPATH", LoginPageElements.loginText).size()>0,"Login page did not open");
    }

    public void enterEmailId() {
        ElementFetch elementFetch = new ElementFetch();
        elementFetch.getWebElement("ID", LoginPageElements.emailAddress).sendKeys("scorpio26.tushi@gmail.com");
    }
}
