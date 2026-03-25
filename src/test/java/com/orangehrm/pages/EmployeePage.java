package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class EmployeePage {
    WebDriver driver;

    public EmployeePage(WebDriver driver){
        this.driver = driver;
    }

    By addButton = By.xpath("//button[normalize-space()='Add']");
    By firstNameEnter = By.name("firstName");
    By lastNameEnter = By.name("lastName");
    By employeeIdEnter = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
    By saveButton = By.xpath("//button[@type='submit']");

    By employeeList  = By.xpath("//a[.='Employee List']");
    By employeeNameSearchField = By.xpath("(//input[@placeholder='Type for hints...'])[1]");
    By employeeIdSearchField = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
    By searchButton = By.cssSelector("button[type='submit']");

    By deleteButton  = By.xpath("//button[.=' Yes, Delete ']");
    /* this part i need to navigate the list to get the right result and replace the 0000 with a specific id*/
//    By searchResult = By.className("oxd-table-body");
//    By idResult = By.xpath("//div[contains(text(),'0000')]");


    public String addEmployee(String employeeFirstName, String employeeLastName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("oxd-loading-spinner")
        ));

        wait.until(ExpectedConditions.elementToBeClickable(addButton));
        driver.findElement(addButton).click();
        driver.findElement(firstNameEnter).sendKeys(employeeFirstName);
        driver.findElement(lastNameEnter).sendKeys(employeeLastName);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("oxd-form-loader")
        ));
        // Lire l'ID auto-généré par OrangeHRM
        String generatedId = driver.findElement(employeeIdEnter).getAttribute("value");
        driver.findElement(saveButton).click();
        return generatedId;
    }

    public int searchEmployee(String firstName, String lastName, String id) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(employeeList));
        driver.findElement(employeeList).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("oxd-loading-spinner")
        ));

        wait.until(ExpectedConditions.elementToBeClickable(searchButton));

        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        driver.findElement(employeeNameSearchField).clear();
        driver.findElement(employeeNameSearchField).sendKeys(firstName + " " + lastName);
        driver.findElement(employeeIdSearchField).clear();
        driver.findElement(employeeIdSearchField).sendKeys(id);
        driver.findElement(searchButton).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("oxd-form-loader")
        ));
        int count = driver.findElements(By.className("oxd-table-row")).size();
        System.out.println("Rows found: " + count);
        return count;
    }

    public void deleteEmployee(String id) {
        driver.findElement(employeeList).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        driver.findElement(employeeIdSearchField).sendKeys(id);
        driver.findElement(searchButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='row'][.//div[text()='" + id + "']]")
        ));

        driver.findElement(
                By.xpath("//div[@role='row'][.//div[text()='" + id + "']]//button[2]")
        ).click();
        driver.findElement(deleteButton).click();
    }

    public void editEmployee(String id, String newFirstName, String newLastName) {
        driver.findElement(employeeList).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        driver.findElement(employeeIdSearchField).sendKeys(id);
        driver.findElement(searchButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='row'][.//div[text()='" + id + "']]")
        ));

        driver.findElement(
                By.xpath("//div[@role='row'][.//div[text()='" + id + "']]//button[1]")
        ).click();
        driver.findElement(firstNameEnter).clear();
        driver.findElement(firstNameEnter).sendKeys(newFirstName);
        driver.findElement(lastNameEnter).clear();
        driver.findElement(lastNameEnter).sendKeys(newLastName);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("oxd-form-loader")
        ));
        driver.findElement(saveButton).click();
    }

    public int searchByIdOnly(String id) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(employeeList));
        driver.findElement(employeeList).click();

        // Spinner de navigation
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("oxd-loading-spinner")
        ));

        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        driver.findElement(employeeIdSearchField).clear();
        driver.findElement(employeeIdSearchField).sendKeys(id);
        driver.findElement(searchButton).click();

        // Spinner des résultats ← MANQUAIT ICI
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("oxd-loading-spinner")
        ));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("oxd-form-loader")
        ));

        int count = driver.findElements(By.className("oxd-table-row")).size();
        System.out.println("Rows found by ID: " + count);
        return count;
    }


}
