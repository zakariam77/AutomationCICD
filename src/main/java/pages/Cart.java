package pages;

import abstractComponenets.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reports.ReportManager;

import java.util.List;

public class Cart extends AbstractComponents {
    public Cart(WebDriver driver){

        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css=".inventory_item_name")
    List<WebElement> cartItemsTitles;
    @FindBy(id="checkout")
    WebElement checkoutBtn;

    public String verifyProductInCart(String productName){

        return  cartItemsTitles.stream().filter(s->s.getText().equals(productName))
                .findFirst().orElse(null).getText();
    }
    public Checkout goToCheckout(){
        ReportManager.getTest().info("going to checkout...");
        checkoutBtn.click();
        return new Checkout(driver);
    }
}
