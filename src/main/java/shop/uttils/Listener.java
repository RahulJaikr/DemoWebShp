package shop.uttils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import shop.uttils.ExtentReporter;

import shop.uttils.ScreenshotUttils;

public class Listener implements ITestListener{

	ExtentTest test;
	ExtentReports extent = ExtentReporter.getReportsObject();
	static ThreadLocal <ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result)
	{
		//extentTest=ExtentTest.c
		test =extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		//extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		System.out.println("Test Case is passed :"+result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		extentTest.get().fail(result.getThrowable());
		WebDriver driver = null;
		
		try 
		{
			driver = (WebDriver) result.getTestClass()
					.getRealClass().getField("driver").get(result.getInstance());
		} 
		catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			
			e.printStackTrace();
		} catch (SecurityException e) {
			
			e.printStackTrace();
		}
			
		String path = ScreenshotUttils.takeScreenshot(driver);
		extentTest.get().addScreenCaptureFromPath(path);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
	
		extent.flush();
	}
	
	public static ExtentTest getTest() {
		return extentTest.get();
		 
		
	}

}
