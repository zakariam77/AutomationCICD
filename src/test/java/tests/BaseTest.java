package tests;

import driver.DriverFactory;
import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void launchApplication() throws MalformedURLException, URISyntaxException {
        WebDriver driver = DriverFactory.setUp();
        DriverManager.setDriver(driver);
        driver.get(ConfigReader.getProperty("url"));
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        DriverManager.removeDriver();
    }


}
