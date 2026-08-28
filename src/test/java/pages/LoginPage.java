package pages;

import framework.BasePage;
import framework.DriverFactory;
import org.openqa.selenium.By;

// Class declaration that groups the related example logic in one place.
public class LoginPage extends BasePage {

    // Method definition that performs a specific part of the program workflow.
    private final By userEmail    = By.cssSelector("input[data-qa='login-email']");
    // Method definition that performs a specific part of the program workflow.
    private final By password     = By.cssSelector("input[data-qa='login-password']");
    // Method definition that performs a specific part of the program workflow.
    private final By loginBtn     = By.cssSelector("button[data-qa='login-button']");
    private final By loggedInAs   = By.xpath("//a[contains(.,'Logged in as')]");
    private final By loginError   = By.xpath("//p[contains(text(),'Your email or password is incorrect!')]");

    // Method definition that performs a specific part of the program workflow.
    public void open(String baseUrl) { DriverFactory.getDriver().get(baseUrl + "/login"); }

    // Method definition that performs a specific part of the program workflow.
    public void loginAs(String email, String pass) {
        type(userEmail, email);
        type(password, pass);
        click(loginBtn);
    }

    // Method definition that performs a specific part of the program workflow.
    public boolean isLoggedIn() {
        // Return the final result back to the caller.
        return $(loggedInAs).isDisplayed();
    }

    // Method definition that performs a specific part of the program workflow.
    public boolean isLoginErrorVisible() {
        // Return the final result back to the caller.
        return $(loginError).isDisplayed();
    }
}
