package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {

    WebDriver driver;
    public DashboardPage(WebDriver driver){
        this.driver = driver;
    }

    By dashboardTitle = By.xpath("//h6[.='Dashboard']");
    By userDropDown = By.className("oxd-userdropdown-tab");
    By logOut = By.xpath("//a[.='Logout']");
    By pimButton = By.xpath("//a[@href='/web/index.php/pim/viewPimModule']");

    public void setUserDropDownAndLogout(){
        driver.findElement(userDropDown).click();
        driver.findElement(logOut).click();
    }

    public String readTitle(){
        return driver.findElement(dashboardTitle).getText();
    }

    public void clickOnPim(){
        driver.findElement(pimButton).click();
    }

}
