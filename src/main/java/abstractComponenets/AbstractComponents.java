package abstractComponenets;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.Cart;
public class AbstractComponents {
    protected WebDriver driver;
    public AbstractComponents(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(css=".shopping_cart_link")
    WebElement cartButton;

    @Step("going to cart ")
    public Cart goToCart(){
        cartButton.click();
        return new Cart(driver);
    }

}
