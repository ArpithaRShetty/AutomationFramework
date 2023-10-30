package practice;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;

public class GenericUtilityPractice {
	public static void main(String[] args) throws Throwable {
		PropertyFileUtility pUtil = new PropertyFileUtility();
		String URL = pUtil.readDatFromProperteyFile("url");
		System.out.println(URL);
		
		String BROWSER = pUtil.readDatFromProperteyFile("browser");
		System.out.println(BROWSER);
		
		ExcelFileUtility eUtil = new ExcelFileUtility();
		String data = eUtil.readDataFromExcel("Organization", 4, 2);
		System.out.println(data);
		
		JavaUtility jutil = new JavaUtility();
		int num = jutil.getRandomNumber();
		System.out.println(num);
		
		String date = jutil.getSystemDate();
		System.out.println(date);
	}

}
