package utils;

import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
public class WaitUtils {
    static int  timeout = Integer.parseInt(ConfigReader.getProperty("timeout"));

    static WebDriverWait getWait(){
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
    }
    public static void visibilityOfElementLocated(By locator){
        getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void invisibilityOfElementLocated(By locator){
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static WebElement elementToBeClickable(By locator){
        return getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }


}
