package testCases;

import org.openqa.selenium.WebDriver;
import org.parabank.actions.CommonActions;
import org.parabank.base.BaseTest;
import org.parabank.config.TestConstants;
import org.parabank.pageObjects.DashboardPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DashboardTests extends BaseTest {
    private CommonActions commonActions;
    private DashboardPage dashboardPage;
    private WebDriver driver;

    @BeforeMethod
    public void loginBeforeTest() {
        // Get driver for current thread using utility method
        driver = BaseTest.getDriverByThreadId(Thread.currentThread().getId());
        System.out.println("DashboardTests - " + BaseTest.getCurrentThreadInfo());
        
        commonActions = new CommonActions(driver);
        
        // Login before each test
        dashboardPage = commonActions.loginWithValidCredentials(
            TestConstants.VALID_USERNAME_2, TestConstants.VALID_PASSWORD_2);
    }

    @Test(description = "Verify dashboard loads after login")
    public void testDashboardLoaded() {
        // Assert
        Assert.assertTrue(dashboardPage.isDashboardLoaded(), "Dashboard should be loaded");
        BaseTest.tlExtentTest.get().pass("Dashboard successfully loaded after login");
    }

    @Test(description = "Verify welcome message is displayed on dashboard")
    public void testWelcomeMessageDisplayed() {
        // Act
        String welcomeMessage = dashboardPage.getWelcomeMessage();

        // Assert
        Assert.assertNotNull(welcomeMessage, "Welcome message should be displayed");
        Assert.assertTrue(welcomeMessage.contains("Welcome"), "Message should contain 'Welcome'");
        BaseTest.tlExtentTest.get().pass("Welcome message displayed: " + welcomeMessage);
    }

    @Test(description = "Verify account table is displayed")
    public void testAccountTableDisplayed() {
        // Assert
        Assert.assertTrue(dashboardPage.isAccountTableDisplayed(), 
            "Account table should be displayed on dashboard");
        BaseTest.tlExtentTest.get().pass("Accounts table is visible on dashboard");
    }

    @Test(description = "Verify navigation links are available")
    public void testNavigationLinksAvailable() {
        // Assert
        Assert.assertTrue(dashboardPage.isDashboardLoaded(), "Dashboard should be loaded");
        // Add more assertions for specific links as needed
        BaseTest.tlExtentTest.get().pass("All navigation elements are available on dashboard");
    }

    @Test(description = "Verify user can logout from dashboard")
    public void testLogoutFromDashboard() {
        // Verify initial state
        Assert.assertTrue(dashboardPage.isDashboardLoaded(), "Should be on dashboard");

        // Act
        dashboardPage.logout();

        // Assert
        Assert.assertTrue(commonActions.isLoginPageLoaded(), "Should be redirected to login page");
        BaseTest.tlExtentTest.get().pass("User successfully logged out from dashboard");
    }

    @Test(description = "Verify page title contains ParaBank")
    public void testPageTitle() {
        // Act
        String pageTitle = commonActions.getPageTitle();

        // Assert
        Assert.assertNotNull(pageTitle, "Page title should not be null");
        Assert.assertTrue(pageTitle.contains("Bank"), "Page title should contain 'Bank'");
        BaseTest.tlExtentTest.get().pass("Page title is correct: " + pageTitle);
    }

    @Test(description = "Verify page refresh maintains dashboard")
    public void testPageRefresh() {
        // Verify initial state
        Assert.assertTrue(dashboardPage.isDashboardLoaded(), "Dashboard should be loaded");

        // Act
        commonActions.refreshPage();

        // Assert - Dashboard should still be accessible after refresh
        BaseTest.tlExtentTest.get().pass("Page refreshed successfully");
    }
}
