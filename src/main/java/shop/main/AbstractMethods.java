package shop.main;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractMethods

{
	public  WebDriver driver;
	public   Actions a;

	public AbstractMethods(WebDriver driver) {

		this.driver = driver;
	    this.a = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".ico-cart")
	WebElement shoppingCartLink;
	
	public boolean checkForTwoElement(WebElement ele1, WebElement ele2) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.or(ExpectedConditions.visibilityOf(ele1),
					ExpectedConditions.visibilityOf(ele2)));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	public void presenceOfElementLocated(By loc) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(loc));
	}

	public void visibilityOf(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void visibilityOf15(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void elementToBeClickable(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public void elementToBeClickable(By ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	public void invisibilityOfElement(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	public void scrollToTheElement(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
	}

	public void javaexecuotrPageLoad() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
				.equals("complete"));
	}

	public void visibilityofListElement(List<WebElement> ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElements(ele));

	}

	public void hoverOnTheElement(WebElement ele) {
		a.moveToElement(ele).build().perform();
	}

	public void clickOnThElement(WebElement ele) {
		a.moveToElement(ele).click().build().perform();
	}
	
	public void selectTextByAction(WebElement element, int index)
	{
		visibilityOf(element);
		Select sel = new Select(element);
		a.moveToElement(element).click().build().perform();
		sel.selectByIndex(index);
		
	}
	public boolean isElementVisible(WebElement ele) {
		try {
			return ele != null && ele.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void Delaware(WebElement element, String visibleText) {
		Select sel = new Select(element);
		sel.selectByVisibleText(visibleText);
	}

	public void selectDropdownByVisibleText1(WebElement element, String visibleText) {

		visibilityOf(element);
		Select sel = new Select(element);
		List<WebElement> listVal = sel.getOptions();

		for (WebElement ele : listVal) {

			String text = ele.getText();
			if (text.contains(visibleText)) {

				ele.click();

			}}}
	
	public void selectDropdownByValueUsingLoop(WebElement element, String value) {
		visibilityOf(element);
	    Select sel = new Select(element);
	    List<WebElement> listVal = sel.getOptions();

	    for (WebElement ele : listVal) {
	        String optionValue = ele.getAttribute("value"); // Get value attribute
	        if (optionValue != null && optionValue.equals(value)) {
	            ele.click();
	            break; // Important: break after finding the match
	        }
	    }
	}

	public void selectDropdownByIndex(WebElement element, int index) {
        Select sel = new Select(element);
        visibilityOf(element);
        List<WebElement> listItems=sel.getOptions();
       //System.out.println("Dropdown size is :"+listItems.size());
      // System.out.println("provided index value is: "+index);
        sel.selectByIndex(index);
    }

	public String spilitString(String str, String b, String c) {

		String newStr = str.split(b)[1].trim();
		String alteredNewStr = newStr.split(c)[0].trim();
		System.out.println(alteredNewStr);
		return alteredNewStr;
	}

	
	public double selectDropdownByVisibleTextAndGetPrice(WebElement element, String visibleText) {
	    Select sel = new Select(element);
	    List<WebElement> listVal = sel.getOptions();
	    double extra = 0.0;

	    for (WebElement ele : listVal) {
	        String text = ele.getText().trim();
	        if (text.contains(visibleText)) {
	            ele.click();

	         extra=    extractExtraPrice( text);
	            return extra;
	            }

	            
	        }
	    return 0;
	    }

	    
	
	
	public double extractExtraPrice(String text) {
		double extra = 0.0;
		try {
			// Example text: "2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]"
			if (text.contains("+")) {
				String pricePart = text.substring(text.indexOf("+") + 1, text.indexOf("]")).replace("$", "").trim();
				extra = Double.parseDouble(pricePart);
			}
		} catch (Exception e) {
			System.out.println("No extra price found in: " + text);
		}
		return extra;
	}

	public void clickOnTheShoppingCartLink() {

		scrollToTheElement(shoppingCartLink);
		elementToBeClickable(shoppingCartLink);
		javaexecuotrPageLoad();
		visibilityOf(shoppingCartLink);
		shoppingCartLink.click();
	}
}
