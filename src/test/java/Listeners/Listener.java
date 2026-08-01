package Listeners;

import driver.DriverManage;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.MDC;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Listener implements ITestListener, IInvokedMethodListener {

    private static final Logger logger = LogManager.getLogger(Listener.class);
    @Override
    public void onTestFailure(ITestResult result) {

       Allure.addAttachment("test failed reason: ", result.getThrowable().getMessage());


        WebDriver driver = DriverManage.getDriverThreadLocal();
            if(driver != null){
                logger.info("taking screenshot...");
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

                String timeStamp = new SimpleDateFormat("ddMMyyy_HHmmss").format(new Date());
                String fileName = result.getMethod().getMethodName() + "_" + timeStamp + ".png";

                Allure.addAttachment(fileName, new ByteArrayInputStream(screenshot));
            }
    }

    // insert test name into log4j logs
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

        // Attach the test method name and parameters to the thread context
        String testName = method.getTestMethod().getMethodName();
        MDC.put("testName", testName);

    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        MDC.remove("testName");

    }
}
