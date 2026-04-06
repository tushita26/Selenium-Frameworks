package org.parabank.actions;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.parabank.base.BaseTest;
import org.parabank.base.Utilities;
import org.parabank.config.TestConstants;
import org.parabank.pageObjects.DashboardPage;
import org.parabank.pageObjects.LoginPage;

public class CommonActions {
    private WebDriver driver;
    private Utilities utilities;

    // Constructor
    public CommonActions(WebDriver driver) {
        this.driver = driver;
        this.utilities = new Utilities();
    }

    /**
     * Navigate to ParaBank application
     */
    public void navigateToParaBank() {
        BaseTest.tlExtentTest.get().log(Status.INFO, "Navigating to ParaBank application at URL: " + TestConstants.BASE_URL);
        utilities.navigateTo(driver, TestConstants.BASE_URL);
    }

    /**
     * Login with valid credentials
     */
    public DashboardPage loginWithValidCredentials(String username, String password) {
        BaseTest.tlExtentTest.get().log(Status.INFO, "Logging in with username: " + username);
        navigateToParaBank();
        LoginPage loginPage = new LoginPage(driver);
        BaseTest.tlExtentTest.get().log(Status.INFO, "Entering credentials and submitting login form");
        loginPage.login(username, password);
        utilities.hardWait(2);
        BaseTest.tlExtentTest.get().log(Status.PASS, "✓ Login completed successfully");
        return new DashboardPage(driver);
    }

    /**
     * Login with invalid credentials
     */
    public LoginPage loginWithInvalidCredentials(String username, String password) {
        BaseTest.tlExtentTest.get().log(Status.INFO, "Attempting login with invalid credentials - Username: " + username);
        navigateToParaBank();
        LoginPage loginPage = new LoginPage(driver);
        BaseTest.tlExtentTest.get().log(Status.INFO, "Entering invalid credentials and submitting form");
        loginPage.login(username, password);
        utilities.hardWait(2);
        BaseTest.tlExtentTest.get().log(Status.INFO, "✓ Login attempt completed - expecting error message");
        return loginPage;
    }

    /**
     * Verify login page is loaded
     */
    public boolean isLoginPageLoaded() {
        LoginPage loginPage = new LoginPage(driver);
        return loginPage.isLoginButtonDisplayed();
    }

    /**
     * Verify dashboard is loaded after login
     */
    public boolean isDashboardLoaded() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        return dashboardPage.isDashboardLoaded();
    }

    /**
     * Logout from application
     */
    public void logout() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.logout();
        utilities.hardWait(2);
    }

    /**
     * Get page title
     */
    public String getPageTitle() {
        return utilities.getPageTitle(driver);
    }

    /**
     * Get current URL
     */
    public String getCurrentURL() {
        return utilities.getCurrentUrl(driver);
    }

    /**
     * Refresh page
     */
    public void refreshPage() {
        driver.navigate().refresh();
        utilities.hardWait(2);
    }

    /**
     * Navigate back
     */
    public void navigateBack() {
        driver.navigate().back();
        utilities.hardWait(2);
    }

    /**
     * Navigate forward
     */
    public void navigateForward() {
        driver.navigate().forward();
        utilities.hardWait(2);
    }

    /**
     * Verify error message on login page
     */
    public String getLoginErrorMessage() {
        LoginPage loginPage = new LoginPage(driver);
        return loginPage.getErrorMessage();
    }
}
