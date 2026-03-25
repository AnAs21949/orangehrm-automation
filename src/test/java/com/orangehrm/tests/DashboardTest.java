package com.orangehrm.tests;
import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {
    @BeforeMethod
    public void login() throws Exception {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(configReader.getProperty("username"), configReader.getProperty("password"));
    }

    @Test
    public void testDropDownAndLogOut() throws Exception{
        DashboardPage dashboardPage = new DashboardPage(driver);

        dashboardPage.setUserDropDownAndLogout();
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
    }
    @Test
    public void testDashboardTitle() throws Exception{
        DashboardPage dashboardPage = new DashboardPage(driver);

        String title = dashboardPage.readTitle();
        Assert.assertEquals(title, "Dashboard");
    }

    @Test
    public void testPimClick() throws Exception{
        DashboardPage dashboardPage = new DashboardPage(driver);

        dashboardPage.clickOnPim();
        Assert.assertTrue(driver.getCurrentUrl().contains("pim")  );
    }
}
