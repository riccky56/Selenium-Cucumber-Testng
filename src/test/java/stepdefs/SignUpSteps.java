package stepdefs;

import context.TestContext;
import framework.ConfigReader;
import pages.SignUp.AccountInfoPage;
import pages.SignUp.SignUp;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.datafaker.Faker;
import org.testng.Assert;

// Class declaration that groups the related example logic in one place.
public class SignUpSteps {

    // Method definition that performs a specific part of the program workflow.
    private final SignUp          sign            = new SignUp();
    // Method definition that performs a specific part of the program workflow.
    private final AccountInfoPage accountInfoPage = new AccountInfoPage();
    // Method definition that performs a specific part of the program workflow.
    private final Faker           faker           = new Faker();

    private String pendingEmail;

    @Given("I am on the SignUp page")
    // Method definition that performs a specific part of the program workflow.
    public void i_am_on_the_SignUp_page() {
        sign.open(ConfigReader.baseUrl());
    }

    @When("I enter name {string} and email {string}")
    // Method definition that performs a specific part of the program workflow.
    public void i_enter_name_and_email(String name, String email) {
        pendingEmail = faker.internet().safeEmailAddress();
        sign.signUp(name, pendingEmail);
    }

    @Then("I should see the account information form")
    // Method definition that performs a specific part of the program workflow.
    public void i_should_see_the_account_information_form() {
        Assert.assertTrue(accountInfoPage.isTitleRadioVisible(), "Account Information form did not load");
    }

    @When("I fill account info with title {string} password {string} day {string} month {string} year {string}")
    // Method definition that performs a specific part of the program workflow.
    public void i_fill_account_info(String title, String password, String day, String month, String year) {
        // Check the condition before deciding whether this block should run.
        if (!password.isEmpty() && pendingEmail != null) {
            TestContext.setCredentials(pendingEmail, password);
        }
        accountInfoPage.fillAccountInfo(title, password, day, month, year);
    }

    @And("I fill address info firstName {string} lastName {string} company {string} address {string} country {string} state {string} city {string} zipcode {string} mobile {string}")
    // Method definition that performs a specific part of the program workflow.
    public void i_fill_address_info(String firstName, String lastName, String company, String address,
                                    String country, String state, String city, String zipcode, String mobile) {
        accountInfoPage.fillAddressInfo(firstName, lastName, company, address, country, state, city, zipcode, mobile);
    }

    @And("I click create account")
    // Method definition that performs a specific part of the program workflow.
    public void i_click_create_account() {
        accountInfoPage.clickCreateAccount();
    }

    @Then("I should see account created page")
    // Method definition that performs a specific part of the program workflow.
    public void i_should_see_account_created_page() {
        Assert.assertEquals(accountInfoPage.getAccountCreatedText(), "ACCOUNT CREATED!", "Account Created page did not appear");
    }

    @Then("I should see signup error {string}")
    // Method definition that performs a specific part of the program workflow.
    public void i_should_see_signup_error(String error) {
        Assert.assertTrue(sign.isSignUpErrorVisible(), "Expected error '" + error + "' did not appear");
    }

    @Then("I should remain on the signup page")
    // Method definition that performs a specific part of the program workflow.
    public void i_should_remain_on_the_signup_page() {
        Assert.assertTrue(sign.isOnSignUpPage(), "Expected to remain on signup page");
    }

    @Then("I should remain on the account info page")
    // Method definition that performs a specific part of the program workflow.
    public void i_should_remain_on_the_account_info_page() {
        Assert.assertTrue(accountInfoPage.isTitleRadioVisible(), "Title radio button is not visible â€” navigated away from account info page");
    }

    @When("I SignUp with user {string} and useremail {string}")
    // Method definition that performs a specific part of the program workflow.
    public void i_SignUp_with_user_and_useremail(String username, String email) {
        sign.signUp(username, email);
    }

    @Then("I should watch {string}")
    // Method definition that performs a specific part of the program workflow.
    public void i_should_watch(String expectedResult) {
        Assert.assertTrue(accountInfoPage.isTitleRadioVisible(), expectedResult + " didn't load");
    }
}
