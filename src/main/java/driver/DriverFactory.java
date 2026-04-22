package driver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigReader;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    public static WebDriver setUp() {
        WebDriver driver;
        String browserType = System.getProperty("browser") != null ?
                        System.getProperty("browser") : ConfigReader.getProperty("browser");

        switch (browserType.toLowerCase()){
            case String b when b.contains("chrome") : {
                ChromeOptions options = new ChromeOptions();

                // disable weak password detection
                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("profile.password_manager_leak_detection", false);
                options.setExperimentalOption("prefs", prefs);
                //

                if(browserType.contains("headless")){
                    options.addArguments("--headless");
                }
                driver = new ChromeDriver(options);
                driver.manage().window().setSize(new Dimension(1440, 900));
            }
                break;
            case "firefox" : driver = new FirefoxDriver();
                break;
            case "edge" : driver = new EdgeDriver();
                break;
            default : throw new RuntimeException("browser not supported: " + browserType);
        }
        return driver;
    }
}
