package stepdefs;

import context.TestContext;
import framework.ConfigReader;
import pages.LoginPage;
import io.cucumber.java.en.*;
import org.testng.Assert;

// Class declaration that groups the related example logic in one place.
public class LoginSteps {

    // Method definition that performs a specific part of the program workflow.
    private final LoginPage login = new LoginPage();

    @Given("I am on the login page")
    // Method definition that performs a specific part of the program workflow.
    public void i_am_on_the_login_page() {
        login.open(ConfigReader.baseUrl());
    }

    @When("I login with email {string} and password {string}")
    // Method definition that performs a specific part of the program workflow.
    public void i_login_with_email_and_password(String email, String pass) {
        login.loginAs(email, pass);
    }

    @When("I login with the registered account credentials")
    // Method definition that performs a specific part of the program workflow.
    public void i_login_with_the_registered_account_credentials() {
        login.loginAs(TestContext.getEmail(), TestContext.getPassword());
    }

    @Then("I should be logged in")
    // Method definition that performs a specific part of the program workflow.
    public void i_should_be_logged_in() {
        Assert.assertTrue(login.isLoggedIn(),
            "Expected 'Logged in as' header but login failed for: " + TestContext.getEmail());
    }

    @Then("I should be logged in as {string}")
    // Method definition that performs a specific part of the program workflow.
    public void i_should_be_logged_in_as(String username) {
        Assert.assertTrue(login.isLoggedIn(),
            "Expected to be logged in as '" + username + "' but 'Logged in as' header was not found");
    }

    @Then("I should see login error {string}")
    // Method definition that performs a specific part of the program workflow.
    public void i_should_see_login_error(String expectedMessage) {
        Assert.assertTrue(login.isLoginErrorVisible(),
            "Expected error message '" + expectedMessage + "' was not displayed");
    }
}
