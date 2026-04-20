package pages;

import abstractComponenets.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reports.ReportManager;
import utils.WaitUtils;

public class Checkout extends AbstractComponents {

    public Checkout(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(id="first-name")
    WebElement firstName;
    @FindBy(id="last-name")
    WebElement lastName;
    @FindBy(id="postal-code")
    WebElement zipCode;
    @FindBy(id="continue")
    WebElement continueBtn;
    By firstNameBy = By.id("first-name");

    public void fillShipping(){

        WaitUtils.visibilityOfElementLocated(firstNameBy);
        firstName.sendKeys("usernameeme");
        lastName.sendKeys("resuresu");
        zipCode.sendKeys("98759");

    }
    public CheckoutOverview submitOrder(){
        ReportManager.getTest().info("clicking continue...");
        continueBtn.click();
        return new CheckoutOverview(driver);
    }

}
