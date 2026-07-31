package tests;

import driver.DriverManage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LandingPage;
import utils.MysqlReader;

import java.util.Iterator;
import java.util.List;

public class NegativeTest extends BaseTest{

    @Test(dataProvider = "getData", description = "Running parallel DataProvider")
    public void failLogin(String username, String password) {

        LandingPage landingPage = new LandingPage(DriverManage.getDriverThreadLocal());
        landingPage.loginApp(username, password);
        Assert.assertEquals(landingPage.getErrorMsg(), "Epic sadface: Username and password do not match any user in this service");
    }

    @DataProvider(name = "getData", parallel = true)
    public Iterator<Object[]> getData() {

        List<Object[]> dataList = MysqlReader.getDataSql();

        return dataList.iterator();
    }
}
