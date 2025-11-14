package shop.uttils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {

    static ExtentReports extent;

    public static ExtentReports getReportsObject() {

        // Create a unique folder each run using date and time
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportFolder = System.getProperty("user.dir") + File.separator + "reports" + File.separator + timeStamp;
        new File(reportFolder).mkdirs();

        // Define report file path
        String reportPath = reportFolder + File.separator + "index.html";

        // Create the HTML report
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setDocumentTitle("Automation Report");
        reporter.config().setReportName("Web Automation Results");

        // Create ExtentReports instance and attach the reporter
        extent = new ExtentReports();
        extent.attachReporter(reporter);

        // Add system info
        extent.setSystemInfo("Tester", "Rahul Jain");
        extent.setSystemInfo("Environment", "QA");

        return extent;
    }
}
