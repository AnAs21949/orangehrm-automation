package com.orangehrm.tests;

import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.EmployeePage;
import com.orangehrm.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EmployeeTest extends BaseTest {
    @BeforeMethod
    public void login() throws Exception {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(configReader.getProperty("username"), configReader.getProperty("password"));
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickOnPim();
    }

    @Test
    public void testEmployeeCRUD() throws Exception {
        test = extent.createTest("Employee CRUD - Create/Read/Update/Delete");
        EmployeePage employeePage = new EmployeePage(driver);
        // CREATE — OrangeHRM génère l'ID
        String id = employeePage.addEmployee("Anas", "Test");
        System.out.println("Generated ID: " + id);

        // READ
        Assert.assertTrue(employeePage.searchByIdOnly(id) > 0);


        // UPDATE — cherche uniquement par ID car le nom hints peut varier
        employeePage.editEmployee(id, "Anas", "Updated");
        Assert.assertTrue(employeePage.searchByIdOnly(id) > 1);

        // DELETE
        employeePage.deleteEmployee(id);
        Assert.assertEquals(employeePage.searchEmployee("Anas", "Updated", id), 1);
    }
}
