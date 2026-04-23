package tests;

import Listeners.Retry;
import driver.DriverManager;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.JsonUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class submitOrder extends BaseTest{
    String productName = "Sauce Labs Onesie";

    @Test(groups = {"buyTest"})
    public void simpleTest(){
        SoftAssert softAssert = new SoftAssert();
        LandingPage landingPage = new LandingPage(DriverManager.getDriver());
        Inventory inventory = landingPage.loginApp("standard_user", "secret_sauce");
        inventory.addProductToCart(productName);
        Cart cart = inventory.goToCart();
        String actual = cart.verifyProductInCart(productName);
        Assert.assertEquals(actual, productName);
        Checkout checkout = cart.goToCheckout();
        checkout.fillShipping();
        CheckoutOverview checkoutOverview = checkout.submitOrder();
        Confirmation confirmation = checkoutOverview.finishOrder();
        softAssert.assertEquals(confirmation.getFinalMessage(), "Thank you for your order!");
        softAssert.assertAll();
    }

    @Test(groups = {"buyTest"})
    public void verifyProduct()  {

       SoftAssert softAssert = new SoftAssert();
       String pName = "Sauce Labs Bike Light";
        LandingPage landingPage = new LandingPage(DriverManager.getDriver());
       Inventory inventory  = landingPage.loginApp("standard_user", "secret_sauce");
       inventory.addProductToCart(pName);
       Cart cart = inventory.goToCart();
       String productInCart = cart.verifyProductInCart(pName);
       softAssert.assertEquals(productInCart, "fake name");
       softAssert.assertAll();
    }
    //f
    @Test(dataProvider = "getData", groups = {"failLogin"})
    public void failLogin(HashMap<String, String> input) {
        SoftAssert softAssert = new SoftAssert();
        LandingPage landingPage = new LandingPage(DriverManager.getDriver());
        landingPage.loginApp(input.get("name"), input.get("last"));
        softAssert.assertEquals(landingPage.getErrorMsg(), "mEpic sadface: Username and password do not match any user in this service");
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = JsonUtil.getDataJson(System.getProperty("user.dir") + "/src/test/java/files/testData.json");
        return new Object[][] {{data.get(0)}, {data.get(1)}};
    }


}
