package org.parabank.testdata;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "validLoginCredentials")
    public Object[][] getValidLoginCredentials() {
        return new Object[][] {
                {"john", "demo"},
                {"admin", "admin123"}
        };
    }

    @DataProvider(name = "invalidLoginCredentials")
    public Object[][] getInvalidLoginCredentials() {
        return new Object[][] {
                {"invalid", "wrong"},
                {"", ""},
                {"user123", "pass456"},
                {"test", "1234"}
        };
    }

    @DataProvider(name = "emptyCredentials")
    public Object[][] getEmptyCredentials() {
        return new Object[][] {
                {"", "password"},
                {"username", ""},
                {"", ""}
        };
    }

    @DataProvider(name = "specialCharacters")
    public Object[][] getSpecialCharacterCredentials() {
        return new Object[][] {
                {"user@123", "pass#456"},
                {"admin$", "pwd%"},
                {"test&user", "pass*123"}
        };
    }
}
