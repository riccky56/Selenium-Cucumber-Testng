package pages;

import framework.BasePage;
import framework.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

// Class declaration that groups the related example logic in one place.
public class CartPage extends BasePage {

    // Method definition that performs a specific part of the program workflow.
    private final By productCards      = By.cssSelector("div.product-image-wrapper");
    // Method definition that performs a specific part of the program workflow.
    private final By viewCartLink      = By.cssSelector(".modal-body a[href='/view_cart']");
    // Method definition that performs a specific part of the program workflow.
    private final By cartProductNames  = By.cssSelector("td.cart_description h4 a");
    // Method definition that performs a specific part of the program workflow.
    private final By cartProductPrices = By.cssSelector("td.cart_price p");
    // Method definition that performs a specific part of the program workflow.
    private final By cartProductQtys   = By.cssSelector("td.cart_quantity button");
    // Method definition that performs a specific part of the program workflow.
    private final By deleteButtons     = By.cssSelector("td.cart_delete a.cart_quantity_delete");
    // Method definition that performs a specific part of the program workflow.
    private final By emptyCartMsg      = By.id("empty_cart");

    private String capturedProductName;
    private String capturedProductPrice;
    private String capturedProductId;

    // Method definition that performs a specific part of the program workflow.
    public void open(String baseUrl) {
        DriverFactory.getDriver().get(baseUrl + "products");
    }

    // Method definition that performs a specific part of the program workflow.
    public void goToCart(String baseUrl) {
        DriverFactory.getDriver().get(baseUrl + "view_cart");
    }

    // Method definition that performs a specific part of the program workflow.
    public void captureAndAddFirstProduct() {
        // Store the web element so actions can be performed on it.
        WebElement firstCard = driver.findElements(productCards).get(0);
        capturedProductName  = firstCard.findElement(By.cssSelector("div.productinfo p")).getText().trim();
        capturedProductPrice = firstCard.findElement(By.cssSelector("div.productinfo h2")).getText().trim();
        // Store the web element so actions can be performed on it.
        WebElement addBtn    = firstCard.findElement(By.cssSelector("a.add-to-cart"));
        capturedProductId    = addBtn.getAttribute("data-product-id");
        new Actions(driver).moveToElement(firstCard).perform();
        // Click the target element to continue the workflow.
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);
        jsClick(viewCartLink);
    }

    // Method definition that performs a specific part of the program workflow.
    public String getCapturedProductName()  { return capturedProductName; }
    // Method definition that performs a specific part of the program workflow.
    public String getCapturedProductPrice() { return capturedProductPrice; }
    // Method definition that performs a specific part of the program workflow.
    public String getCapturedProductId()    { return capturedProductId; }

    // Method definition that performs a specific part of the program workflow.
    public void removeFirstProduct() {
        click(deleteButtons);
    }

    // Method definition that performs a specific part of the program workflow.
    public boolean isProductInCartByName(String name) {
        List<WebElement> elements = findAll(cartProductNames);
        // Loop through each element one by one.
        for (WebElement el : elements) {
            // Check the condition before deciding whether this block should run.
            if (el.getText().trim().equals(name)) return true;
        }
        // Return the final result back to the caller.
        return false;
    }

    // Method definition that performs a specific part of the program workflow.
    public String getCartProductPrice() {
        // Return the final result back to the caller.
        return findAll(cartProductPrices).get(0).getText().trim();
    }

    // Method definition that performs a specific part of the program workflow.
    public String getCartProductQuantity() {
        // Return the final result back to the caller.
        return findAll(cartProductQtys).get(0).getText().trim();
    }

    // Method definition that performs a specific part of the program workflow.
    public boolean isCartEmpty() {
        // Return the final result back to the caller.
        return $(emptyCartMsg).isDisplayed();
    }
}
