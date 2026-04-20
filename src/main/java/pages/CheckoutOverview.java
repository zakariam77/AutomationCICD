package pages;

import abstractComponenets.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reports.ReportManager;

public class CheckoutOverview extends AbstractComponents {
    public CheckoutOverview(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(id="finish")
    WebElement finishBtn;

    public Confirmation finishOrder(){
        ReportManager.getTest().info("clicking finish...");
        finishBtn.click();
        return new Confirmation(driver);
    }


}
