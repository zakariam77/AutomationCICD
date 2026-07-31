package tests;

import driver.DriverFactory;
import driver.DriverManage;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

public class BaseTest {

    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    @Step("Launching WebDriver")
    @BeforeMethod(alwaysRun = true)
    public void launch(){
        String basePageUrl = ConfigReader.getProperty("url");

        WebDriver driver  =  DriverFactory.setUp();
        DriverManage.setDriverThreadLocal(driver);
        logger.info("Opening Base Page url: {}", basePageUrl);
        driver.get(basePageUrl);
    }
    @Step("Driver Teardown")
    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        DriverManage.removeDriver();
    }


}
