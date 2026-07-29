package driver;

import org.openqa.selenium.WebDriver;

public class DriverManage {

    private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();

    public static void setDriverThreadLocal(WebDriver instanceDriver){
        DRIVER_THREAD_LOCAL.set(instanceDriver);
    }
    public static WebDriver getDriverThreadLocal(){
        return DRIVER_THREAD_LOCAL.get();
    }
    public static void removeDriver(){
        if (DRIVER_THREAD_LOCAL.get() != null) {
            DRIVER_THREAD_LOCAL.get().quit();
            DRIVER_THREAD_LOCAL.remove();
        }
    }
}
