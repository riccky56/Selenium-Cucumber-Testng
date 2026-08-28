package pages.SignUp;

import framework.BasePage;
import org.openqa.selenium.By;

// Class declaration that groups the related example logic in one place.
public class AccountInfoPage extends BasePage {

    // Method definition that performs a specific part of the program workflow.
    private final By titleMr          = By.id("id_gender1");
    // Method definition that performs a specific part of the program workflow.
    private final By titleMrs         = By.id("id_gender2");
    // Method definition that performs a specific part of the program workflow.
    private final By password         = By.id("password");
    // Method definition that performs a specific part of the program workflow.
    private final By days             = By.id("days");
    // Method definition that performs a specific part of the program workflow.
    private final By months           = By.id("months");
    // Method definition that performs a specific part of the program workflow.
    private final By years            = By.id("years");
    // Method definition that performs a specific part of the program workflow.
    private final By firstName        = By.id("first_name");
    // Method definition that performs a specific part of the program workflow.
    private final By lastName         = By.id("last_name");
    // Method definition that performs a specific part of the program workflow.
    private final By company          = By.id("company");
    // Method definition that performs a specific part of the program workflow.
    private final By address1         = By.id("address1");
    // Method definition that performs a specific part of the program workflow.
    private final By country          = By.id("country");
    // Method definition that performs a specific part of the program workflow.
    private final By state            = By.id("state");
    // Method definition that performs a specific part of the program workflow.
    private final By city             = By.id("city");
    // Method definition that performs a specific part of the program workflow.
    private final By zipcode          = By.id("zipcode");
    // Method definition that performs a specific part of the program workflow.
    private final By mobileNumber     = By.id("mobile_number");
    // Method definition that performs a specific part of the program workflow.
    private final By createAccountBtn = By.cssSelector("button[data-qa='create-account']");
    // Method definition that performs a specific part of the program workflow.
    private final By accountCreated   = By.cssSelector("h2[data-qa='account-created']");

    // Method definition that performs a specific part of the program workflow.
    public void fillAccountInfo(String title, String pwd, String day, String month, String year) {
        // Check the condition before deciding whether this block should run.
        if (title.equalsIgnoreCase("Mr")) {
            click(titleMr);
        } else {
            click(titleMrs);
        }
        type(password, pwd);
        selectByValue(days, day);
        selectByVisibleText(months, month);
        selectByValue(years, year);
    }

    // Method definition that performs a specific part of the program workflow.
    public void fillAddressInfo(String fName, String lName, String comp, String addr,
                                String ctry, String st, String cty, String zip, String mobile) {
        type(firstName, fName);
        type(lastName, lName);
        type(company, comp);
        type(address1, addr);
        selectByVisibleText(country, ctry);
        type(state, st);
        type(city, cty);
        type(zipcode, zip);
        type(mobileNumber, mobile);
    }

    // Method definition that performs a specific part of the program workflow.
    public void clickCreateAccount() {
        jsClick(createAccountBtn);
    }

    // Method definition that performs a specific part of the program workflow.
    public String getAccountCreatedText() {
        // Return the final result back to the caller.
        return getText(accountCreated);
    }

    // Method definition that performs a specific part of the program workflow.
    public boolean isTitleRadioVisible() {
        // Return the final result back to the caller.
        return $(titleMr).isDisplayed();
    }

    // Method definition that performs a specific part of the program workflow.
    public boolean isPasswordFieldVisible() {
        // Return the final result back to the caller.
        return $(password).isDisplayed();
    }
}
