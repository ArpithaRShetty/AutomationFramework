package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrganization {
	public static void main(String[] args) throws Throwable {

		// Step 1: Create all the required Objects
		JavaUtility jUtil = new JavaUtility();
		ExcelFileUtility eUtil = new ExcelFileUtility();
		PropertyFileUtility pUtil = new PropertyFileUtility();
		WebDriverUtility wUtil = new WebDriverUtility();
		WebDriver driver = null;

		// Step 2: Read the Required data
		String BROWSER = pUtil.readDatFromProperteyFile("browser");
		String URL = pUtil.readDatFromProperteyFile("url");
		String USERNAME = pUtil.readDatFromProperteyFile("username");
		String PASSWORD = pUtil.readDatFromProperteyFile("password");

		String ORGNAME = eUtil.readDataFromExcel("Contacts", 7, 3) + jUtil.getRandomNumber();
		String LASTNAME = eUtil.readDataFromExcel("Contacts", 7, 2);

		// Step 3:launch the browser
		if (BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println(BROWSER + " launched");
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println(BROWSER + " launched");
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println(BROWSER + " launched");
		} else {
			System.out.println("Invalod browser name");
		}
		wUtil.maximazeWindow(driver);
		wUtil.waitForPageLoad(driver);

		// Step 4: Login to the Application
		driver.get(URL);
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USERNAME);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();

		// Step 5: Navigate to the organization
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();

		// Step 6: Click on the Create Organization look up image
		driver.findElement(By.xpath("//img[contains(@title,'Organization')]")).click();

		// Step 7: Create Organization with mandatory information
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(ORGNAME);

		// Step 8: Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// step 9: Validate for Organization
		String OrgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

		if (OrgHeader.contains(ORGNAME)) {
			System.out.println("Organization created successfully");
		} else {
			System.out.println("FAIL");
		}

		// Step 10: Navigate to the contact Link
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();

		// Step 11: Click on the Create contact look up image
		driver.findElement(By.xpath("//img[contains(@title,'Create')]")).click();

		// Step 12: Create contacts with mandatory fields
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(LASTNAME);

		// Step 13: Click on organization look up image
		driver.findElement(By.xpath("(//img[@alt='Select'])[1]")).click();

		wUtil.switchToWindow(driver, "Accounts");

		driver.findElement(By.name("search_text")).sendKeys(ORGNAME);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+ORGNAME+"']")).click();

		wUtil.switchToWindow(driver, "Contacts");

		// Step 14: Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// step 15: Validate for Organization
		String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

		if (contactHeader.contains(LASTNAME)) {
			System.out.println(contactHeader);
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}
		// Step 16: Logout
		WebElement so = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mouseOverAction(driver, so);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		System.out.println("LogOut Successful");
	}
}
