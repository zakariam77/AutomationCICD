package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

private static final Logger logger = LogManager.getLogger(BasePage.class);

    protected WebDriver driver;
    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(css=".shopping_cart_link")
    WebElement cartButton;

    @Step("going to cart ")
    public Cart goToCart(){
        logger.debug("attempt to click button [cart]" );
        cartButton.click();
        return new Cart(driver);
    }

}
