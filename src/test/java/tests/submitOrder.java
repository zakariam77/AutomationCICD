package tests;

import driver.DriverManage;
import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.ConfigReader;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class submitOrder extends BaseTest{

    private static final Logger logger = LogManager.getLogger(submitOrder.class);

    String productName = "Sauce Labs Onesie";
    String testUsername = ConfigReader.getProperty("testUsername");
    String testPassword = ConfigReader.getProperty("testPassword");

    @Test(testName = "Verify successful E2E Checkout Flow", groups = {"buyTest"})
    public void simpleTest(){

        SoftAssert softAssert = new SoftAssert();
        LandingPage landingPage = new LandingPage(DriverManage.getDriver());
        logger.info("logging to application with username: {}", testUsername);
        Inventory inventory = landingPage.loginApp(testUsername, testPassword);

        logger.info("adding product to cart: {} ",productName );
        inventory.addProductToCart(productName);
        logger.info("go to cart");
        Cart cart = inventory.goToCart();
        logger.info("verifying product in cart");
        String actual = cart.verifyProductInCart(productName);
        Assert.assertEquals(actual, productName);
        logger.info("go to checkout");
        Checkout checkout = cart.goToCheckout();
        logger.info("filling shipping details and submitting order");
        checkout.fillShipping();
        CheckoutOverview checkoutOverview = checkout.submitOrder();
        logger.info("finish order");
        Confirmation confirmation = checkoutOverview.finishOrder();
        softAssert.assertEquals(confirmation.getFinalMessage(), "Thank you for your order!");
        softAssert.assertAll();
    }

    @Test(groups = {"buyTest"})
    public void verifyProduct()  {

       SoftAssert softAssert = new SoftAssert();
       String pName = "Sauce Labs Bike Light";
       LandingPage landingPage = new LandingPage(DriverManage.getDriver());
       Inventory inventory  = landingPage.loginApp(testUsername, testPassword);
       inventory.addProductToCart(pName);
       Cart cart = inventory.goToCart();
       String productInCart = cart.verifyProductInCart(pName);
       softAssert.assertEquals(productInCart, pName);
       softAssert.assertAll();
    }
    @Test(dataProvider = "getData", description = "testing various data" ,groups = {"failLogin"})
    public void failLogin(String username, String password) {
        SoftAssert softAssert = new SoftAssert();
        LandingPage landingPage = new LandingPage(DriverManage.getDriver());
        landingPage.loginApp(username, password);
        softAssert.assertEquals(landingPage.getErrorMsg(), "Epic sadface: Username and password do not match any user in this service");
        softAssert.assertAll();
    }


    @DataProvider
    public Iterator<Object[]> getData() throws IOException, SQLException {
        String DB_password = System.getenv("DB_PASSWORD");
        String DB_user = "root";
        String DB_url = "jdbc:mysql://localhost:3306/testdb";
        List<Object[]> dataList = new ArrayList<>();

         Connection connection = DriverManager.getConnection(DB_url, DB_user, DB_password);

        Statement statement = connection.createStatement();
        ResultSet rs =  statement.executeQuery("select username, userpass from testingdata");
        while(rs.next()){
            String username = rs.getString("username");
            String password = rs.getString("userpass");
            dataList.add(new Object[] {username, password});
        }
        return dataList.iterator();
    }


}
