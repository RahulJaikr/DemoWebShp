package shop.main;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ProdcutPage extends AbstractMethods {

	public WebDriver driver;

	public ProdcutPage(WebDriver driver) {
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

	public void addProductToCart(java.util.Map<String, Double> pDetails) {

		javaexecuotrPageLoad();
		visibilityofListElement(productTitle);

		for (WebElement ele : productTitle) {
			int index = productTitle.indexOf(ele);
			String pName = ele.getText().trim();
		//	System.out.println(pName);

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

	public void selectProdcut(java.util.Map<String, Double> pDetails) throws InterruptedException {

		javaexecuotrPageLoad();
		visibilityofListElement(productTitle);

		for (WebElement ele : productTitle) {
			boolean flag = false;

			visibilityOf(ele);

			int index = productTitle.indexOf(ele);
            //System.out.println(index);
			String pName = ele.getText().trim();

			for (String pn : pDetails.keySet()) {
				//System.out.println(pn);

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
//			        visibilityOf(addText);
//		            String text =	 addText.getText().trim();
//	              	System.out.println(text);
				}

			}
			if (flag) {
				Thread.sleep(1000);
				break;

			}

		}
	}
	
	//ul[@class='top-menu']//a[normalize-space(text())='Books']
	
}