package shop.main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shop.uttils.ScreenshotUttils;
import shop.uttils.Listener;

public class LoginPage extends AbstractMethods {

	public WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[text()='Log in']")
	WebElement loginButton;

	@FindBy(css = "#Email")
	WebElement enterEmail;

	@FindBy(css = "#Password")
	WebElement pass;

	@FindBy(css = ".login-button")
	WebElement submitButton;

	@FindBy(css = ".ico-logout")
	WebElement logoutLink;

	@FindBy(xpath = "//*[.='No customer account found']")
	WebElement errorMessage;
	
	@FindBy(xpath="//*[.='The credentials provided are incorrect']")
	WebElement passMessage;

	public void clickOnTheLoginLink() {
		visibilityOf(loginButton);
		loginButton.click();

	}

	public void loginIntoTheWebSite(java.util.Map<String, String> loginDeatils) {
		for (String email : loginDeatils.keySet()) {

			clickOnTheLoginLink();
			Listener.getTest().info("Clicked on the login button");
			visibilityOf(enterEmail);
			enterEmail.clear();
			enterEmail.sendKeys(email);

			visibilityOf(pass);
			pass.clear();
			pass.sendKeys(loginDeatils.get(email));

			ScreenshotUttils.takeScreenshot(driver);
			submitButton.click();

			clickOnTheLogOutButton();

		}

	}

	public void clickOnTheLogOutButton() {
		visibilityOf(logoutLink);
		elementToBeClickable(logoutLink);
		logoutLink.click();
	}

	public String loginwithInavlidMailAddress(java.util.Map<String, String> loginDeatils) {
		String text;
		for (String email : loginDeatils.keySet()) {

			clickOnTheLoginLink();

			visibilityOf(enterEmail);
			enterEmail.clear();
			enterEmail.sendKeys(email);

			visibilityOf(pass);
			pass.clear();
			pass.sendKeys(loginDeatils.get(email));

			submitButton.click();

			return errorMessage.getText().trim();

			// clickOnTheLogOutButton();

		}
		return null;
	}
	public String loginwithInavlidMailPass(java.util.Map<String, String> loginDeatils) {
		String text;
		for (String email : loginDeatils.keySet()) {

			clickOnTheLoginLink();

			visibilityOf(enterEmail);
			enterEmail.clear();
			enterEmail.sendKeys(email);

			visibilityOf(pass);
			pass.clear();
			pass.sendKeys(loginDeatils.get(email));

			submitButton.click();

			return passMessage.getText().trim();

			// clickOnTheLogOutButton();

		}
		return null;
	}
}