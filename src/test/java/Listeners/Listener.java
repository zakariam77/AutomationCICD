package Listeners;
import driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.ReportManager;
import utils.ScreenshotUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Listener implements ITestListener {


    @Override
    public void onTestStart(ITestResult result) {
        ReportManager.setTest(ReportManager.getExtent().createTest(result.getMethod().getMethodName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ReportManager.getTest().pass("test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ReportManager.getTest().fail(result.getThrowable());
        WebDriver driver = DriverManager.getDriver();
            if(driver != null){
                String screenshotPath = ScreenshotUtils.getScreenshot(driver, result.getMethod().getMethodName());
                ReportManager.getTest().addScreenCaptureFromPath(screenshotPath);
            }
            else{
                System.out.println("driver null, no screenshot" + result.getName());
            }

        }

    @Override
    public void onTestSkipped(ITestResult result) {
        ReportManager.getTest().skip("test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        ReportManager.getExtent().flush();
        ReportManager.unload();

    }




}
