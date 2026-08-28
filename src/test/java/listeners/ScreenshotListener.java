package listeners;

import framework.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

// Class declaration that groups the related example logic in one place.
public class ScreenshotListener implements ITestListener {

    @Override
    // Method definition that performs a specific part of the program workflow.
    public void onTestFailure(ITestResult result) {
        // Create or store the browser driver used to control the web session.
        WebDriver driver = DriverFactory.getDriver();
        // Check the condition before deciding whether this block should run.
        if (driver instanceof TakesScreenshot) {
            try {
                File src  = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                Path dest = Paths.get("target", "screenshots", result.getName() + ".png");
                Files.createDirectories(dest.getParent());
                Files.copy(src.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);
                // Display information to the console for the user.
                System.out.println("Screenshot saved: " + dest.toAbsolutePath());
            } catch (IOException e) {
                // Display information to the console for the user.
                System.out.println("Could not save screenshot: " + e.getMessage());
            }
        }
    }
}
