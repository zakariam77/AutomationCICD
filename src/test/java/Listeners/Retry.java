package Listeners;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    private static final Logger logger = LogManager.getLogger(Retry.class);
    private final int maxTry = 1;
    private int count = 0;
    @Step("Retrying test")
    @Override
    public boolean retry(ITestResult iTestResult) {
        if(count<maxTry){
            count++;
            logger.info("Test failed, Retrying number: {}", count);
            return true;
        }
            return false;
    }

}
