package Listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    private final int maxTry = 1;
    private int count = 0;
    @Override
    public boolean retry(ITestResult iTestResult) {
        if(count<maxTry){
            count++;
            System.out.println("retry number: " + count);
            return true;
        }
            return false;
    }

}
