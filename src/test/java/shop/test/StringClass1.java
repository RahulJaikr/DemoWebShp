package shop.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import shop.main.utils.ExcelUtils;

public class StringClass1 
{

	public static void main(String[] args)
	
	{
		String ramm = "8 GB [2000]";
		String ramm1 = "Windows 7 [+50.00]";//Windows 7 [+50.00]
		String text = null;
		System.out.println(ramm1.indexOf('['));
		
		if(ramm1.indexOf('[')>0){
			text = ramm1.substring(0, ramm1.indexOf('['));
		}else {
			text=ramm1;
		}
		
		
		

		System.out.println(text);
		
		
		String ram2 = "8GB [+60.00]";
		
		String result=ram2.substring(ram2.indexOf('+')+1, ram2.indexOf(']'));
		System.out.println(result);
		StringClass1 sc = new StringClass1();
		sc.mapTest();
	}

	

	
	
	public void mapTest()
	{
		Map<String, Map<String, String>> productDetails = new HashMap<>();

		// Product 1: Smartphone
		Map<String, String> smartphoneDetails = new HashMap<>();
		smartphoneDetails.put("price", "699.99");
		smartphoneDetails.put("quantity", "1");
		productDetails.put("smartphone", smartphoneDetails);

		// Product 2: Laptop
		Map<String, String> laptopDetails = new HashMap<>();
		laptopDetails.put("price", "1299.99");
		laptopDetails.put("quantity", "2");
		productDetails.put("laptop", laptopDetails);

		// Product 3: Headphones
		Map<String, String> headphonesDetails = new HashMap<>();
		headphonesDetails.put("price", "199.99");
		headphonesDetails.put("quantity", "3");
		productDetails.put("headphones", headphonesDetails);

		// Product 4: Tablet
		Map<String, String> tabletDetails = new HashMap<>();
		tabletDetails.put("price", "499.99");
		tabletDetails.put("quantity", "1");
		productDetails.put("tablet", tabletDetails);

		// Product 5: Smartwatch
		Map<String, String> smartwatchDetails = new HashMap<>();
		smartwatchDetails.put("price", "299.99");
		smartwatchDetails.put("quantity", "2");
		productDetails.put("smartwatch", smartwatchDetails);
		
		
		

	
	for (Map.Entry<String, Map<String, String>> entry : productDetails.entrySet()) {
	   // System.out.println(entry.getKey()); // This will print: smartphone, laptop, etc.
	    
	String   mainProduct = entry.getKey();
	   String price = entry.getValue().get("price");
	   String quantity =  entry.getValue().get("quantity");
		System.out.println(mainProduct +" (Productname and price is) "+price+" (and number of item) "+ quantity+  " ");
	}
	}
	public void run2()
	{
		String str ="Medium [+15.00]";
	String price = 	str.split("\\+")[1].trim();
	String d = price.split("\\]")[0].trim();
	System.out.println(d);
	}
	
	public void run3()
	{
	String str = "	Processor: Fast [+100.00]";
	String str1 =  str.split(":")[0].trim();
	String str2 = str.split(":")[1].trim();
	System.out.println(str1 +"    "+str2);
	}
	String software1 ="Jaikr";
	String software4 ="Apti";
	public void run4()
	{
		ArrayList<String> al = new ArrayList();
		al.add(software1);
		al.add(software4);
		addSoftwareIfNotEmpty(al,software1);
		 addSoftwareIfNotEmpty(al,software4);
		
		System.out.println(combineSoftware(al));
}
private String combineSoftware(ArrayList<String> listedSoftware) {
	if (listedSoftware.isEmpty()) {
		return "";
	}
	return String.join(", ", listedSoftware);

	}

private void addSoftwareIfNotEmpty(ArrayList<String> all, String SoftwareAL) {
	if (SoftwareAL != null & !SoftwareAL.trim().isEmpty()) {
		all.add(SoftwareAL);
	}
}
}