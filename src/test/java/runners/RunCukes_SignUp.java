package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;

@CucumberOptions(
        features   = "src/test/resources/features/auth/SignUp",
        glue       = {"stepdefs", "hooks"},
        plugin     = {"pretty", "html:cucumber-reports/report-signup.html"},
        monochrome = true,
        tags       = "@signup"
)
// Class declaration that groups the related example logic in one place.
public class RunCukes_SignUp extends AbstractTestNGCucumberTests {

    @BeforeClass(alwaysRun = true)
    // Method definition that performs a specific part of the program workflow.
    public void setBrowser() { System.setProperty("browser", "chrome"); }
}
