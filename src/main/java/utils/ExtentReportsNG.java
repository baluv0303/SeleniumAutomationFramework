//package utils;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//
//
//public class ExtentReportsNG {
//
//    public static ExtentReports getReportObject(){
//       String path =  System.getProperty("user.dir") + "//reports//index.html";
//        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
//        reporter.config().setReportName("Web Automation Test Results");
//        reporter.config().setDocumentTitle("Test results");
//
//        ExtentReports extent = new ExtentReports();
//        extent.attachReporter(reporter);
//        extent.setSystemInfo("Test Name", "Balaji Reddy");
//        return extent;
//
//    }
//}
package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.File;

public class ExtentReportsNG {

    public static ExtentReports getReportObject(){
       String path =  System.getProperty("user.dir") + "//reports//index.html";

       // Create reports directory if it doesn't exist
       File reportDir = new File(System.getProperty("user.dir") + "//reports");
       if (!reportDir.exists()) {
           reportDir.mkdirs();
       }

        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Test Results");
        reporter.config().setDocumentTitle("Test results");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Test Name", "Balaji Reddy");
        return extent;
    }
}
