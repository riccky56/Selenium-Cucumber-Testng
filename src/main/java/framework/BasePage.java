package framework;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    // Method definition that performs a specific part of the program workflow.
    protected BasePage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // Method definition that performs a specific part of the program workflow.
    protected WebElement $(By locator) {
        // Return the final result back to the caller.
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Method definition that performs a specific part of the program workflow.
    protected void click(By locator) {
        // Click the target element to continue the workflow.
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // Method definition that performs a specific part of the program workflow.
    protected void type(By locator, String text) {
        // Store the web element so actions can be performed on it.
        WebElement el = $(locator);
        el.clear();
        // Type the required value into the target input field.
        el.sendKeys(text);
    }

    // Method definition that performs a specific part of the program workflow.
    protected void selectByVisibleText(By locator, String text) {
        new Select($(locator)).selectByVisibleText(text);
    }

    // Method definition that performs a specific part of the program workflow.
    protected void selectByValue(By locator, String value) {
        new Select($(locator)).selectByValue(value);
    }

    // Method definition that performs a specific part of the program workflow.
    protected void jsClick(By locator) {
        // Store the web element so actions can be performed on it.
        WebElement el = driver.findElement(locator);
        // Click the target element to continue the workflow.
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    // Method definition that performs a specific part of the program workflow.
    protected String getText(By locator) {
        // Return the final result back to the caller.
        return $(locator).getText();
    }

    // Method definition that performs a specific part of the program workflow.
    protected List<WebElement> findAll(By locator) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        // Return the final result back to the caller.
        return driver.findElements(locator);
    }

    // Method definition that performs a specific part of the program workflow.
    public String getTitle() { return driver.getTitle(); }
}
