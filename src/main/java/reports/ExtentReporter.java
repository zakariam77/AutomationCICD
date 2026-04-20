package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
    public static ExtentReports createInstance(){

        ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") +"/Reports/Spark.html");
        // Customize report
        reporter.config().setDocumentTitle("Automation Test Report");
        reporter.config().setReportName("Regression Suite");

        ExtentReports extent = new ExtentReports();
        extent.setSystemInfo("utils", "zakaria");
        extent.attachReporter(reporter);
        return extent;
    }
}
