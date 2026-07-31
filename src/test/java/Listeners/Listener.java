package Listeners;

import driver.DriverManage;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.MDC;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Listener implements ITestListener {


    @Override
    public void onTestFailure(ITestResult result) {

        Allure.addAttachment("test failed reason: ", result.getThrowable().getMessage());

        WebDriver driver = DriverManage.getDriverThreadLocal();
            if(driver != null){
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

                String timeStamp = new SimpleDateFormat("ddMMyyy_HHmmss").format(new Date());
                String fileName = result.getMethod().getMethodName() + "_" + timeStamp + ".png";

                Allure.addAttachment(fileName, new ByteArrayInputStream(screenshot));
            }
    }

    @BeforeMethod
    public void setLogContext(Method method, Object[] testData) {
        String testName = method.getName();
        if (testData.length > 0 && testData[0] != null) {
            testName += "[" + testData[0].toString() + "]";
        }
        MDC.put("testContext", testName);
    }

    @AfterMethod
    public void clearLogContext() {
        MDC.remove("testContext");
    }



}
