package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

// Class declaration that groups the related example logic in one place.
public class DriverFactory {
	// Method definition that performs a specific part of the program workflow.
	private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	// Method definition that performs a specific part of the program workflow.
	public static void initDriver(String browser, boolean headless) {
		browser = browser == null ? "chrome" : browser.toLowerCase();
		switch (browser) {
		case "chrome" -> {
			ChromeOptions options = new ChromeOptions();
			// Check the condition before deciding whether this block should run.
			if (headless)
				options.addArguments("--headless=new");
			options.addArguments("--start-maximized");
			tlDriver.set(new ChromeDriver(options));
		}
		case "firefox" -> {
			FirefoxOptions options = new FirefoxOptions();
			// Check the condition before deciding whether this block should run.
			if (headless)
				options.addArguments("-headless");
			tlDriver.set(new FirefoxDriver(options));
		}
		case "edge" -> {
			EdgeOptions options = new EdgeOptions();
			// Check the condition before deciding whether this block should run.
			if (headless)
				options.addArguments("--headless=new");
			tlDriver.set(new EdgeDriver(options));
		}
		default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
		}
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
	}

	// Method definition that performs a specific part of the program workflow.
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	// Method definition that performs a specific part of the program workflow.
	public static void quitDriver() {
		// Create or store the browser driver used to control the web session.
		WebDriver driver = tlDriver.get();
		// Check the condition before deciding whether this block should run.
		if (driver != null) {
			driver.quit();
			tlDriver.remove();
		}
	}
}
