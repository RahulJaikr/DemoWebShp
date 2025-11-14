package shop.uttils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils
{

	
	
	public static String getCellValue(String filePath, String sheetName, int row, int col) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row dataRow = sheet.getRow(row);
            Cell cell = dataRow.getCell(col);

            return cell.getStringCellValue();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
