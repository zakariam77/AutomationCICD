package tests;

import driver.DriverManage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.ConfigReader;

public class E2ECheckoutTest extends BaseTest{

    String productName = "Sauce Labs Onesie";
    String testUsername = ConfigReader.getProperty("testUsername");
    String testPassword = ConfigReader.getProperty("testPassword");

    @Test(testName = "Verify successful E2E Checkout Flow")
    public void E2ECheckout(){

        SoftAssert softAssert = new SoftAssert();
        LandingPage landingPage = new LandingPage(DriverManage.getDriverThreadLocal());
        Inventory inventory = landingPage.loginApp(testUsername, testPassword);

        inventory.addProductToCart(productName);
        Cart cart = inventory.goToCart();
        String actual = cart.verifyProductInCart(productName);
        Assert.assertEquals(actual, productName);
        Checkout checkout = cart.goToCheckout();
        checkout.fillShipping(testUsername);
        CheckoutOverview checkoutOverview = checkout.submitOrder();
        Confirmation confirmation = checkoutOverview.finishOrder();
        softAssert.assertEquals(confirmation.getFinalMessage(), "Thank lyou for your order!");
        softAssert.assertAll();

    }

}
