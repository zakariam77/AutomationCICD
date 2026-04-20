package driver;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setDriver(WebDriver instanceDriver){
        driver.set(instanceDriver);
    }
    public static WebDriver getDriver(){
        return driver.get();
    }
    public static void removeDriver(){
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
