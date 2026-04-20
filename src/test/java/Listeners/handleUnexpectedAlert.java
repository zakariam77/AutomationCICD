package Listeners;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class handleUnexpectedAlert {
    WebDriver driver;
    public handleUnexpectedAlert(WebDriver driver){
        this.driver = driver;
    }
    public void handleAlert(){
        try {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
        } catch (NoAlertPresentException e) {
            // ignore
        }
    }

}
