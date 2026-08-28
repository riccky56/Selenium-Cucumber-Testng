package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features   = "src/test/resources/features",
        glue       = {"com.automationEx.stepdefs", "com.automationEx.hooks"},
        plugin     = {"pretty", "html:target/report-chrome.html"},
        monochrome = true,
        tags       = "@smoke"
)
// Class declaration that groups the related example logic in one place.
public class RunCukes_Chrome extends AbstractTestNGCucumberTests {

    @BeforeClass(alwaysRun = true)
    // Method definition that performs a specific part of the program workflow.
    public void setBrowser() { System.setProperty("browser", "chrome"); }

    @Override
    @DataProvider(parallel = true)
    // Method definition that performs a specific part of the program workflow.
    public Object[][] scenarios() { return super.scenarios(); }
}
