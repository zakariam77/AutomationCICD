package utils;

import driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    public static String getScreenshot(WebDriver driver, String testCaseName){
        String timeStamp = new SimpleDateFormat("ddMMyyy_HHmmss").format(new Date());

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String folderPath = System.getProperty("user.dir") + File.separator + "screenshots";
        String fileName = testCaseName + "_" + timeStamp + ".png";
        String fullPath = folderPath + File.separator + fileName;

        try {
            FileUtils.copyFile(src, new File(fullPath));
        }catch (Exception e){
            System.out.println(e);
        }
        return fullPath;
    }
}
