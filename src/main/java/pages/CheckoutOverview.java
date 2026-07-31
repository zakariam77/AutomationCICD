package pages;

import abstractComponenets.AbstractComponents;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOverview extends AbstractComponents {
    private static final Logger logger = LogManager.getLogger(CheckoutOverview.class);

    public CheckoutOverview(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(id="finish")
    WebElement finishBtn;

    @Step("finishing order ")
    public Confirmation finishOrder(){
        logger.debug("attempt to click button [finish]");
        finishBtn.click();
        return new Confirmation(driver);
    }


}
