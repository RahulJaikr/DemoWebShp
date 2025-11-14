package shop.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import shop.base.BaseTest;
import shop.dataProvider.TestDataProvider;
import shop.main.AbstractMethods;
import shop.main.ProdcutPage3;
import shop.main.WishListPage;


public class AddProductToWishListTest extends BaseTest

{
	@Test
	public void addFirst() throws InterruptedException {

		//category based on the header name
		String book = TestDataProvider.getBook();
		String computer = TestDataProvider.getComputer();
		String electronics = TestDataProvider.getElectronics();
		String apparel = TestDataProvider.getApparel();
		String digital = TestDataProvider.getDigital();
		String jewelry = TestDataProvider.getJewelry();
		String giftCards = TestDataProvider.getGiftCards();

		// Computer Subcategories
		String cDesktops = TestDataProvider.getDesktops();
		String cNotebooks = TestDataProvider.getNotebooks();
		String cAssessories = TestDataProvider.getAccessories();
		String cCamera = TestDataProvider.getCamera();
		String cPhoto = TestDataProvider.getPhoto();
		String cCellPhones = TestDataProvider.getCellPhones();

		// Product Data
		String pSmartphone = TestDataProvider.getSmartphone();
		String pShoes = TestDataProvider.getShoes();
		String pTop = TestDataProvider.getTop();
		String pAlbum = TestDataProvider.getAlbum();
		String pJewelry = TestDataProvider.getJewelryProduct();
		String pGiftCard = TestDataProvider.getGiftCard();
		
		String priceSmartphone = TestDataProvider.getSmartphonePrice();
		String priceShoes = TestDataProvider.getShoesPrice();
		String priceTop = TestDataProvider.getTopPrice();
		String priceAlbum = TestDataProvider.getAlbumPrice();
		String priceJewelry = TestDataProvider.getJewelryProductPrice();
		String priceGiftCard = TestDataProvider.getGiftCardPrice();

		Map<String, String> headerName = new HashMap();
		headerName.put(electronics, cCellPhones);
		headerName.put(apparel, null);
		headerName.put(jewelry, null);
		headerName.put(digital, null);
		headerName.put(giftCards, null);

		Map<String, List<String>> productMap = new HashMap();
		productMap.put(electronics, Arrays.asList(pSmartphone));
		productMap.put(apparel, Arrays.asList(pShoes, pTop));
		productMap.put(digital,Arrays.asList(pAlbum));
		productMap.put(jewelry, Arrays.asList(pJewelry));
		productMap.put(giftCards,Arrays.asList(pGiftCard));
		
		List <String>iteamAddTocart = new ArrayList();
		List <String>iteamRemoveFromWishList = new ArrayList();
		
		iteamAddTocart.add(pShoes);
		iteamAddTocart.add(pAlbum);
		iteamAddTocart.add(pJewelry);
		
		iteamRemoveFromWishList.add(pGiftCard);
		iteamRemoveFromWishList.add(pTop);
		iteamRemoveFromWishList.add(pSmartphone);
		
		
		
		//Login into the website
		ProdcutPage3 pp3 = new ProdcutPage3(driver);
		pp3.loginDeatils();
		pp3.verifyWishListIsEmaptyOrNot();
		pp3.selectCatagorey2(headerName, productMap);
		
		//Here productDeatil with price quantity
		Map<String,Map<String,String>>productDetails = new HashMap();

		Map<String, String> smartphoneDetails = new HashMap<>();
		smartphoneDetails.put("price", priceSmartphone);
		smartphoneDetails.put("quantity", "1");

		Map<String, String> shoesDetails = new HashMap<>();
		shoesDetails.put("price", priceShoes);
		shoesDetails.put("quantity", "1");

		Map<String, String> topDetails = new HashMap<>();
		topDetails.put("price", priceTop);
		topDetails.put("quantity", "1");

		Map<String, String> albumDetails = new HashMap<>();
		albumDetails.put("price", priceAlbum);
		albumDetails.put("quantity", "1");

		Map<String, String> jewelryDetails = new HashMap<>();
		jewelryDetails.put("price", priceJewelry);
		jewelryDetails.put("quantity", "1");

		Map<String, String> giftCardDetails = new HashMap<>();
		giftCardDetails.put("price", priceGiftCard);
		giftCardDetails.put("quantity", "1");

		
		productDetails.put(pSmartphone, smartphoneDetails);
		productDetails.put(pShoes, shoesDetails);
		productDetails.put(pTop, topDetails);
		productDetails.put(pAlbum, albumDetails);
		productDetails.put(pJewelry, jewelryDetails);
		productDetails.put(pGiftCard, giftCardDetails);
		
		WishListPage wlp = new WishListPage(driver);
		wlp.verifyAllProducts(productDetails);
		
		
		wlp.iteamRemoveToCart(iteamRemoveFromWishList);
		wlp.iteamMoveToCart(iteamAddTocart);
	}

}
