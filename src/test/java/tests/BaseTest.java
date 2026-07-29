package tests;

import driver.DriverFactory;
import driver.DriverManage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

public class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void launchApplication(){
        WebDriver driver = DriverFactory.setUp();
        DriverManage.setDriverThreadLocal(driver);
        driver.get(ConfigReader.getProperty("url"));
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        DriverManage.removeDriver();
    }


}
