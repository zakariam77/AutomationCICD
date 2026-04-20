package abstractComponenets;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.Cart;
import reports.ReportManager;

public class AbstractComponents {
    protected WebDriver driver;
    public AbstractComponents(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(css=".shopping_cart_link")
    WebElement cartButton;

    public Cart goToCart(){
        ReportManager.getTest().info("going to cart...");
        cartButton.click();
        return new Cart(driver);
    }

}
