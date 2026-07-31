package pages;

import abstractComponenets.AbstractComponents;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigReader;

public class LandingPage extends AbstractComponents {
    private static final Logger logger = LogManager.getLogger(LandingPage.class);

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

    @Step("logging in with user : {0} ")
    public Inventory loginApp(String username, String password) {
        logger.info("typing login for username:  {}", username);
        nameInput.sendKeys(username);
        passwordInput.sendKeys(password);

        logger.debug("attempt to click login button {}", login_button);
        login_button.click();
        return new Inventory(driver);
    }

    public String getErrorMsg(){
        return errorMsg.getText();
    }


}
