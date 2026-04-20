package tests;

import driver.DriverFactory;
import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LandingPage;
import utils.ConfigReader;


public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void launchApplication() {
        driver = DriverFactory.setUp();
        DriverManager.setDriver(driver);
        driver.get(ConfigReader.getProperty("url"));
    }
    @AfterMethod(alwaysRun = true)
    //review
    public void tearDown(){
        DriverManager.removeDriver();
    }



}
