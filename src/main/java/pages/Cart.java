package pages;

import abstractComponenets.AbstractComponents;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Cart extends AbstractComponents {
    private static final Logger logger = LogManager.getLogger(Cart.class);

    public Cart(WebDriver driver){

        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css=".inventory_item_name")
    List<WebElement> cartItemsTitles;
    @FindBy(id="checkout")
    WebElement checkoutBtn;

    @Step("verifying product {productName} in cart ")
    public String verifyProductInCart(String productName){

        return  cartItemsTitles.stream().filter(s->s.getText().equals(productName))
                .findFirst().orElse(null).getText();
    }
    public Checkout goToCheckout(){
        logger.debug("attempt to click button [checkout]");
        checkoutBtn.click();
        return new Checkout(driver);
    }
}
