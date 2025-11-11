package shop.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AddProdcutToCart5 extends AbstractMethods {

	public WebDriver driver;

	public AddProdcutToCart5(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	 public static  Map<String, Double> productNameAndPrices=new HashMap<String, Double>();
	
	@FindBy(xpath = "//div/div/h2")
	List<WebElement> productTitle;

	@FindBy(xpath = "//div/div/input[@value='Add to cart']")
	List<WebElement> productAddTocart;

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

	@FindBy(css = ".actual-price")
	List<WebElement> priceProductPage;

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

	@FindBy(id = "product_attribute_28_7_10")
	WebElement shoesSize;

	@FindBy(id = "product_attribute_5_7_1")
	WebElement topSize;

	@FindBy(xpath = "//dd/ul/li/label/span")
	List<WebElement> colour;

	@FindBy (css=".add-to-wishlist-button")
	WebElement clickOnWishListButton;
	
	@FindBy(xpath = "//input[@value='Add to cart']")
	WebElement addCart;
	
	@FindBy(css=".recipient-name")
	WebElement recipentName;
	
	@FindBy(css=".recipient-email")
	WebElement recipentEmail;
	
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
				// System.out.println("we have value of element" + text);
				if (text.contains(ssoftware)) {

					WebElement checkToBeClicked = soft.findElement(clickOnTheCheckBox);
					elementToBeClickable(checkToBeClicked);
					// System.out.println("name of the software is " + ssoftware);
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

	public Map<String, Double> buildYourOwnComputer(String processor, String ram, String hdd, ArrayList<String> al, String qnt,
			String os) throws InterruptedException {
		count = 0;
		selectProcessorDropDoown(processor);
		selectRamDropDown(ram);
		selectHDD(hdd);
		selectOS(os);
		selectSoft(al);
		double totalPrice = verifyOtherDesktopDeatils(qnt);
		productNameAndPrices.put("Build your own computer", totalPrice);
		return productNameAndPrices;
	}

	public Map<String, Double> buildYourOwnExpensiveComputer(String processor, String ram, String hdd, String soft, String qnt) {
		count = 0;
		selectProcessorRadioButton(processor);
		selectRamRadioButton(ram);
		selectHDD(hdd);
		selectSoft(soft);
		double totalPrice = verifyOtherDesktopDeatils(qnt);
		productNameAndPrices.put("Build your own expensive computer", totalPrice);
		return productNameAndPrices;
	}

	public Map<String, Double> AddToCartBook(List<String> products) throws InterruptedException {

		System.out.println(products);
		for (String bookName : products) 
		{
			javaexecuotrPageLoad();
			System.out.println(bookName);
			visibilityofListElement(productTitle);
			boolean bookisAdded = false;

			for (int i = 0; i < productTitle.size(); i++)
{
				WebElement ele = productTitle.get(i);
				visibilityOf(ele);
				String book = ele.getText().trim();
				System.out.println("BookName From WebElement " + book);
				visibilityofListElement(priceProductPage);
				String producrPrice = priceProductPage.get(i).getText().trim();
				double count = Double.parseDouble(producrPrice);
				if (book.equalsIgnoreCase(bookName) || book.contains(bookName)) {

					// visibilityOf(addToCartButton);
					System.out.println("Value of i is " + i);

					WebElement addButton = driver.findElement(By.xpath("//div[@class='product-item']"
							+ "[.//a[normalize-space(text())='" + bookName + "']]//input[@value='Add to cart']"));
					productNameAndPrices.put(bookName, count);
					visibilityOf(addButton);
					addButton.click();
					invisibilityOfElement(spinner);
					visibilityOf(addText);
					invisibilityOfElement(addText);
					bookisAdded = true;
					break;
				}

			}
			if (!bookisAdded) {
				System.out.println("⚠️ Book not found on the page: " + bookName);
			}

		}
		return productNameAndPrices;
	}

	public void addElectorinc(List<String> products) throws InterruptedException {
		Thread.sleep(1000);

		for (String electronicName : products) {

			visibilityofListElement(productTitle);

			for (WebElement ele : productTitle) {

				int index = productTitle.indexOf(ele);
				String iteamName = ele.getText().trim();

				if (iteamName.contains(electronicName)) {

					// visibilityOf(addToCartButton);
					productAddTocart.get(index).click();
					javaexecuotrPageLoad();
					invisibilityOfElement(spinner);
					visibilityOf(addText);
					invisibilityOfElement(addText);
					break;
				}
			}
		}

	}

	public Map<String, Double> addApparelandShoes(List<String> products) throws InterruptedException {

		double ppp = 0.0;
		for (String appearlName : products) {
			Thread.sleep(1000);
			System.out.println("we are getting here list of prodcut " + appearlName);
			visibilityofListElement(productTitle);

			for (WebElement ele : productTitle) {
				int index = productTitle.indexOf(ele);
				System.out.println("Here is the indexof product " + index);
				String appear = ele.getText().trim();
				System.out.println("Getting web product  " + appear);

				if (appear.equalsIgnoreCase(appearlName)) {
					Assert.assertEquals(appear, appearlName);
					if (appear.contains("Casual Golf Belt")) {
						ppp = addBelt(index);
						productNameAndPrices.put(appear, ppp);
					}

					else if (appear.equalsIgnoreCase("50's Rockabilly Polka Dot Top JR Plus Size")) {
						ppp = addTop(index);
						productNameAndPrices.put(appear, ppp);
					} else if (appear.equalsIgnoreCase("Blue and green Sneaker")) {

						ppp = addShoes(index);
						productNameAndPrices.put(appear, ppp);
					} else {
						System.out.println("No product found");
					}
					break;
				}
			}
		}
		return productNameAndPrices;
	}

	public double addShoes(int index) {
		productAddTocart.get(index).click();
		// javaexecuotrPageLoad();
		invisibilityOfElement(spinner);
		javaexecuotrPageLoad();
		visibilityOf(shoesSize);
		selectDropdownByVisibleText1(shoesSize, "9");
		visibilityofListElement(colour);
		boolean flag = false;

		for (WebElement optionColoure : colour) {
			String shoesColour = optionColoure.getText().trim();

			if (shoesColour.equalsIgnoreCase("White")) {

				visibilityOf(optionColoure);

				optionColoure.click();
				flag = true;
				break;
			}

			if (!flag) {
				System.out.println("⚠️ White color option not found for shoes.");
			}

		}
		visibilityOf(itemPrice);
		String producrPrice = itemPrice.getText().trim();
		double count = Double.parseDouble(producrPrice);
		visibilityOf(addCart);
		addCart.click();
		invisibilityOfElement(spinner);
		visibilityOf(addText);
		invisibilityOfElement(addText);
		return count;
	}

	public double addTop(int index) {
		productAddTocart.get(index).click();
		javaexecuotrPageLoad();
		visibilityOf(itemPrice);
		String producrPrice = itemPrice.getText().trim();
		double count = Double.parseDouble(producrPrice);
		invisibilityOfElement(spinner);
		visibilityOf(topSize);
		selectDropdownByValueUsingLoop(topSize, "2X");
		visibilityOf(addCart);
		invisibilityOfElement(spinner);
		visibilityOf(addText);
		invisibilityOfElement(addText);

		return count;
	}

	public double addBelt(int index) {

		// visibilityOf(addToCartButton);
		productAddTocart.get(index).click();
		javaexecuotrPageLoad();
		invisibilityOfElement(spinner);
		visibilityOf(addText);
		invisibilityOfElement(addText);
		visibilityofListElement(priceProductPage);
		String producrPrice = priceProductPage.get(index).getText().trim();
		double count = Double.parseDouble(producrPrice);

		return count;
	}

	
	public void clickOnTheProductName(List<String> productNames)
	{
		visibilityofListElement(productTitle);
		for(String proName : productNames )
		{
			boolean productFound = false;
		int s = productTitle.size();
		for (int i=0;i<s;i++)
		{
			//System.out.println("here wehave a vlaue of i"+i);
		String webProdutName = productTitle.get(i).getText().trim();
		
		if (webProdutName.contains(proName))
		{
			WebElement clickOnTheProductTitle = productTitle.get(i);
			clickOnThElement(clickOnTheProductTitle);
			 productFound = true;
			break;
		}
		
		
	
		}
		if (!productFound) {
            System.out.println("❌ Product not found: " + proName);
        }
		
		clickOnTheAddProductToWishList();
		driver.navigate().back();
		visibilityofListElement(productTitle);
		}
	}
	
	public void clickOnTheGiftcard(List<String> productNames)
	{
		visibilityofListElement(productTitle);
		for(String proName : productNames )
		{
			boolean productFound = false;
		int s = productTitle.size();
		for (int i=0;i<s;i++)
		{
			//System.out.println("here wehave a vlaue of i"+i);
		String webProdutName = productTitle.get(i).getText().trim();
		
		if (webProdutName.contains(proName))
		{
			
			
			WebElement clickOnTheProductTitle = productTitle.get(i);
			clickOnThElement(clickOnTheProductTitle);
			 productFound = true;
			 visibilityOf(recipentName);
			 recipentName.sendKeys("Rahul Jaikr");
				recipentEmail.sendKeys("Rahul.jaikr@Conduent.com");
				
			break;
		}
					
		}
		if (!productFound) {
            System.out.println("❌ Product not found: " + proName);
        }
		
		clickOnTheAddProductToWishList();
		driver.navigate().back();
		visibilityofListElement(productTitle);
		}
	}
	
	public void clickOnTheAddProductToWishList()
	{
		elementToBeClickable(clickOnWishListButton);
		hoverOnTheElement(clickOnWishListButton);
		clickOnThElement(clickOnWishListButton);
		//clickOnWishListButton.click();
		invisibilityOfElement(spinner);
		visibilityOf(addText);
		invisibilityOfElement(addText);
		
	}
	
}