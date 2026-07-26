package Listeners;
import driver.DriverManager;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.ReportManager;
import utils.ScreenshotUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Listener implements ITestListener {


    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverManager.getDriver();
            if(driver != null){
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

                String timeStamp = new SimpleDateFormat("ddMMyyy_HHmmss").format(new Date());
                String fileName = result.getMethod().getMethodName() + "_" + timeStamp + ".png";

                Allure.addAttachment(fileName, new ByteArrayInputStream(screenshot));
            }
            else{
                System.out.println("driver null, no screenshot" + result.getName());
            }

    }





}
