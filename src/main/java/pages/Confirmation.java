package pages;

import abstractComponenets.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reports.ReportManager;

public class Confirmation extends AbstractComponents {
    public Confirmation(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(css=".complete-header")
    WebElement finalMessage;

    public String getFinalMessage() {
        ReportManager.getTest().info("getting final message");
        return finalMessage.getText();
    }
}
