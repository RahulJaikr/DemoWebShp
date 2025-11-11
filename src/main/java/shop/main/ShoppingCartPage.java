package shop.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.Map;

public class ShoppingCartPage extends AbstractMethods {

	public WebDriver driver;

	public ShoppingCartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".ico-cart")
	WebElement shoppingCartLink;

	@FindBy(xpath = "//td/div[@class='attributes']")
	WebElement cartDeatils;

	@FindBy(xpath = "//tr[@class='cart-item-row']//a[@class='product-name']")
	List<WebElement> productNames;

	@FindBy(xpath = "//tr[@class='cart-item-row']//div[@class='attributes']")
	List<WebElement> productAttributes;

	@FindBy(xpath = "//tr[@class='cart-item-row']/td[@class='unit-price nobr']")
	List<WebElement> iPrice;

	@FindBy(css = "input.qty-input")
	List<WebElement> quntity;

	@FindBy(xpath = "//td/input[@class='qty-input']")
	List<WebElement> qnty;

	@FindBy(css = "#CountryId")
	WebElement countryInput;

	@FindBy(css = "#StateProvinceId")
	WebElement stateOption;

	@FindBy(css = ".shipping-results")
	List<WebElement> shipRate;

	@FindBy(css = ".estimate-shipping-button")
	WebElement clickOnTheEstimateButton;

	@FindBy(css = "#termsofservice")
	WebElement selectTermConditons;

	@FindBy(css = "#checkout")
	WebElement clickOnTheCheckOutButton;

	@FindBy(css = ".zip-input")
	WebElement zipCode;
	
	By state = By.cssSelector("#StateProvinceId");
//	List<WebElement> productNames1 = driver.findElements(By.xpath("//tr[@class='cart-item-row']//a[@class='product-name']"));
//	List<WebElement> productAttributes1 = driver.findElements(By.xpath("//tr[@class='cart-item-row']//div[@class='attributes']"));

//	public void verifyAddProductIntoTheCart()
//	{
//	 javaexecuotrPageLoad();
//	 visibilityOf(cartDeatils);
//	System.out.println(cartDeatils.getText()); ;
//	}

	public Map<String, Map<String, String>> getCartData() {
		clickOnTheShoppingCartLink();

		visibilityofListElement(productNames);
		visibilityofListElement(productAttributes);
		visibilityofListElement(iPrice);
		visibilityofListElement(quntity);

		Map<String, Map<String, String>> cartData = new HashMap<>();

		for (WebElement ele : productNames) {
			int index = productNames.indexOf(ele);
			String pn = ele.getText();
			String att = productAttributes.get(index).getText();

			String price = iPrice.get(index).getText();
			String quntity1 = quntity.get(index).getAttribute("value");
			System.out.println("Value :" + quntity1);

			String[] lines = att.split("\\r?\\n");

			HashMap<String, String> dataFromUI = new HashMap();
			List<String> softwareList = new ArrayList<>();
			for (String line : lines) {
				String[] parts = line.split(":", 2);
				String key = parts[0];
				String value = parts[1];

				if (key.equals("Software")) {
					softwareList.add(value);
				} else {
					dataFromUI.put(key, value);
				}
			}
			dataFromUI.put("quntity", quntity1);
			dataFromUI.put("Price", price);

			if (!softwareList.isEmpty()) {
				String combinedSoftware = String.join(",", softwareList);
				dataFromUI.put("Software", combinedSoftware);
			}

			String uniqueKey = pn;
			int doublicateKeyCount = 1;

			while (cartData.containsKey(uniqueKey)) {
				doublicateKeyCount++;
				uniqueKey = uniqueKey + "_" + doublicateKeyCount;
			}
			cartData.put(uniqueKey, dataFromUI);
//			System.out.println("Product " + (index + 1) + ": " + pn);
//			System.out.println("Details:\n" + att);
//
//			System.out.println("Total iteam no are " + quntity1 + "and price is " + price);
//			System.out.println("----------------------------------");
//			
		}
		return cartData;
	}

	public void verifyData() {
		Map<String, Map<String, String>> expectedData = getCartData();
	}

	public void checkOut() throws InterruptedException {
		visibilityOf(countryInput);
		countryInput.click();
		selectDropdownByIndex(countryInput, 1);
		//selectDropdownByValueUsingLoop(countryInput, "United States");
		
		Thread.sleep(1500);
		
		presenceOfElementLocated(state);
		stateOption.click();
		selectDropdownByVisibleText1(stateOption, "Florida");

		zipCode.click();
		zipCode.sendKeys("470661");
		visibilityOf(clickOnTheEstimateButton);
		clickOnTheEstimateButton.click();

		
		javaexecuotrPageLoad();
		visibilityofListElement(shipRate);

		for (WebElement ele : shipRate) {
			// int index = shipRate.indexOf(ele);;

			String text = ele.getText();
			//System.out.println(text);
			if (text.contains("Next Day Air (0.00)")) {
				System.out.println("we have a good air option");

			}
		}

		//visibilityOf(selectTermConditons);
		selectTermConditons.click();
		
		clickOnTheCheckOutButton.click();
	}

	public void verfiyProduct1() {
		for (int i = 0; i < productNames.size(); i++) {
			String name = productNames.get(i).getText();
			String attributes = productAttributes.get(i).getText();

			System.out.println("Product " + (i + 1) + ": " + name);
			System.out.println("Details:\n" + attributes);
			System.out.println("----------------------------------");
		}
	}

}
