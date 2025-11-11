package shop.exculdedClass;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddProdcutToCart extends AbstractMethods {

	public WebDriver driver;

	public AddProdcutToCart(WebDriver driver) {
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

	public void productDetails(String processor, String ram, String hdd, String software1, String qnt, String opeSys) {

		try {
			if (isElementVisible(processorDropdown)) {
				selectDropdownByVisibleText1(processorDropdown, processor);

			} else if (!selectProcessor.isEmpty()) {
				visibilityofListElement(selectProcessor);

				for (WebElement pr : selectProcessor) {
					visibilityOf(pr);

					String text = pr.getText().trim();
					// String text = tex.substring(0, tex.in).trim();
					System.out.println(text);
					if (text.contains(processor)) {
						pr.findElement(clickOnTheObject).click();

						break;
					}

				}
			} else {
				System.out.println("Processor option not found");
			}
		}

		catch (Exception e) {
			System.out.println("Processor selection skipped");
		}
		try {
			if (isElementVisible(ramDropdown)) {
				selectDropdownByVisibleText1(ramDropdown, ram);

			} else if (!selectRamText.isEmpty()) {

				visibilityofListElement(selectRamText);

				for (WebElement ramm : selectRamText) {
					visibilityOf(ramm);

					String text = ramm.getText();

					String texts = text.substring(0, text.indexOf("GB") + 2).trim();

					if (texts.contains(ram)) {
						System.out.println("120");
						ramm.findElement(clickOnTheObject).click();
						// ramm.click();
						System.out.println(ramm);
						break;

					}
				}
			} else {
				System.out.println("Ram option not found");
			}

		} catch (Exception e) {
			// System.out.println(e.printStackTrace());
			System.out.println("Ram selection skipped");
			// System.out.println(e.printStackTrace());
		}

		try {
			if (!selectHDD.isEmpty()) {

				visibilityofListElement(selectHDD);
				for (WebElement hd : selectHDD) {

					visibilityOf(hd);

					String text = hd.getText().trim();
					System.out.println(text);
					if (text.contains(hdd)) {
						hd.findElement(clickOnTheObject).click();
						break;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("HDD option skipped");
		}

		try {
			if (!selectSoft.isEmpty()) {
				visibilityofListElement(selectSoft);
				for (WebElement soft : selectSoft) {

					visibilityOf(soft);

					String text = soft.getText().trim();
					System.out.println(text);
					if (text.contains(software1)) {
						soft.findElement(clickOnTheCheckBox).click();
						break;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Soft option skipped");
		}

		try {
			if (!selectOS.isEmpty()) {
				
				System.out.println(173);
				System.out.println(opeSys);
				
				visibilityofListElement(selectOS);
				for (WebElement os : selectOS) 
				{
					
					System.out.println(os);
					visibilityOf(os);
					System.out.println(opeSys);
					String text = os.getText().trim();
					System.out.println(text);
					if (text.contains(opeSys)) {
						System.out.println(182);
						os.findElement(clickOnTheObject).click();
						break;
					}

				}

			}
		} catch (Exception e) {
			System.out.println("OS Not Found");
		}
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

}
