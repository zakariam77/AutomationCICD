package driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
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

    private static WebDriver  driver;
    private static final Logger logger = LogManager.getLogger(DriverFactory.class);


    public static WebDriver setUp(){
        String browserType = System.getProperty("browser") != null ?
                        System.getProperty("browser") : ConfigReader.getProperty("browser");

        String grid_URL = ConfigReader.getProperty("grid_URL");


        switch (browserType.toLowerCase()){
            //chrome
            case "chrome" : {
                ChromeOptions options = getChromeOptions();
                try {
                    driver = new RemoteWebDriver(new URI(grid_URL).toURL(), options);
                    driver.manage().window().setSize(new Dimension(1440, 900));

                }catch (URISyntaxException | MalformedURLException e){
                    logger.fatal("Grid url malformed or syntax issue {} driver can't be started {}", grid_URL, e.getMessage() );
                    throw new RuntimeException("failed to instantiate RemoteWebDriver due to invalid Grid URL", e);
                }

            }
                break;

            //firefox
            case "firefox" : {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless");
                try {
                    driver = new RemoteWebDriver(new URI(grid_URL).toURL(), options);
                    driver.manage().window().setSize(new Dimension(1440, 900));

                }catch (URISyntaxException | MalformedURLException e){
                    logger.fatal("Grid url malformed or syntax issue {} driver can't be started {}", grid_URL, e.getMessage() );
                    throw new RuntimeException("failed to instantiate RemoteWebDriver due to invalid Grid URL", e);
                }
            }
                break;

            default : throw new RuntimeException("browser not supported: " + browserType);
        }

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
}
