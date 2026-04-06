package testCases;

import org.openqa.selenium.WebDriver;
import org.parabank.actions.CommonActions;
import org.parabank.base.BaseTest;
import org.parabank.config.TestConstants;
import org.parabank.pageObjects.DashboardPage;
import org.parabank.testdata.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    private CommonActions commonActions;
    private WebDriver driver;

    @Test(description = "Verify user can login with valid credentials")
    public void testLoginWithValidCredentials() {
        // Get driver for current thread using utility method
        driver = BaseTest.getDriverByThreadId(Thread.currentThread().getId());
        System.out.println("LoginTests - " + BaseTest.getCurrentThreadInfo());
        
        commonActions = new CommonActions(driver);

        // Arrange
        String username = TestConstants.VALID_USERNAME;
        String password = TestConstants.VALID_PASSWORD;

        // Act
        DashboardPage dashboardPage = commonActions.loginWithValidCredentials(username, password);

        // Assert
        Assert.assertTrue(dashboardPage.isDashboardLoaded(), "Dashboard should be loaded after successful login");
        BaseTest.tlExtentTest.get().pass("User successfully logged in with valid credentials : "+ username);
    }

    @Test(description = "Verify user cannot login with invalid credentials")
    public void testLoginWithInvalidCredentials() {
        driver = BaseTest.tlDriver.get();
        commonActions = new CommonActions(driver);

        // Arrange
        String username = TestConstants.INVALID_USERNAME;
        String password = TestConstants.INVALID_PASSWORD;

        // Act
        commonActions.loginWithInvalidCredentials(username, password);

        // Assert
        String errorMessage = commonActions.getLoginErrorMessage();
        Assert.assertNotNull(errorMessage, "Error message should be displayed");
        BaseTest.tlExtentTest.get().pass("Error message displayed for invalid credentials : " + username);
    }

    @Test(dataProvider = "validLoginCredentials", dataProviderClass = TestDataProvider.class,
            description = "Verify login with multiple valid credentials")
    public void testLoginWithMultipleValidCredentials(String username, String password) {
        driver = BaseTest.tlDriver.get();
        commonActions = new CommonActions(driver);

        // Act
        DashboardPage dashboardPage = commonActions.loginWithValidCredentials(username, password);

        // Assert
        Assert.assertTrue(dashboardPage.isDashboardLoaded(), 
            "Dashboard should load for user: " + username);
        BaseTest.tlExtentTest.get().pass("Logged in successfully with username: " + username);
    }

    @Test(dataProvider = "invalidLoginCredentials", dataProviderClass = TestDataProvider.class,
            description = "Verify login fails with invalid credentials")
    public void testLoginWithMultipleInvalidCredentials(String username, String password) {
        driver = BaseTest.tlDriver.get();
        commonActions = new CommonActions(driver);

        // Act
        commonActions.loginWithInvalidCredentials(username, password);

        // Assert
        Assert.assertTrue(commonActions.isLoginPageLoaded(),
            "Login page should still be displayed for failed login");
        BaseTest.tlExtentTest.get().pass("Login failed as expected for invalid credentials");
    }

    @Test(description = "Verify user can logout")
    public void testLogout() {
        driver = BaseTest.tlDriver.get();
        commonActions = new CommonActions(driver);

        // Arrange - Login first
        DashboardPage dashboardPage = commonActions.loginWithValidCredentials(
            TestConstants.VALID_USERNAME, TestConstants.VALID_PASSWORD);
        Assert.assertTrue(dashboardPage.isDashboardLoaded(), "Dashboard should be loaded");

        // Act
        commonActions.logout();

        // Assert
        Assert.assertTrue(commonActions.isLoginPageLoaded(), "Login page should be displayed after logout");
        BaseTest.tlExtentTest.get().pass("User successfully logged out");
    }

    @Test(description = "Verify ParaBank URL is correct")
    public void testParaBankURL() {
        driver = BaseTest.tlDriver.get();
        commonActions = new CommonActions(driver);

        // Act
        commonActions.navigateToParaBank();
        String currentURL = commonActions.getCurrentURL();

        // Assert
        Assert.assertTrue(currentURL.contains("parabank"), "URL should contain 'parabank'");
        BaseTest.tlExtentTest.get().pass("ParaBank URL is correct: " + currentURL);
    }
}
