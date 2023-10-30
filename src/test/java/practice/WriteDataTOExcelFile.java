package practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataTOExcelFile {
	public static void main(String[] args) throws Throwable {
		// Step 1: Open the doc in java readable format
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");

		// Step 2: Create workbook
		Workbook wb = WorkbookFactory.create(fis);

		// Step 3: Navigate to required sheet
		Sheet sh = wb.getSheet("Contacts");

		// Step 4: Navigate to required row
		Row rw = sh.createRow(9);

		// Step 5: Navigate to required cell
		Cell cl = rw.createCell(0);

		// Step 6: Write the value and print
		cl.setCellValue("TC_007");
		FileOutputStream fis2 = new FileOutputStream(".\\src\\test\\resources\\TestData.xlsx");
		wb.write(fis2);
	}
}
