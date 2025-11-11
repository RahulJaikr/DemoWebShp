package shop.main;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class WishListPage extends AbstractMethods {
	public WebDriver driver;

	public WishListPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div.header-links a.ico-wishlist ")
	WebElement wishListHeader;

	@FindBy(xpath = "//tbody/tr/td[@class='remove-from-cart']/input")
	List<WebElement> removeFromCart;

	@FindBy(xpath = "//tbody/tr/td[@class='add-to-cart']/input")
	List<WebElement> addTocart;

	@FindBy(xpath = "//tbody/tr/td/a")
	List<WebElement> productName;

	@FindBy(xpath = "//tbody/tr/td[@class='unit-price nobr']/span[2]")
	List<WebElement> productPrice;

	@FindBy(xpath = "	//tbody/tr/td[@class='qty nobr']/input")
	List<WebElement> productQuantity;

	@FindBy(css = ".button-2.update-wishlist-button")
	WebElement updateWishListButton;

	@FindBy(css = ".button-2.wishlist-add-to-cart-button")
	WebElement moveProductToCartButton;

	public void verifyProductName(String pn, String price, String quantity) throws InterruptedException {

		wishListHeader.click();
		visibilityofListElement(productName);
		boolean flag = false;
		for (int i = 0; i < productName.size(); i++) {

			String text = productName.get(i).getText().trim();
			if (text.contains(pn)) {

				String price1 = productPrice.get(i).getText().trim();
				String quantity1 = productQuantity.get(i).getAttribute("value").trim();
				System.out.println(
						"We are verifying " + pn + " here is the quntity " + quantity1 + "and price is +" + price1);
				Assert.assertEquals(text, pn, "We have find actual and expected product " + pn);
				Assert.assertEquals(price1, price, "Price of the product " + price);
				Assert.assertEquals(quantity1, quantity, "Quantity of the product " + quantity);

				flag = true;

				verifyProductPrice(i, price);
				Thread.sleep(1200);
				verifyProductQuantity(i, quantity);
				break;
			}
		}
		if (!flag) {
			Assert.fail("❌ Product not found in wishlist: " + pn);
		}

	}

	public void verifyAllProducts(Map<String, Map<String, String>> productDetails) throws InterruptedException {
		for (Map.Entry<String, Map<String, String>> entry : productDetails.entrySet()) {
			String productName = entry.getKey();
			String price = entry.getValue().get("price");
			String qty = entry.getValue().get("quantity");

			verifyProductName(productName, price, qty);
		}
	}

	public void iteamMoveToCart(List<String> list) {

		for (String gPn : list) {
			System.out.println("here we are ging to add productotCart " + gPn);
			visibilityofListElement(productName);
			boolean flag = false;
			for (int i = 0; i < productName.size(); i++) {

				String text = productName.get(i).getText().trim();

				if (text.contains(gPn)) {

					visibilityofListElement(addTocart);
					addTocart.get(i).click();

					flag = true;
					break;
				}
			}
			if (!flag) {
				System.out.println("We are not able select product");
			}
		}
		elementToBeClickable(moveProductToCartButton);
		moveProductToCartButton.click();
	}

	public void iteamRemoveToCart(List<String> list) {

		for (String gPn : list) {
			
			System.out.println("here we are ging to remive from wishlist " + gPn);
			visibilityofListElement(productName);
			boolean flag = false;
			
			for (int i = 0; i < productName.size(); i++) {
				String text = productName.get(i).getText().trim();

				if (text.contains(gPn)) {
					
					visibilityofListElement(removeFromCart);
					removeFromCart.get(i).click();
					flag = true;
					break;
				}
			}
			if (!flag) {
				System.out.println("We are not able select product");
			}
		}
		elementToBeClickable(updateWishListButton);
		updateWishListButton.click();
	}

	public void verifyProductPrice(int index, String price) {
		visibilityofListElement(productPrice);
		String text = productName.get(index).getText().trim();
		if (text.contains(price)) {
			Assert.assertEquals(text, price, "We have find actual and expected product+" + price);
		}
	}

	public void verifyProductQuantity(int index, String quantity) {
		visibilityofListElement(productPrice);
		String text = productName.get(index).getText().trim();
		if (text.contains(quantity)) {
			Assert.assertEquals(text, quantity, "We have find actual and expected product+" + quantity);
		}
	}

}
