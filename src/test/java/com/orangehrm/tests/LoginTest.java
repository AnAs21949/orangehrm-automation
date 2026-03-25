package com.orangehrm.tests;

import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test
    public void testValidLogin() throws Exception{
        test = extent.createTest("Valid Login - Admin User");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(configReader.getProperty("username"), configReader.getProperty("password"));
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
    }

}
