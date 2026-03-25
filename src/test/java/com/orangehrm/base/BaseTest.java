package com.orangehrm.base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.orangehrm.utils.ConfigReader;
import com.orangehrm.utils.ExtentManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTest {

    protected static ExtentReports extent;
    protected ExtentTest test;
    protected WebDriver driver;
    protected ConfigReader configReader;

    @BeforeMethod
    public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        configReader = new ConfigReader();

        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(configReader.getProperty("url"));
    }
    @AfterMethod
    public void tearDown(ITestResult result) { // ← ITestResult = résultat du test
        if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test passed");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            test.fail("Test failed: " + result.getThrowable());
        }
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeSuite
    public void setUpReport() {
        extent = ExtentManager.createInstance();
    }

    @AfterSuite
    public void tearDownReport() {
        ExtentManager.flush();
    }
}

