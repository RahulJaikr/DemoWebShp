package shop.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest 
{
	public WebDriver driver;
	
	@BeforeMethod
	public void launch() throws IOException
	{
		
	String browserName=	readFile() ;
	if (browserName.equalsIgnoreCase("chrome"))
	{
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		ChromeOptions options = new ChromeOptions();
	//	options.addArguments("--force-device-scale-factor=.80");
		options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("--start-maximized");
		options.addArguments("--incognito");
		driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://demowebshop.tricentis.com/");
	}
	
	System.setProperty("webdriver.http.factory", "jdk-http-client");

	if (browserName.equalsIgnoreCase("edge"))
	{
    EdgeOptions options = new EdgeOptions();
    // options.addArguments("--force-device-scale-factor=.80");
    options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
    options.setExperimentalOption("useAutomationExtension", false);
    options.addArguments("--start-maximized");
    options.addArguments("--inprivate"); // correct for Edge (instead of --incognito)

    driver = new EdgeDriver(options);
    driver.manage().deleteAllCookies();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    driver.get("https://demowebshop.tricentis.com/");
	}
    
	
	}
	public String readFile() throws IOException
	{
		String filePath = "src/test/resources/Global.properties";
		FileInputStream fis = new FileInputStream(filePath);
		Properties prop = new Properties();
		prop.load(fis);
	String browserName =	prop.getProperty("browser");
	return browserName;
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.close();
	}
}
