package tests;

import driver.DriverManage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Cart;
import pages.Inventory;
import pages.LandingPage;
import utils.ConfigReader;

public class VerifyPorduct {
    @Test
    public void verifyProduct()  {

        String testUsername = ConfigReader.getProperty("testUsername");
        String testPassword = ConfigReader.getProperty("testPassword");

        SoftAssert softAssert = new SoftAssert();
        String pName = "Sauce Labs Bike Light";
        LandingPage landingPage = new LandingPage(DriverManage.getDriverThreadLocal());
        Inventory inventory  = landingPage.loginApp(testUsername, testPassword);
        inventory.addProductToCart(pName);
        Cart cart = inventory.goToCart();
        String productInCart = cart.verifyProductInCart(pName);
        softAssert.assertEquals(productInCart, pName);
        softAssert.assertAll();
    }
}
