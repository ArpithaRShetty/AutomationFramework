package practice;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcelFile {
	public static void main(String[] args) throws Throwable {
		//Step 1: Open the doc in java readable format
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		
		//Step 2: Create workbook
		Workbook wb = WorkbookFactory.create(fis);
		
		//Step 3: Navigate to required sheet
		Sheet sh = wb.getSheet("Contacts");
		
		//Step 4: Navigate to required row
		Row rw = sh.getRow(7);
		
		//Step 5: Navigate to required cell
		Cell cl = rw.getCell(2);
		
		//Step 6: Capture the value and print
		String value = cl.getStringCellValue();
		System.out.println(value);
	}
}
