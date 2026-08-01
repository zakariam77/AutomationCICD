package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

public class Checkout extends BasePage {
    private static final Logger logger = LogManager.getLogger(Checkout.class);

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

    @Step("Filling shipping details for username: {0}")
    public void fillShipping(String sUsername){
        WaitUtils.visibilityOfElementLocated(firstNameBy);
        logger.info("filling shipping details: {}", sUsername );
        firstName.sendKeys(sUsername);
        lastName.sendKeys("lastname");
        zipCode.sendKeys("98759");

    }
    @Step("submitting order ")
    public CheckoutOverview submitOrder(){
        logger.debug("attempt to click button [continue]");
        continueBtn.click();
        return new CheckoutOverview(driver);
    }

}
