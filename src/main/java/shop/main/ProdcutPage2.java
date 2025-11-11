package shop.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ProdcutPage2 extends AbstractMethods {

	public WebDriver driver;
	public AddProdcutToCart4 apc4;

	public ProdcutPage2(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		apc4 = new AddProdcutToCart4(driver);
	}

	By checkitle = By.xpath("//div/div/h2");
	@FindBy(xpath = "//*[text()='Log in']")
	WebElement loginButton;

	@FindBy(css = "#Email")
	WebElement enterEmail;

	@FindBy(css = "#Password")
	WebElement pass;

	@FindBy(xpath = "//ul[@class='top-menu']/li/a[contains(text(),'Computers')]")
	WebElement clickOnDesktopHeader;

	@FindBy(xpath = "//ul[@class='top-menu']//a[contains(text(),'Desktops')]")
	WebElement desktopFromList;

	@FindBy(xpath = "//div/div/h2")
	List<WebElement> productTitle;

	@FindBy(xpath = "//div/div[@class='prices']")
	List<WebElement> priceofAllproduct;

	@FindBy(xpath = "//div/div/input[@value='Add to cart']")
	List<WebElement> productAddTocart;

	@FindBy(xpath = "//div[@class='loading-image']")
	WebElement spinner;

	@FindBy(xpath = "//p[contains(text(),'added to your')]")
	WebElement addText;

	@FindBy(css = ".login-button")
	WebElement submitButton;

	@FindBy(css = "a.ico-cart")
	WebElement shoppingCart;

	@FindBy(xpath = "//td[@class='remove-from-cart']")
	List<WebElement> checkBoxCount;

	@FindBy(xpath = "//div[contains(text(),'Your Shopping Cart is empty!')]")
	WebElement shoppingCartEmptyMesage;

	@FindBy(css = ".common-buttons input.button-2.update-cart-button")
	WebElement updateCartButton;

	public void loginDeatils() {
		visibilityOf(loginButton);
		loginButton.click();

		visibilityOf(enterEmail);
		enterEmail.clear();
		enterEmail.sendKeys("Rahul@conduent.com");
		visibilityOf(pass);
		pass.clear();
		pass.sendKeys("Enter@123");
		elementToBeClickable(submitButton);
		submitButton.click();

		// checking shopping cart is empty or it has product added already
		visibilityOf(shoppingCart);
		String text = shoppingCart.getText();

		if (text.contains("0")) {
			System.out.println("cart is emapty");
		} else {
			clickOnTheShoppingCartLink();
			visibilityofListElement(checkBoxCount);

			for (WebElement ele : checkBoxCount) {
				if (!ele.isSelected()) {
					WebElement clickOnThe = ele.findElement(By.xpath("./input"));
					elementToBeClickable(clickOnThe);
					clickOnThe.click();
				}
			}

			elementToBeClickable(updateCartButton);
			updateCartButton.click();
			visibilityOf(shoppingCartEmptyMesage);
			String texts = shoppingCartEmptyMesage.getText().trim();
			Assert.assertEquals(texts, "Your Shopping Cart is empty!", "Cart is clear now");

		}
	}

	public void goToTheProductCart() {
		javaexecuotrPageLoad();
		visibilityOf(clickOnDesktopHeader);
		hoverOnTheElement(clickOnDesktopHeader);
		// clickOnDesktopHeader.click();
		visibilityOf(desktopFromList);
		desktopFromList.click();

	}

	public void listOfProduct() {
		javaexecuotrPageLoad();
		visibilityofListElement(productTitle);

		for (WebElement ele : productTitle) {
			javaexecuotrPageLoad();
			int index = productTitle.indexOf(ele);

			String pTitle = ele.getText().trim();
			String pPrice = priceofAllproduct.get(index).getText().trim();
			// System.out.println(pPrice);
			// System.out.println(pTitle+" is my product "+ pPrice+" of my ");
		}
	}

	public void addProductToCart(Map<String, Double> pDetails) {

		javaexecuotrPageLoad();
		visibilityofListElement(productTitle);

		for (WebElement ele : productTitle) {
			int index = productTitle.indexOf(ele);
			String pName = ele.getText().trim();
			// System.out.println(pName);

			for (String pn : pDetails.keySet()) {
				// System.out.println(pn);

				if (pName.contains(pn)) {
					visibilityofListElement(priceofAllproduct);
					String pPrice = priceofAllproduct.get(index).getText().trim();
					double d = pDetails.get(pn);
					String pPrc = String.valueOf(d);

					if (pPrice.contains(pPrc)) {
						visibilityofListElement(productAddTocart);
						productAddTocart.get(index).click();
					}

				}
			}
		}
	}

	public void selectProdcut(Map<String, Double> pDetails) throws InterruptedException {

		javaexecuotrPageLoad();
		visibilityofListElement(productTitle);

		for (WebElement ele : productTitle) {
			boolean flag = false;

			visibilityOf(ele);

			int index = productTitle.indexOf(ele);
//System.out.println(index);
			String pName = ele.getText().trim();

			for (String pn : pDetails.keySet()) {
				// System.out.println(pn);

				if (pName.contains(pn)) {

					visibilityofListElement(priceofAllproduct);
					visibilityofListElement(productAddTocart);
					javaexecuotrPageLoad();
					invisibilityOfElement(spinner);

					visibilityofListElement(productTitle);
					productAddTocart.get(index).click();
					Thread.sleep(1000);
					flag = true;

					break;
//			 visibilityOf(addText);
//		String text =	 addText.getText().trim();
//		System.out.println(text);
				}

			}
			if (flag) {
				Thread.sleep(1000);
				break;

			}

		}
	}

	public void selectCatagorey(Map<String, String> catagoryMap, Map<String, List<String>> productMap,
			Map<String, String> desktopConfig) throws InterruptedException {

		for (String productType : catagoryMap.keySet()) {

			String mainCatagory = productType;
			System.out.println(mainCatagory);
			String subCatagory = catagoryMap.get(productType);
			System.out.println(subCatagory);

			List<String> productName = productMap.get(mainCatagory);

			// selecting the category so we can navigate through it
			selectCategory(mainCatagory, subCatagory);

			// selecting case statement so we can chose product according to the category
			selectProductandAddTocart(mainCatagory, productName, desktopConfig);

		}
	}

	public void selectCategory(String mainCatagory, String subCatagory) {
		String getCategory = "";
		String getSubCat = "";
		WebElement mainCat = driver
				.findElement(By.xpath("//ul[@class='top-menu']//a[normalize-space(text())='" + mainCatagory + "']"));
		getCategory = mainCat.getText();

		visibilityOf(mainCat);
		hoverOnTheElement(mainCat);

		if (subCatagory == null || subCatagory.trim().isEmpty() || subCatagory.equalsIgnoreCase("null")) {
			System.out.println("Main Category block for +" + mainCatagory);
			mainCat.click();

		} else {
			WebElement subCat1 = driver
					.findElement(By.xpath("//ul[@class='top-menu']//a[normalize-space(text())='" + mainCatagory + "']"
							+ "/following-sibling::ul//a[normalize-space(text())='" + subCatagory + "']"));
			getSubCat = subCat1.getText();
			visibilityOf(subCat1);
			System.out.println("Sub Category block for +" + subCatagory);
			subCat1.click();

		}
		System.out.println("Line no 246 " + getCategory + " we have sub " + getSubCat);
		System.out.println("==================================");
		// addProductToCart( productNames);
	}

	public void selectProductandAddTocart(String mainCatagory, List<String> productNames,

			Map<String, String> desktopConfig) throws InterruptedException {

		switch (mainCatagory) {

		case "Books":

			System.out.println("Inside the book switch statment");
			// System.out.println(productNames);
			apc4.AddToCartBook(productNames);
			break;

		case "Computers":

			System.out.println("Inside the Computer switch statment");
			ifCaseIsComputer(desktopConfig, productNames);
			break;

//		case "Electronics":

//			System.out.println("Inside the switch case Electronics");
//			apc4.addElectorinc(productNames);
//			break;

		case "Apparel & Shoes":

			System.out.println("Inside the Apparel & Shoes switch case");
			// System.out.println(productNames);
			apc4.addApparelandShoes(productNames);
			break;

		default:

			System.out.println("Unknown category: ");
		}
	}

	public void ifCaseIsComputer(Map<String, String> desktopConfig, List<String> productNames)
			throws InterruptedException {
		String processorR = desktopConfig.get("ProcessorR");
		String processorD = desktopConfig.get("ProcessorD");
		String ramR = desktopConfig.get("RamR");
		String ramD = desktopConfig.get("RamD");
		String os1 = desktopConfig.get("OS");
		String os2 = desktopConfig.get("OS2");
		String hdd = desktopConfig.get("HDD");
		String soft1 = desktopConfig.get("Software1");
		String soft2 = desktopConfig.get("Software2");

		ArrayList<String> softwarelist = new ArrayList();
		softwarelist.add(soft1);
		softwarelist.add(soft2);

		for (String productActulname : productNames) {
			goToTheProductSepcification(productActulname);

			if (productActulname.contains("Build your own computer")) {
				apc4.buildYourOwnComputer(processorD, ramD, hdd, softwarelist, "1", os2);
				driver.navigate().back();
			} else if (productActulname.contains("Build your own expensive computer")) {
				apc4.buildYourOwnExpensiveComputer(processorR, ramR, hdd, soft2, "1");
				driver.navigate().back();
			} else {
				System.out.println("No product Found");
			}
		}
	}

	public void addProductToCart2(List<String> productNames) throws InterruptedException {
		javaexecuotrPageLoad();
		visibilityofListElement(productTitle);

		for (int i = 0; i < productTitle.size(); i++) {
			WebElement productNameEle = productTitle.get(i);
			boolean flag = false;
			visibilityOf(productNameEle);
			String eleProductName = productNameEle.getText().trim();

			System.out.println(" 265 before for block Element product name " + eleProductName);
			for (String listProductNames : productNames) {
				System.out.println(" 267 here we have a prodcut name from ArrayList " + listProductNames);

				System.out.println("==================================");
				if (eleProductName.contains(listProductNames)) {

					visibilityofListElement(priceofAllproduct);
					javaexecuotrPageLoad();
					invisibilityOfElement(spinner);

					visibilityofListElement(productTitle);
					String productPrice = priceofAllproduct.get(i).getText().trim();

					visibilityofListElement(productAddTocart);
					productAddTocart.get(i).click();

					Thread.sleep(1000);
					flag = true;
					break;
				}

				if (flag) {
					Thread.sleep(1000);
					break;
				}
			}
		}
	}

	public void goToTheProductSepcification(String productNames) throws InterruptedException {
		//System.out.println("====Start====");
		//System.out.println(productNames);
		
		javaexecuotrPageLoad();
		visibilityofListElement(productTitle);
		//System.out.println("Product title size :"+productTitle.size());
		for (int i = 0; i < productTitle.size(); i++) 
		{
			//System.out.println("i Val = "+i);
//			WebElement productNameEle = productTitle.get(i);				
//			String eleProductName = productNameEle.getText().trim();
			Thread.sleep(2000);
			String eleProductName = productTitle.get(i).getText().trim();
			//System.out.println(eleProductName);
			if (eleProductName.contains(productNames)) {
				
				javaexecuotrPageLoad();
				visibilityofListElement(productAddTocart);
				productAddTocart.get(i).click();
				break;
			}

		}
		//System.out.println("====END====");
	}
}