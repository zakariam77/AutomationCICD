package driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

public class DriverManage {

    private static final Logger logger = LogManager.getLogger(DriverManage.class);
    private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();

    public static void setDriverThreadLocal(WebDriver instanceDriver){
        DRIVER_THREAD_LOCAL.set(instanceDriver);
    }
    public static WebDriver getDriverThreadLocal(){
        return DRIVER_THREAD_LOCAL.get();
    }
    public static void removeDriver(){

        if (DRIVER_THREAD_LOCAL.get() != null) {
            try {
                DRIVER_THREAD_LOCAL.get().quit();
                DRIVER_THREAD_LOCAL.remove();
            } catch (UnreachableBrowserException e) {
                logger.fatal("Browser was already dead when attempting quit: {}", e.getMessage());
            }
        }
    }
}
