package pages;

import abstractComponenets.AbstractComponents;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

import java.util.List;

public class Inventory extends AbstractComponents {

    public Inventory(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(css=".inventory_item")
    List<WebElement> productContainers;
    By inventoryItemName = By.cssSelector(".inventory_item_name ");
    By inventoryHeader = By.cssSelector(".header_secondary_container");
    By addToCart = By.cssSelector(".btn_inventory");


    public WebElement getProduct(String productName){
        WaitUtils.visibilityOfElementLocated(inventoryHeader);

        return productContainers.stream()
                .filter(s -> s.findElement(inventoryItemName).getText().equals(productName))
                .findFirst()
                .orElse(null);
    }

    @Step("adding product: {productName} to cart")
    public void addProductToCart(String productName){
        WebElement product = getProduct(productName);

        if(product == null){
            throw new RuntimeException("Product not found: " + productName);
        }

        product.findElement(addToCart).click();
    }


}
