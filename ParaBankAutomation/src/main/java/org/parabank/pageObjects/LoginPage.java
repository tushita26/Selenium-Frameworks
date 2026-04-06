package org.parabank.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.parabank.base.Utilities;

public class LoginPage {
    private WebDriver driver;
    private Utilities utilities;

    // Locators
    private By usernameField = By.xpath("//input[@name='username']");
    private By passwordField = By.xpath("//input[@name='password']");
    private By loginButton = By.xpath("//input[@value='Log In']");
    private By errorMessage = By.xpath("//p[@class='error']");
    private By welcomeMessage = By.xpath("//h1[@class='title']");
    

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.utilities = new Utilities();
    }

    // Methods
    public void enterUsername(String username) {
        utilities.typeText(driver, usernameField, username);
    }

    public void enterPassword(String password) {
        utilities.typeText(driver, passwordField, password);
    }

    public void clickLoginButton() {
        utilities.click(driver, loginButton);
    }

    public String getErrorMessage() {
        return utilities.getText(driver, errorMessage);
    }

    public String getWelcomeMessage() {
        return utilities.getText(driver, welcomeMessage);
    }

    public boolean isLoginButtonDisplayed() {
        return utilities.isElementDisplayed(driver, loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}
