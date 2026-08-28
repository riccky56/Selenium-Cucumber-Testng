package hooks;

import context.TestContext;
import framework.ConfigReader;
import framework.DriverFactory;
import pages.LoginPage;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

// Class declaration that groups the related example logic in one place.
public class Hooks {

    @Before(order = 0)
    // Method definition that performs a specific part of the program workflow.
    public void setUp() {
        // Store text data that will be processed by the program logic.
        String browser = System.getProperty("browser", ConfigReader.browser());
        // Store a true or false state needed for conditional logic.
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", String.valueOf(ConfigReader.headless())));
        DriverFactory.initDriver(browser, headless);
    }

    @Before(order = 1, value = "@requiresLogin")
    // Method definition that performs a specific part of the program workflow.
    public void login() {
        // Store text data that will be processed by the program logic.
        String email    = TestContext.getEmail()    != null ? TestContext.getEmail()    : ConfigReader.loginEmail();
        // Store text data that will be processed by the program logic.
        String password = TestContext.getPassword() != null ? TestContext.getPassword() : ConfigReader.loginPassword();
        LoginPage loginPage = new LoginPage();
        loginPage.open(ConfigReader.baseUrl());
        loginPage.loginAs(email, password);
        Assert.assertTrue(loginPage.isLoggedIn(),
            "Login precondition failed â€” could not log in as " + email);
    }

    @After(order = 1)
    // Method definition that performs a specific part of the program workflow.
    public void takeScreenshotOnFailure(Scenario scenario) {
        // Check the condition before deciding whether this block should run.
        if (scenario.isFailed()) {
            try {
                // Create or store the browser driver used to control the web session.
                WebDriver driver = DriverFactory.getDriver();
                // Check the condition before deciding whether this block should run.
                if (driver != null) {
                    byte[] shot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    scenario.attach(shot, "image/png", "failed_scenario");
                }
            } catch (Exception e) {
                // Display information to the console for the user.
                System.out.println("Could not take screenshot: " + e.getMessage());
            }
        }
    }

    @After(order = 0)
    // Method definition that performs a specific part of the program workflow.
    public void tearDown() {
        try {
            DriverFactory.quitDriver();
        } catch (Exception e) {
            // Display information to the console for the user.
            System.out.println("Could not quit driver: " + e.getMessage());
        }
    }
}
