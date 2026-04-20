package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ReportManager {
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static ExtentReports extent = ExtentReporter.createInstance();

    public static ExtentTest getTest(){
        return test.get();
    }
    public static void setTest(ExtentTest testInstance){
        test.set(testInstance);
    }
    public static void unload(){
        test.remove();
    }
    public static ExtentReports getExtent(){
        return extent;
    }







}
