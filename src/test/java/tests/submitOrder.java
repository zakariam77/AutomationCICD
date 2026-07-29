package tests;

import driver.DriverManage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.ConfigReader;
import utils.MysqlReader;
import utils.WaitUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class submitOrder extends BaseTest{

    private static final Logger logger = LogManager.getLogger(submitOrder.class);

    String productName = "Sauce Labs Onesie";
    String testUsername = ConfigReader.getProperty("testUsername");
    String testPassword = ConfigReader.getProperty("testPassword");

    @Test(testName = "Verify successful E2E Checkout Flow")
    public void simpleTest(){

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
        softAssert.assertEquals(confirmation.getFinalMessage(), "Thank you for your order!");
        softAssert.assertAll();

    }

    @Test(dataProvider = "getData")
    public void failLogin(String username, String password) {
        SoftAssert softAssert = new SoftAssert();
        LandingPage landingPage = new LandingPage(DriverManage.getDriverThreadLocal());
        landingPage.loginApp(username, password);
        softAssert.assertEquals(landingPage.getErrorMsg(), "Epic sadface: Username and password do not match any user in this service");
        softAssert.assertAll();
    }



    @DataProvider(name = "getData", parallel = true)
    public Iterator<Object[]> getData() {

        List<Object[]> dataList = MysqlReader.getDataSql();

        return dataList.iterator();
    }



}
