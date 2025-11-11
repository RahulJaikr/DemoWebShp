package shop.main;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddProdcutToCart3 extends AbstractMethods {

	public WebDriver driver;

	public AddProdcutToCart3(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//label[normalize-space()='Processor']/../following-sibling::dd[1]/ul[1]/li")
	List<WebElement> selectProcessor;
//
//	@FindBy(xpath = "//label[normalize-space()='RAM']/following::ul[@class='option-list'][1]")
//	List<WebElement> selectRamText;

	@FindBy(xpath = "//label[normalize-space()='RAM']/../following-sibling::dd[1]/ul[1]/li")
	List<WebElement> selectRamText;

	// label[normalize-space()='RAM']/../following-sibling::dd[1]/ul[1]/li/input[@type='radio']

//	@FindBy(xpath = "//label[normalize-space()='HDD']/following::ul[@class='option-list'][1]")
//	List<WebElement> selectHDD;

	@FindBy(xpath = "//label[normalize-space()='HDD']/../following-sibling::dd[1]/ul/li")
	List<WebElement> selectHDD;

	@FindBy(xpath = "//label[normalize-space()='Software']/../following-sibling::dd[1]/ul[1]/li")
	List<WebElement> selectSoft;

	@FindBy(xpath = "//span[@itemprop='price']")
	WebElement itemPrice;

	@FindBy(css = "input[class='qty-input']")
	WebElement qnty;
	@FindBy(css = ".add-to-cart-button")
	WebElement addToCartButton;

	@FindBy(xpath = "//p[contains(text(),'added to your')]")
	WebElement addText;

	@FindBy(xpath = "//div[@class='loading-image']")
	WebElement spinner;

	@FindBy(css = "#product_attribute_16_5_4")
	WebElement processorDropdown;

	@FindBy(css = "#product_attribute_16_6_5")
	WebElement ramDropdown;

	@FindBy(xpath = "//label[normalize-space()='OS']/../following-sibling::dd[1]/ul/li")
	List<WebElement> selectOS;

	By clickOnTheObject = By.xpath("./input[@type='radio']");
	By clickOnTheCheckBox = By.xpath("./input[@type='checkbox']");

	double count = 0;
	double actualPrice;

	private void selectOS(String opeSys) {
		visibilityofListElement(selectOS);
		for (WebElement os : selectOS) {
			visibilityOf(os);
			String text = os.getText().trim();
			// System.out.println(text);
			if (text.contains(opeSys)) {
				os.findElement(clickOnTheObject).click();
				count += extractExtraPrice(text);

				break;
			}
		}
	}

	private void selectHDD(String hdd) {
		visibilityofListElement(selectHDD);
		for (WebElement hd : selectHDD) {
			visibilityOf(hd);
			String text = hd.getText().trim();
			// System.out.println(text);
			if (text.contains(hdd)) {
				hd.findElement(clickOnTheObject).click();
				count += extractExtraPrice(text);
				break;
			}
		}
	}

	private void selectSoft(ArrayList<String> al) throws InterruptedException {
		visibilityofListElement(selectSoft);
		javaexecuotrPageLoad();
		for (String ssoftware : al) {

			for (WebElement soft : selectSoft) {
				visibilityOf(soft);
				String text = soft.getText().trim();
			//	System.out.println("we have value of element" + text);
				if (text.contains(ssoftware)) {

					WebElement checkToBeClicked = soft.findElement(clickOnTheCheckBox);
					elementToBeClickable(checkToBeClicked);
					//System.out.println("name of the software is " + ssoftware);
					if (!checkToBeClicked.isSelected()) {
						checkToBeClicked.click();
					}

					count += extractExtraPrice(text);
					// break;
					Thread.sleep(1000);
				}
			}
		}
	}

	private void selectSoft(String ssoftware) {
		visibilityofListElement(selectSoft);

		for (WebElement soft : selectSoft) {
			visibilityOf(soft);
			String text = soft.getText().trim();
			// System.out.println(text);
			if (text.contains(ssoftware)) {
				soft.findElement(clickOnTheCheckBox).click();
				count += extractExtraPrice(text);
				break;
			}
		}

	}

	private void selectProcessorDropDoown(String processor) {
		if (isElementVisible(processorDropdown)) {
			double p = selectDropdownByVisibleTextAndGetPrice(processorDropdown, processor);
			count = count + p;
		}
	}

	private void selectProcessorRadioButton(String processor2) {

		visibilityofListElement(selectProcessor);
		for (WebElement pr : selectProcessor) {
			visibilityOf(pr);
			String text = pr.getText().trim();
			// System.out.println(text);
			if (text.contains(processor2)) {
				pr.findElement(clickOnTheObject).click();
				count += extractExtraPrice(text);
				break;
			}
		}
	}

	private void selectRamDropDown(String ram) {
		if (isElementVisible(ramDropdown)) {
			double p = selectDropdownByVisibleTextAndGetPrice(ramDropdown, ram);
			count = count + p;
		}
	}

	private void selectRamRadioButton(String ram1) {
		visibilityofListElement(selectRamText);

		for (WebElement ramm : selectRamText) {
			visibilityOf(ramm);
			String text = ramm.getText();
			// System.out.println(text);
			String texts = text.substring(0, text.indexOf("GB") + 2).trim();
			if (texts.contains(ram1)) {
				ramm.findElement(clickOnTheObject).click();
				count += extractExtraPrice(text);

				break;
			}
		}

	}

	public double verifyOtherDesktopDeatils(String qnt) {

		System.out.println(count);
		visibilityOf(qnty);
		qnty.clear();
		qnty.sendKeys(qnt);

		visibilityOf(itemPrice);
		String p = itemPrice.getText().trim();
		if (qnty != null) {
			double qnity = Double.parseDouble(qnt);
			double basePrice = Double.parseDouble(p);
			actualPrice = qnity * (basePrice + count);

		}
		// System.out.println(p);
		invisibilityOfElement(spinner);
		javaexecuotrPageLoad();
		visibilityOf(addToCartButton);
		addToCartButton.click();
		javaexecuotrPageLoad();
		visibilityOf15(addText);
		String text = addText.getText().trim();
		// System.out.println(text);
		invisibilityOfElement(addText);
		return actualPrice;
	}

	public double buildYourOwnComputer(String processor, String ram, String hdd, ArrayList<String> al, String qnt,
			String os) throws InterruptedException {
		count = 0;
		selectProcessorDropDoown(processor);
		selectRamDropDown(ram);
		selectHDD(hdd);
		selectOS(os);
		selectSoft(al);
		double totalPrice = verifyOtherDesktopDeatils(qnt);

		return totalPrice;
	}

	public double buildYourOwnExpensiveComputer(String processor, String ram, String hdd, String soft, String qnt) {
		count = 0;
		selectProcessorRadioButton(processor);
		selectRamRadioButton(ram);
		selectHDD(hdd);
		selectSoft(soft);
		double totalPrice = verifyOtherDesktopDeatils(qnt);

		return totalPrice;
	}
}