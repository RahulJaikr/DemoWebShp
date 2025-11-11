package shop.main;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends AbstractMethods {

	public WebDriver driver;

	public RegisterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "gender-male")
	WebElement selectGender;

	@FindBy(id = "FirstName")
	WebElement fName;

	@FindBy(id = "LastName")
	WebElement lName;

	@FindBy(id = "Email")
	WebElement email;

	@FindBy(id = "Password")
	WebElement pass;

	@FindBy(xpath = "//input[@id='ConfirmPassword']")
	WebElement cpass;

	@FindBy(id = "register-button")
	WebElement registerButton;

	@FindBy(xpath = "//a[text()='Register']")
	WebElement clickOnTheRegisterPage;

	@FindBy(css = ".result")
	WebElement sucesMsg;

	@FindBy(xpath = ".validation-summary-errors li")
	WebElement errorMsg;

	public String registerUser(String fnam, String lnam, String mail, String pas, String cpas)
			throws InterruptedException
	// public void registerUser()
	{
		// visibilityOf(clickOnTheRegisterPage);
		visibilityOf(selectGender);
		// Thread.sleep(2000);
		elementToBeClickable(selectGender);
		selectGender.click();
		fName.sendKeys(fnam);
		lName.sendKeys(lnam);
		email.sendKeys(mail);
		pass.sendKeys(pas);
		visibilityOf(cpass);
		cpass.sendKeys(cpas);
		By errorMsgs = By.xpath("//li[text()='The specified email already exists']");
		By sucessMsgs = By.cssSelector(".result");
		elementToBeClickable(registerButton);
		registerButton.click();

		try {
			if (driver.findElements(sucessMsgs).size() > 0) {
				String text = driver.findElement(sucessMsgs).getText().trim();
				System.out.println(text);
				return text;
			}

			else if (driver.findElements(errorMsgs).size() > 0) {
				String text = driver.findElement(errorMsgs).getText().trim();
				System.out.println(text);
				return text;
			} else {
				// Case: neither success nor error message appeared
				System.out.println("⚠️ No success or error message found!");
				return "⚠️ No success or error message found!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ("⚠️ Exception occurred while checking messages: \" + e.getMessage()");
		}

		// String text = driver.findElement(errorMsgs).getText().trim();
		// return text;

	}
}
