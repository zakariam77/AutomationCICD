package pages;

import abstractComponenets.AbstractComponents;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOverview extends AbstractComponents {
    public CheckoutOverview(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(id="finish")
    WebElement finishBtn;

    @Step("finishing order ")
    public Confirmation finishOrder(){
        finishBtn.click();
        return new Confirmation(driver);
    }


}
