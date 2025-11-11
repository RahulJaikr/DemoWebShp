package shop.main;

import java.beans.Visibility;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends AbstractMethods {

	public WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//select[@id='billing-address-select']")
	WebElement selectNewAdress;

	@FindBy(css = "#billing-please-wait")
	WebElement continue1;

	@FindBy(xpath = "//div[@class='edit-address']/div[1]/input")
	WebElement eFirstN;

	@FindBy(xpath = "//div[@class='edit-address']/div[2]/input")
	WebElement eLastN;

	@FindBy(xpath = "//div[@class='edit-address']/div[3]/input")
	WebElement eMail;

	@FindBy(xpath = "//div[@class='edit-address']/div[4]/input")
	WebElement eCompany;

	@FindBy(id = "BillingNewAddress_CountryId")
	WebElement selectContry;

	@FindBy(id = "BillingNewAddress_StateProvinceId")
	WebElement selectState;

	@FindBy(xpath = "//div[@class='edit-address']/div[7]/input")
	WebElement ecity;

	@FindBy(xpath = "//div[@class='edit-address']/div[8]/input")
	WebElement address1;

	@FindBy(xpath = "//div[@class='edit-address']/div[9]/input")
	WebElement address2;

	@FindBy(xpath = "//div[@class='edit-address']/div[10]/input")
	WebElement zipCode;

	@FindBy(xpath = "//div[@class='edit-address']/div[11]/input")
	WebElement phoneNo;

	@FindBy(css = "input[onclick='Billing.save()']")
	WebElement clickOnTheContinue;

	@FindBy(css = "#PickUpInStore")
	WebElement pickUpIn;

	@FindBy(xpath = "//input[@onclick='Shipping.save()']")
	WebElement clickOnTheContinue2;

	@FindBy(xpath = "//div[@class='method-name']")
	List<WebElement> paymentMethodList;

	By selectRadioButton = By.xpath("./div/input[@type='radio']");

	@FindBy(css = ".payment-method-next-step-button")
	WebElement clickOnTheContinue3;

	@FindBy(css = ".payment-info-next-step-button")
	WebElement clickOnTheContinue4;
	
	
	
	@FindBy(css="#PurchaseOrderNumber")
	WebElement purchaseOrderNumber;
	
	@FindBy(css=".confirm-order-next-step-button")
	WebElement confirmOrder;
	
	
	
	
	public void enterBillingDeatils(String fN, String lN, String mail, String company, String country, String state,
			String city, String ad1, String ad2, String zcode, String no) throws InterruptedException {
		
		visibilityOf(selectNewAdress);
		//System.out.println("=====Address Dropdown=====");
		//Thread.sleep(1500);
		selectDropdownByIndex(selectNewAdress, 1);
		//System.out.println("=====Address Dropdown End=====");
		// selectNewAdress.click();
		// selectDropdownByVisibleText1(selectNewAdress, "New Address");
		// visibilityOf(continue1);
		// continue1.click();

		clearAndSendKeys(eFirstN, fN);
		clearAndSendKeys(eLastN, lN);
		clearAndSendKeys(eMail, mail);
		clearAndSendKeys(eCompany, company);

		selectDropdownByIndex(selectContry, 1);
		// selectDropdownByValueUsingLoop(selectContry, country);

		selectDropdownByValueUsingLoop(selectState, state);

		ecity.sendKeys(city);
		address1.sendKeys(ad1);
		address2.sendKeys(ad2);
		zipCode.sendKeys(zcode);
		phoneNo.sendKeys(no);

		clickOnTheContinue.click();
	}
	
	public void confirmOrder()
	{
		visibilityOf(pickUpIn);
		pickUpIn.click();
		elementToBeClickable(clickOnTheContinue2);
		clickOnThElement(clickOnTheContinue2);
		
		visibilityofListElement(paymentMethodList);
		for(WebElement ele : paymentMethodList)
		{
					
		String text=	ele.getText();
		System.out.println(text);
		if(text.contains("Purchase Order"))
		{
			System.out.println("inside the line no 137");
			ele.findElement(selectRadioButton).click();
			System.out.println("element is selected after line no 137");
			break;
		}
		}
		visibilityOf(clickOnTheContinue3);
		clickOnTheContinue3.click();
		
		visibilityOf(purchaseOrderNumber);
		purchaseOrderNumber.sendKeys("756689767");
		
		clickOnTheContinue4.click();
		
		scrollToTheElement(confirmOrder);
		confirmOrder.click();
	}

	private void clearAndSendKeys(WebElement element, String text) {
		visibilityOf(element);
		element.clear();
		element.sendKeys(text);
		
	}

}
