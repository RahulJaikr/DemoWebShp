package shop.uttils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import shop.uttils.Listener;  // ✅ Import your listener to access ExtentTest

public class ScreenshotUttils {

    public static String takeScreenshot(WebDriver driver) {

        // Get caller class and method name (for filename)
        StackTraceElement caller = Thread.currentThread().getStackTrace()[2];
        String fullClassName = caller.getClassName();
        String methodName = caller.getMethodName();
        String simpleClassName = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);
        String screenshotName = simpleClassName + "-" + methodName;

        // Add timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Folder path for screenshots
        String destDir = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator;
        new File(destDir).mkdirs(); // create folder if not exists

        // Final file path
        String destPath = destDir + screenshotName + "_" + timestamp + ".png";

        try {
            // Take screenshot
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File(destPath));
            System.out.println("✅ Screenshot saved: " + destPath);

            // ✅ Automatically attach to Extent Report
            if (Listener.getTest() != null) {
                Listener.getTest().addScreenCaptureFromPath(destPath);
                Listener.getTest().info("Screenshot captured: " + screenshotName);
            } else {
                System.out.println("⚠️ Listener test object is null — screenshot not attached to report.");
            }

        } catch (IOException e) {
            System.err.println("❌ Failed to capture screenshot: " + e.getMessage());
        }

        return destPath;
    }
}
