package shop.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import shop.base.BaseTest;
import shop.main.AddProdcutToCart3;
import shop.main.CheckOutPage;
import shop.main.ProdcutPage;
import shop.main.RegisterPage;
import shop.main.ShoppingCartPage;
import shop.main.utils.ExcelUtils;

public class ProductTest extends BaseTest {

	public Map<String, Map<String, String>> expectedData = new HashMap<>();

	@Test
	public void checkProdcutOnTheCart() {
		ProdcutPage pp = new ProdcutPage(driver);
		pp.loginDeatils();
		pp.goToTheProductCart();
		pp.listOfProduct();

		HashMap<String, Double> map = new HashMap<String, Double>();
		map.put("Simple Computer", 800.00);
		map.put("Desktop PC with CDRW", 500.00);
		pp.addProductToCart(map);
	}

	@Test
	public void checkProcessorDeatils() throws Exception {

		ShoppingCartPage scp = new ShoppingCartPage(driver);
		String filePath = "src/test/resources/RegisterData.xlsx";

		String processorR = ExcelUtils.getCellValue(filePath, "Sheet2", 1, 0);
		String ramR = ExcelUtils.getCellValue(filePath, "Sheet2", 1, 1);
		String hdd = ExcelUtils.getCellValue(filePath, "Sheet2", 1, 2);
		String software1 = ExcelUtils.getCellValue(filePath, "Sheet2", 1, 3);
		String processorD = ExcelUtils.getCellValue(filePath, "Sheet2", 1, 4);
		String OS = ExcelUtils.getCellValue(filePath, "Sheet2", 1, 6);
		String processorD2 = ExcelUtils.getCellValue(filePath, "Sheet2", 1, 7);
		String OS2 = ExcelUtils.getCellValue(filePath, "Sheet2", 1, 8);
		String software4 = ExcelUtils.getCellValue(filePath, "Sheet2", 1, 9);
		String dektopTypeD = ExcelUtils.getCellValue(filePath, "Sheet2", 1, 10);
		String desktopTypeR = ExcelUtils.getCellValue(filePath, "Sheet2", 1, 14);
		String ramD = ExcelUtils.getCellValue(filePath, "Sheet2", 1, 12);
		driver.get("https://demowebshop.tricentis.com");
		String quntity1 = "1";

		ArrayList<String> al = new ArrayList();
		al.add(software1);
		al.add(software4);

		// Add products to cart
		ProdcutPage pp = new ProdcutPage(driver);
		pp.loginDeatils();
		pp.goToTheProductCart();
		pp.listOfProduct();

		// calling method giveing the example of HashMap
		HashMap<String, Double> map = new HashMap<String, Double>();
		map.put("Build your own expensive computer", 800.00);
		pp.selectProdcut(map);

		// Add first computer
		AddProdcutToCart3 aptc = new AddProdcutToCart3(driver);
		double totalPriceOfP1 = aptc.buildYourOwnExpensiveComputer(processorR, ramR, hdd, software1, quntity1);

		String ownE = Double.toString(totalPriceOfP1);

		// Add products to cart 2
		driver.navigate().back();
		pp.goToTheProductCart();

		HashMap<String, Double> map2 = new HashMap<String, Double>();
		map2.put("Build your own computer", 800.00);

		// Add Second Computer
		pp.selectProdcut(map2);

		double totalPriceOfP2 = aptc.buildYourOwnComputer(processorD, ramD, hdd, al, quntity1, OS2);
		String ownC = Double.toString(totalPriceOfP2);

		// ✅ VERIFICATION - Add this part

		scp.clickOnTheShoppingCartLink();

		Map<String, String> computer1 = new HashMap<>();
		computer1.put("Processor", processorR);
		computer1.put("RAM", ramR);
		computer1.put("HDD", hdd);
		// computer1.put("Software", software1);
		computer1.put("Price", ownE);
		computer1.put("quntity", quntity1);

		Map<String, String> computer2 = new HashMap<>();
		computer2.put("Processor", processorD);
		computer2.put("RAM", ramD);
		computer2.put("HDD", hdd);
		computer2.put("Software", combineSoftware(al));
		computer2.put("Price", ownC);
		computer2.put("OS", OS2);
		computer2.put("quntity", quntity1);

		Map<String, String> shoes = new HashMap<>();
		shoes.put("Size", "9");
		shoes.put("Color", "White");
		shoes.put("quntity", quntity1);
		shoes.put("Price","1.00");
		
		Map<String, String> belt = new HashMap<>();
		belt.put("Price","1.00");
		belt.put("quntity", quntity1);

		Map<String, String> book1 = new HashMap<>();
		book1.put("Price", "10.00");
		book1.put("quntity", quntity1);

		Map<String, String> book2 = new HashMap<>();
		book2.put("Price", "24.00");
		book2.put("quntity", quntity1);

		expectedData.put("Build your own expensive computer", computer1);
		expectedData.put("Build your own computer", computer2);
		expectedData.put("Blue and green Sneaker", shoes);
		expectedData.put("Casual Golf Belt", belt);
		expectedData.put("Computing and Internet", book1);
		expectedData.put("Computing and Internet", book2);
		
		Map<String, Map<String, String>> actualData = scp.getCartData();

		verifyProductFromUIExcpetced(actualData);

		scp.checkOut();
		billingTest();
	}

	public void verifyProductFromUIExcpetced(Map<String, Map<String, String>> actualDataVerification) {

		for (String expectedProductName : expectedData.keySet()) {
			System.out.println("Checking " + expectedProductName);

			if (!actualDataVerification.containsKey(expectedProductName)) {
				Assert.fail("Product name is not found on the cart" + expectedProductName);
			}

			Map<String, String> expectd = expectedData.get(expectedProductName);
			Map<String, String> actual = actualDataVerification.get(expectedProductName);

//			System.out.println(expectd);
//			System.out.println("           ==============          ");
//			System.out.println(actual);
			for (String key : expectd.keySet()) {
				String expectedValue = expectd.get(key);
				String actualValue = actual.get(key);

				// System.out.println("I am excpecting " + expectedValue + " But i got " +
				// actualValue);

				if (expectedValue != null && !expectedValue.isEmpty()) {
					if (actualValue == null) {
						Assert.fail("❌ " + key + " not found for " + expectedProductName);
					} else if (actualValue.contains(expectedValue)) {
						// System.out.println("✅ PASS: " + key + " - " + expectedValue);
					} else {
						Assert.fail("❌ " + key + " mismatch for " + expectedProductName + " | Expected: "
								+ expectedValue + " | Actual: " + actualValue);
					}
				}
			}
			// System.out.println("🎉 VERIFIED: " + expectedProductName);
		}

	}

	public void billingTest() throws Exception {
		CheckOutPage cop = new CheckOutPage(driver);

		String filePath = "src/test/resources/RegisterData.xlsx";

		// Customer Information - Sheet3
		String firstName = ExcelUtils.getCellValue(filePath, "Sheet3", 1, 0);
		String lastName = ExcelUtils.getCellValue(filePath, "Sheet3", 1, 1);
		String email = ExcelUtils.getCellValue(filePath, "Sheet3", 1, 2);
		String company = ExcelUtils.getCellValue(filePath, "Sheet3", 1, 3);
		String country = ExcelUtils.getCellValue(filePath, "Sheet3", 1, 4);
		String state = ExcelUtils.getCellValue(filePath, "Sheet3", 1, 5);
		String city = ExcelUtils.getCellValue(filePath, "Sheet3", 1, 6);
		String address1 = ExcelUtils.getCellValue(filePath, "Sheet3", 1, 7);
		String address2 = ExcelUtils.getCellValue(filePath, "Sheet3", 1, 8);
		String zipCode = ExcelUtils.getCellValue(filePath, "Sheet3", 1, 9);
		String phoneNumber = ExcelUtils.getCellValue(filePath, "Sheet3", 1, 10);
		String faxNumber = ExcelUtils.getCellValue(filePath, "Sheet3", 1, 11);
		cop.enterBillingDeatils(firstName, lastName, email, company, country, state, city, address1, address2, zipCode,
				phoneNumber);

		cop.confirmOrder();

	}

	private void addSoftwareIfNotEmpty(ArrayList<String> all, String SoftwareAL) {
		if (SoftwareAL != null & !SoftwareAL.trim().isEmpty()) {
			all.add(SoftwareAL);
		}
	}

	private String combineSoftware(ArrayList<String> listedSoftware) {
		if (listedSoftware.isEmpty()) {
			return "";
		}
		return String.join(", ", listedSoftware);

	}
}
