package stepdefs;

import framework.ConfigReader;
import pages.CartPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

// Class declaration that groups the related example logic in one place.
public class CartSteps {

    // Method definition that performs a specific part of the program workflow.
    private final CartPage cart = new CartPage();

    @Given("I navigate to the products page")
    // Method definition that performs a specific part of the program workflow.
    public void i_navigate_to_the_products_page() {
        cart.open(ConfigReader.baseUrl());
    }

    @When("I capture and add the first product to the cart")
    // Method definition that performs a specific part of the program workflow.
    public void i_capture_and_add_the_first_product() {
        cart.captureAndAddFirstProduct();
    }

    @And("I go to the cart page")
    // Method definition that performs a specific part of the program workflow.
    public void i_go_to_the_cart_page() {
        cart.goToCart(ConfigReader.baseUrl());
    }

    @Then("the cart should contain the product with correct name, price, and quantity")
    // Method definition that performs a specific part of the program workflow.
    public void the_cart_should_contain_the_product_with_correct_name_price_and_quantity() {
        // Store text data that will be processed by the program logic.
        String expectedName  = cart.getCapturedProductName();
        // Store text data that will be processed by the program logic.
        String expectedPrice = cart.getCapturedProductPrice();
        // Store text data that will be processed by the program logic.
        String actualName    = cart.isProductInCartByName(expectedName) ? expectedName : "";
        // Store text data that will be processed by the program logic.
        String actualPrice   = cart.getCartProductPrice();
        // Store text data that will be processed by the program logic.
        String actualQty     = cart.getCartProductQuantity();

        Assert.assertTrue(
            actualName.equals(expectedName) && actualPrice.equals(expectedPrice) && actualQty.equals("1"),
            "Cart mismatch â€” Name: [" + actualName + " vs " + expectedName + "] " +
            "Price: [" + actualPrice + " vs " + expectedPrice + "] " +
            "Qty: [" + actualQty + " vs 1]"
        );
    }

    @When("I remove the product from the cart")
    // Method definition that performs a specific part of the program workflow.
    public void i_remove_the_product_from_the_cart() {
        cart.removeFirstProduct();
    }

    @Then("the cart should be empty")
    // Method definition that performs a specific part of the program workflow.
    public void the_cart_should_be_empty() {
        Assert.assertTrue(cart.isCartEmpty(), "Cart is NOT empty after removing the product");
    }
}
