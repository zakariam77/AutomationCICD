package pages;

import abstractComponenets.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigReader;

public class LandingPage extends AbstractComponents {

    public LandingPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(id="user-name")
    WebElement nameInput;
    @FindBy(id="password")
    WebElement passwordInput;
    @FindBy(id="login-button")
    WebElement login_button;
    @FindBy(css=".error-message-container")
    WebElement errorMsg;

    public void goTo(){
        driver.get(ConfigReader.getProperty("url"));
    }

    public Inventory loginApp(String username, String password) {
        nameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        login_button.click();
        return new Inventory(driver);
    }

    public String getErrorMsg(){
        return errorMsg.getText();
    }


}
