package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Confirmation extends BasePage {
    private static final Logger logger = LogManager.getLogger(Confirmation.class);

    public Confirmation(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(css=".complete-header")
    WebElement finalMessage;
    @Step("Fetching order confirmation text")
    public String getFinalMessage() {
        logger.info("Fetching order confirmation text");
        return finalMessage.getText();
    }
}
