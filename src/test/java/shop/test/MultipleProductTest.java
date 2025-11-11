package shop.test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import shop.base.BaseTest;
import shop.main.AddProdcutToCart5;
import shop.main.ProdcutPage;
import shop.main.ProdcutPage2;
import shop.main.ProdcutPage3;
import shop.main.ShoppingCartPage2;
import shop.main.utils.ExcelUtils;

public class MultipleProductTest extends BaseTest {

	public Map<String, Map<String, String>> expectedData = new HashMap<>();

	@Test
	public void addFirst() throws InterruptedException {

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
		// driver.get("https://demowebshop.tricentis.com");
		String quntity1 = "1";

		ProdcutPage3 pp3 = new ProdcutPage3(driver);
		// All the header names in which under we will get productname
		
		Map<String, String> headerName = new HashMap();
		headerName.put("Books", null);
		headerName.put("Computers", "Desktops");
		/// headerName.put("Electronics", "Camera, photo");
		headerName.put("Apparel & Shoes", null);

		Map<String, List<String>> productMap = new HashMap();
		productMap.put("Books", Arrays.asList("Computing and Internet", "Fiction"));
		productMap.put("Computers", Arrays.asList("Build your own computer", "Build your own expensive computer"));
		// productMap.put("Electronics",Arrays.asList("Digital SLR Camera 12.2
		// Mpixel"));
		productMap.put("Apparel & Shoes", Arrays.asList("Casual Golf Belt", "Blue and green Sneaker"));


		// giving details for desktop
		Map<String, String> desktopConfig = new HashMap();
		desktopConfig.put("ProcessorR", processorR);
		desktopConfig.put("ProcessorD", processorD);
		desktopConfig.put("RamD", ramD);
		desktopConfig.put("RamR", ramR);
		desktopConfig.put("HDD", hdd);
		desktopConfig.put("Software1", software1);
		desktopConfig.put("Software2", software4);
		desktopConfig.put("OS2", OS2);
		desktopConfig.put("OS", OS);

		String ownE = "2060.00";
		String ownC = "1455.00";

		// Creating product page 2 object

		pp3.loginDeatils();

		AddProdcutToCart5 ap5 = new AddProdcutToCart5(driver);
		// Selecting header based on the map
		Map<String,Double>proNameandPrice=	pp3.selectCatagorey(headerName, productMap, desktopConfig);

		
		System.out.println("Here i am printing the actual price of all the prodcut"+pp3.productNamePrice);
		ShoppingCartPage2 scp2 = new ShoppingCartPage2(driver);
		
		System.out.println(ap5.productNameAndPrices);
		
		
		scp2.getCartData();
		// ✅ VERIFICATION - Add this part

		// scp2.clickOnTheShoppingCartLink();

		ArrayList<String> al = new ArrayList();
		
		al.add(software1);
		al.add(software4);

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
		shoes.put("Price", "1.00");

		Map<String, String> belt = new HashMap<>();
		belt.put("Price", "1.00");
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
		expectedData.put("Fiction", book2);

		
		Map<String, Map<String, String>> actualData = scp2.getCartData();
		
		verifyProductFromUIExcpetced(actualData);
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
					 System.out.println("✅ PASS: " + key + " - " + expectedValue);
					} else {
						Assert.fail("❌ " + key + " mismatch for " + expectedProductName + " | Expected: "
								+ expectedValue + " | Actual: " + actualValue);
					}
				}
			}
			// System.out.println("🎉 VERIFIED: " + expectedProductName);
		}

	}
	private String combineSoftware(ArrayList<String> listedSoftware) {
		if (listedSoftware.isEmpty()) {
			return "";
		}
		return String.join(", ", listedSoftware);

	}
}
