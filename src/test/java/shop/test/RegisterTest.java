package shop.test;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import shop.base.BaseTest;
import shop.exculdedClass.ObsletedRegisterPage;
import shop.main.LoginPage;

import shop.main.RegisterPage;
import shop.main.utils.ExcelUtils;

public class RegisterTest extends BaseTest
{
	
	@Test
	public void testRegister() throws InterruptedException {
	    String filePath = "src/test/resources/RegisterData.xlsx";

	    String fname  = ExcelUtils.getCellValue(filePath, "Sheet1", 1, 0);
	    String lname  = ExcelUtils.getCellValue(filePath, "Sheet1", 1, 1);
	    String email  = ExcelUtils.getCellValue(filePath, "Sheet1", 1, 2);
	    String pass   = ExcelUtils.getCellValue(filePath, "Sheet1", 1, 3);
	    String cpass  = ExcelUtils.getCellValue(filePath, "Sheet1", 1, 4);
        String successMsg = ExcelUtils.getCellValue(filePath, "Sheet1", 1, 5);
	    driver.get("https://demowebshop.tricentis.com/register");

	  ObsletedRegisterPage rp = new ObsletedRegisterPage(driver);
	  String text =  rp.registerUser(fname, lname, email, pass, cpass);
	  
	  Assert.assertEquals(text,successMsg,"❌ Success message mismatch!");
	    //rp.registerUser();
	}
	
	@Test
	public void exisitingUserRegistration() throws InterruptedException {
	    String filePath = "src/test/resources/RegisterData.xlsx";

	    String fname  = ExcelUtils.getCellValue(filePath, "Sheet1", 1, 0);
	    String lname  = ExcelUtils.getCellValue(filePath, "Sheet1", 1, 1);
	    String email  = ExcelUtils.getCellValue(filePath, "Sheet1", 1, 2);
	    String pass   = ExcelUtils.getCellValue(filePath, "Sheet1", 1, 3);
	    String cpass  = ExcelUtils.getCellValue(filePath, "Sheet1", 1, 4);
        String errorMsg = ExcelUtils.getCellValue(filePath, "Sheet1", 1, 6);
	    driver.get("https://demowebshop.tricentis.com/register");

	    RegisterPage rp = new RegisterPage(driver);
	  String text =  rp.registerUser(fname, lname, email, pass, cpass);
	  
	  Assert.assertEquals(text,errorMsg, "❌ Error message mismatch!");
	    //rp.registerUser();
	}
	
	@Test
	public void loginIntoTheApllication()
	{
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("Rahul.Jaikr@gmail.com", "Enter@123");
		map.put("Rahul@Conduent.com", "Enter@123");
		LoginPage lp = new LoginPage(driver);
	lp.	loginIntoTheWebSite(map);
	}
	@Test
	public void newRun()
	{
		
	}

	@Test
	public void loginIntowithInavlidEmail()
	{
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("2Rahul.Jaikr@gmail.com", "Enter@123");
		map.put("Rahul2@Conduent.com", "Enter@123");
		LoginPage lp = new LoginPage(driver);
	String text = lp.	loginwithInavlidMailAddress(map);
	
	Assert.assertEquals(text, "No customer account found", "user deatils is not correct");
	}
	@Test
	public void loginIntowithInavlidPass()
	{
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("Rahul.Jaikr@gmail.com", "Enter123");
		map.put("Rahul@Conduent.com", "Enter123");
		LoginPage lp = new LoginPage(driver);
	String text = lp.	loginwithInavlidMailPass(map);
	
	Assert.assertEquals(text, "The credentials provided are incorrect", "Pass is not correct");
	}
}
