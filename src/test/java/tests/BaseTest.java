package tests;

import driver.DriverFactory;
import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

import java.lang.reflect.Method;


public class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void launchApplication(Method method) {
        System.out.println(method.getName() + " test starting");
        WebDriver driver = DriverFactory.setUp();
        DriverManager.setDriver(driver);
        driver.get(ConfigReader.getProperty("url"));
    }
    @AfterMethod(alwaysRun = true)
    //review
    public void tearDown(){
        DriverManager.removeDriver();
    }



}
