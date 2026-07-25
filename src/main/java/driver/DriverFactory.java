package driver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigReader;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    public static WebDriver setUp() throws URISyntaxException, MalformedURLException {
        WebDriver driver;
        String browserType = System.getProperty("browser") != null ?
                        System.getProperty("browser") : ConfigReader.getProperty("browser");
        String grid_URL = ConfigReader.getProperty("grid_URL");
        switch (browserType.toLowerCase()){
            case "chrome" : {
                ChromeOptions options = new ChromeOptions();

                // disable weak password detection
                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("profile.password_manager_leak_detection", false);
                options.setExperimentalOption("prefs", prefs);
                //

                options.addArguments("--headless");
                        driver = new RemoteWebDriver(new URI("http://localhost:4444").toURL(), options);
                        driver.manage().window().setSize(new Dimension(1440, 900));
                }
                break;
            case "firefox" : {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless");
                driver = new RemoteWebDriver(new URI(grid_URL).toURL(), options);
                driver.manage().window().setSize(new Dimension(1440, 900));
            }
                break;

            default : throw new RuntimeException("browser not supported: " + browserType);
        }
        return driver;
    }
}
