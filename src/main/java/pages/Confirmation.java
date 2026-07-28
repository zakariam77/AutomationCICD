package pages;

import abstractComponenets.AbstractComponents;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Confirmation extends AbstractComponents {
    public Confirmation(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(css=".complete-header")
    WebElement finalMessage;
    @Step("getting final message")
    public String getFinalMessage() {
        return finalMessage.getText();
    }
}
