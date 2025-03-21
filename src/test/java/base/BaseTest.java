package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class BaseTest {
    protected static WebDriver driver;  // Make driver static

    public static void initializeDriver(String browser) {  // Make method static
        if (driver == null) {  // Prevent multiple driver instances
            if (browser.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito"); 
                driver = new ChromeDriver(options);
            } else {
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
    }

    public static void closeDriver() {  // Make method static
        if (driver != null) {
            driver.quit();
            driver = null;  // Reset driver after quitting
        }
    }
}
