package pages.SignUp;

import framework.BasePage;
import framework.DriverFactory;
import org.openqa.selenium.By;

// Class declaration that groups the related example logic in one place.
public class SignUp extends BasePage {

    // Method definition that performs a specific part of the program workflow.
    private final By user       = By.cssSelector("input[placeholder='Name']");
    // Method definition that performs a specific part of the program workflow.
    private final By userEmail  = By.cssSelector("input[data-qa='signup-email']");
    // Method definition that performs a specific part of the program workflow.
    private final By signUpBtn  = By.cssSelector("button[data-qa='signup-button']");
    private final By signUpError = By.xpath("//p[contains(text(),'Email Address already exist!')]");

    // Method definition that performs a specific part of the program workflow.
    public void open(String baseUrl) { DriverFactory.getDriver().get(baseUrl + "/signup"); }

    // Method definition that performs a specific part of the program workflow.
    public void signUp(String username, String email) {
        type(user, username);
        type(userEmail, email);
        click(signUpBtn);
    }

    // Method definition that performs a specific part of the program workflow.
    public boolean isOnSignUpPage() {
        // Return the final result back to the caller.
        return $(user).isDisplayed();
    }

    // Method definition that performs a specific part of the program workflow.
    public boolean isSignUpErrorVisible() {
        // Return the final result back to the caller.
        return $(signUpError).isDisplayed();
    }
}
