package org.parabank.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.parabank.base.Utilities;

public class DashboardPage {
    private WebDriver driver;
    private Utilities utilities;

    // Locators
    private By welcomeHeading = By.xpath("//h1[@class='title']");
    private By accountsOverviewLink = By.xpath("//a[contains(text(),'Accounts Overview')]");
    private By transferFundsLink = By.xpath("//a[contains(text(),'Transfer Funds')]");
    private By billPayLink = By.xpath("//a[contains(text(),'Bill Pay')]");
    private By findTransactionsLink = By.xpath("//a[contains(text(),'Find Transactions')]");
    private By logoutLink = By.xpath("//a[contains(text(),'Log Out')]");
    private By accountTable = By.xpath("//table[@class='table']");

    // Constructor
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.utilities = new Utilities();
    }

    // Methods
    public String getWelcomeMessage() {
        return utilities.getText(driver, welcomeHeading);
    }

    public void clickAccountsOverview() {
        utilities.click(driver, accountsOverviewLink);
    }

    public void clickTransferFunds() {
        utilities.click(driver, transferFundsLink);
    }

    public void clickBillPay() {
        utilities.click(driver, billPayLink);
    }

    public void clickFindTransactions() {
        utilities.click(driver, findTransactionsLink);
    }

    public void logout() {
        utilities.click(driver, logoutLink);
    }

    public boolean isAccountTableDisplayed() {
        return utilities.isElementDisplayed(driver, accountTable);
    }

    public boolean isDashboardLoaded() {
        return utilities.isElementPresent(driver, welcomeHeading) && 
               utilities.isElementDisplayed(driver, welcomeHeading);
    }
}
