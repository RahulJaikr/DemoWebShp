package shop.exculdedClass;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import shop.main.AbstractMethods;

public class ObsletedRegisterPage extends AbstractMethods {

	public WebDriver driver;

	public ObsletedRegisterPage(WebDriver driver) {
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

	@FindBy(id = "ConfirmPassword")
	WebElement cpass;

	@FindBy(id = "register-button")
	WebElement registerButton;

	@FindBy(css = ".result")
	WebElement successMsg;

	@FindBy(css = ".validation-summary-errors li")
	WebElement errorMsg;

	public String registerUser(String fnam, String lnam, String mail, String pas, String cpas)
			throws InterruptedException {

		visibilityOf(selectGender);
		elementToBeClickable(selectGender);
		selectGender.click();
		fName.sendKeys(fnam);
		lName.sendKeys(lnam);
		email.sendKeys(mail);
		pass.sendKeys(pas);
		visibilityOf(cpass);
		cpass.sendKeys(cpas);

		elementToBeClickable(registerButton);
		registerButton.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {
			// Wait for either success or error message to appear in DOM
			wait.until(ExpectedConditions.or(
					ExpectedConditions.presenceOfElementLocated(By.cssSelector(".result")),
					ExpectedConditions.presenceOfElementLocated(By.cssSelector(".validation-summary-errors li"))));

			// Check which one appeared
			List<WebElement> successElements = driver.findElements(By.cssSelector(".result"));
			if (!successElements.isEmpty()) {
				String successText = successElements.get(0).getText().trim();
				System.out.println("✅ Registration Success: " + successText);
				return successText;
			}

			List<WebElement> errorElements = driver.findElements(By.cssSelector(".validation-summary-errors li"));
			if (!errorElements.isEmpty()) {
				String errorText = errorElements.get(0).getText().trim();
				System.out.println("❌ Registration Failed: " + errorText);
				return errorText;
			}

			return "⚠️ Neither success nor error message appeared!";

		} catch (TimeoutException e) {
			return "❌ Timeout: No message appeared after clicking Register!";
		}
	}
}
