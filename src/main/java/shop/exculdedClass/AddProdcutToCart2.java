package shop.exculdedClass;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shop.main.AbstractMethods;

public class AddProdcutToCart2 extends AbstractMethods {

	public WebDriver driver;

	public AddProdcutToCart2(WebDriver driver) {
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
	By clickOnTheCheckBox = By.xpath("./input");

	double count=0;
	private void selectOS(String opeSys) {
		visibilityofListElement(selectOS);
		for (WebElement os : selectOS) {
			visibilityOf(os);
			String text = os.getText().trim();
			if (text.contains(opeSys)) {
				os.findElement(clickOnTheObject).click();
				break;
			}
		}
	}

	private void selectHDD(String hdd) {
		visibilityofListElement(selectHDD);
		for (WebElement hd : selectHDD) {
			visibilityOf(hd);
			String text = hd.getText().trim();
			if (text.contains(hdd)) {
				hd.findElement(clickOnTheObject).click();
				break;
			}
		}
	}

	private void selectSoft(String software1) {
		visibilityofListElement(selectSoft);
		for (WebElement soft : selectSoft) {
			visibilityOf(soft);
			String text = soft.getText().trim();
			if (text.contains(software1)) {
				soft.findElement(clickOnTheCheckBox).click();
				break;
			}
		}
	}

	private void selectProcessorDropDoown(String processor) {
		if (isElementVisible(processorDropdown)) 
		{
			selectDropdownByVisibleText1(processorDropdown, processor);
		}
	}

	private void selectProcessorRadioButton(String processor2) {

		visibilityofListElement(selectProcessor);
		for (WebElement pr : selectProcessor) {
			visibilityOf(pr);
			String text = pr.getText().trim();
			System.out.println(text);
			if (text.contains(processor2)) {
				pr.findElement(clickOnTheObject).click();
				break;
			}
		}
	}

	private void selectRamDropDown(String ram) {
		if (isElementVisible(ramDropdown)) {
			selectDropdownByVisibleText1(ramDropdown, ram);
		}
	}

	private void selectRamRadioButton(String ram1) {
		visibilityofListElement(selectRamText);

		for (WebElement ramm : selectRamText) {
			visibilityOf(ramm);
			String text = ramm.getText();
			String texts = text.substring(0, text.indexOf("GB") + 2).trim();
			if (texts.contains(ram1)) {
				ramm.findElement(clickOnTheObject).click();
				System.out.println(ramm);
				break;
			}
		}

	}

	public void verifyOtherDesktopDeatils(String qnt) {
		visibilityOf(qnty);
		qnty.clear();
		qnty.sendKeys(qnt);

		visibilityOf(itemPrice);
		String p = itemPrice.getText().trim();
		System.out.println(p);
		invisibilityOfElement(spinner);
		javaexecuotrPageLoad();
		visibilityOf(addToCartButton);
		addToCartButton.click();
		javaexecuotrPageLoad();
		visibilityOf15(addText);
		String text = addText.getText().trim();
		System.out.println(text);
	}

	public void buildYourOwnComputer(String processor,String ram,String hdd, String soft,String qnt,String os)
	{
		selectProcessorDropDoown( processor);
		selectRamDropDown( ram);
		selectHDD(hdd);
		selectOS(os);
		selectSoft(soft);
		verifyOtherDesktopDeatils( qnt);
	}
	public void buildYourOwnExpensiveComputer(String processor,String ram,String hdd, String soft,String qnt)
	{
		selectProcessorRadioButton( processor);
		selectRamRadioButton(ram);
		selectHDD(hdd);
		selectSoft(soft);
		verifyOtherDesktopDeatils( qnt);
	}
}