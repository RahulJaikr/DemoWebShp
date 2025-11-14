package shop.dataProvider;

import shop.uttils.ExcelUtils;

public class TestDataProvider 
{
    private static final String FILE_PATH = "src/test/resources/RegisterData.xlsx";
    private static final String SHEET_NAME = "Sheet4";
    
    // Main Categories (Row 1)
    public static String getBook() {
        return ExcelUtils.getCellValue(FILE_PATH, SHEET_NAME, 1, 0);
    }
    
    public static String getComputer() {
        return ExcelUtils.getCellValue(FILE_PATH, SHEET_NAME, 1, 1);
    }
    
    public static String getElectronics() {
        return ExcelUtils.getCellValue(FILE_PATH, SHEET_NAME, 1, 2);
    }
    
    public static String getApparel() {
        return ExcelUtils.getCellValue(FILE_PATH, SHEET_NAME, 1, 3);
    }
    
    public static String getDigital() {
        return ExcelUtils.getCellValue(FILE_PATH, SHEET_NAME, 1, 4);
    }
    
    public static String getJewelry() {
        return ExcelUtils.getCellValue(FILE_PATH, SHEET_NAME, 1, 5);
    }
    
    public static String getGiftCards() {
        return ExcelUtils.getCellValue(FILE_PATH, SHEET_NAME, 1, 6);
    }
    
    // Computer Subcategories (Row 4)
    public static String getDesktops() {
        return ExcelUtils.getCellValue(FILE_PATH, SHEET_NAME, 4, 0);
    }
    
    public static String getNotebooks() {
        return ExcelUtils.getCellValue(FILE_PATH, SHEET_NAME, 4, 1);
    }
    
    public static String getAccessories() {
        return ExcelUtils.getCellValue(FILE_PATH, SHEET_NAME, 4, 2);
    }
    
    public static String getCamera() {
        return ExcelUtils.getCellValue(FILE_PATH, SHEET_NAME, 4, 3);
    }
    
    public static String getPhoto() {
        return ExcelUtils.getCellValue(FILE_PATH, SHEET_NAME, 4, 4);
    }
    
    public static String getCellPhones() {
        return ExcelUtils.getCellValue(FILE_PATH, SHEET_NAME, 4, 5);
    }
    
    // Product Data (Row 9)
    public static String getSmartphone() {
        return ExcelUtils.getCellValue(FILE_PATH, SHEET_NAME, 9, 0);
    }
    
    public static String getSmartphonePrice() {
        return ExcelUtils.getCellValue(FILE_PATH, SHEET_NAME, 10, 0);
    }
    
    public static String getShoes() {
        return ExcelUtils.getCellValue(FILE_PATH, SHEET_NAME, 9, 1);
    }
    
    public static String getShoesPrice() {
        return ExcelUtils.getCellValue(FILE_PATH, SHEET_NAME, 10, 1);
    }
    
    public static String getTop() {
        return ExcelUtils.getCellValue(FILE_PATH, SHEET_NAME, 9, 2);
    }
    
    public static String getTopPrice() {
        return ExcelUtils.getCellValue(FILE_PATH, SHEET_NAME, 10, 2);
    }
    
    public static String getAlbum() {
        return ExcelUtils.getCellValue(FILE_PATH, SHEET_NAME, 9, 3);
    }
    
    public static String getAlbumPrice() {
        return ExcelUtils.getCellValue(FILE_PATH, SHEET_NAME, 10, 3);
    }
    
    public static String getJewelryProduct() {
        return ExcelUtils.getCellValue(FILE_PATH, SHEET_NAME, 9, 4);
    }
    
    public static String getJewelryProductPrice() {
        return ExcelUtils.getCellValue(FILE_PATH, SHEET_NAME, 10, 4);
    }
    
    public static String getGiftCard() {
        return ExcelUtils.getCellValue(FILE_PATH, SHEET_NAME, 9, 5);
    }
    
    public static String getGiftCardPrice() {
        return ExcelUtils.getCellValue(FILE_PATH, SHEET_NAME, 10, 5);
    }
    
    // SubCategory Methods with clear names
    public static String getSubCategory4() {
        return ExcelUtils.getCellValue(FILE_PATH, SHEET_NAME, 4, 3); // Camera
    }
    
    public static String getSubCategory5() {
        return ExcelUtils.getCellValue(FILE_PATH, SHEET_NAME, 4, 4); // Photo
    }
    
    public static String getSubCategory6() {
        return ExcelUtils.getCellValue(FILE_PATH, SHEET_NAME, 4, 5); // Cell phones
    }
    
    // Alternative method names for better readability
    public static String getSubCategoryCamera() {
        return getSubCategory4();
    }
    
    public static String getSubCategoryPhoto() {
        return getSubCategory5();
    }
    
    public static String getSubCategoryCellPhones() {
        return getSubCategory6();
    }
    
    
}