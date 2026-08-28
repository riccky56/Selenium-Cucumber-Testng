package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;

@CucumberOptions(
        features   = "src/test/resources/features/auth/Login",
        glue       = {"stepdefs", "hooks"},
        plugin     = {"pretty", "html:cucumber-reports/report-login.html"},
        monochrome = true,
        tags       = "@login"
)
// Class declaration that groups the related example logic in one place.
public class RunCukes_Login extends AbstractTestNGCucumberTests {

    @BeforeClass(alwaysRun = true)
    // Method definition that performs a specific part of the program workflow.
    public void setBrowser() { System.setProperty("browser", "chrome"); }
}
