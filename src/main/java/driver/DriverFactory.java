package driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigReader;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    private static final Logger logger = LogManager.getLogger(DriverFactory.class);

    private static WebDriver driver;

    public static WebDriver setUp(){
        String browserType = System.getProperty("browser") != null ?
                        System.getProperty("browser") : ConfigReader.getProperty("browser");

        MutableCapabilities options;

        switch (browserType.toLowerCase()) {
            case "chrome" :
                options = getChromeOptions();
            break;

            case "firefox" :
                options = getFirefoxOptions();
            break;

            default : throw new RuntimeException("Browser not supported: " + browserType);
        }

        String grid_URL = ConfigReader.getProperty("grid_URL");


        try {
            logger.info("initiating remoteWebDriver ({}) at grid url {}",browserType, grid_URL);
            driver = new RemoteWebDriver(new URI(grid_URL).toURL(), options);

            }catch (URISyntaxException | MalformedURLException e){
                    logger.fatal("Grid url malformed or syntax issue {} driver can't be started {}", grid_URL, e.getMessage() );
                    throw new RuntimeException("failed to instantiate RemoteWebDriver due to invalid Grid URL", e);
            }

        driver.manage().window().setSize(new Dimension(1440, 900));
        return driver;
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        // disable weak password detection
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);
        //

        options.addArguments("--headless");
        return options;
    }
    private static FirefoxOptions getFirefoxOptions(){
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        return options;
    }
}
